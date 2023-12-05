package training.spring.microservice.mscustomer;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import training.spring.microservice.mscommon.error.ErrorObj;
import training.spring.microservice.mscommon.error.RemoteException;
import training.spring.microservice.mscustomer.models.AddResult;
import training.spring.microservice.mscustomer.models.FraudQueryRequest;
import training.spring.microservice.mscustomer.models.FraudQueryResult;
import training.spring.microservice.mscustomerapi.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class CustomerProvisionController {
    private final ReactorLoadBalancerExchangeFilterFunction lbFilterFunction;

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/api/v1/customer/provision/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Customer> GetAllCustomers() {
        return Flux.just(
                new Customer("osman","yaycıoğlu",10D,"LocalPort:" + port,"xyz"),
                new Customer("ali","veli",20D,"LocalPort:" + port,"xyz"));
    }

    private AtomicInteger counter    = new AtomicInteger();

    @PostMapping("/api/v1/customer/provision/add")
    public Mono<AddResult> add(Customer customerParam) {
        counter.incrementAndGet();
        HttpClient httpClientLoc = HttpClient.create()
                                             .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                                                     2000)
                                             .doOnConnected(c -> c.addHandlerLast(new ReadTimeoutHandler(10))
                                                                  .addHandlerLast(new WriteTimeoutHandler(10)));

        WebClient buildLoc = WebClient.builder()
                                      .clientConnector(new ReactorClientHttpConnector(httpClientLoc))
                                      .defaultHeader("xyz",
                                                     "osman")
                                      .filter(lbFilterFunction)
                                      .build();

        return buildLoc.post()
                       .uri("http://FRAUD/api/v1/fraud/query/check")
                       .body(BodyInserters.fromValue(new FraudQueryRequest(customerParam.getPhoneNumber(),
                                                                           "27816312123",
                                                                           "Turkey")))
                       .retrieve()
                       .onStatus(HttpStatusCode::isError,
                                 r ->
                                         r.bodyToMono(ErrorObj.class)
                                          .flatMap(e -> Mono.error(new RemoteException(e)))
                       )
                       .bodyToMono(FraudQueryResult.class)
                       .doOnNext(f -> System.out.println("Received Fraud Response : " + f))
                       .flatMap(f -> {
                           return Mono.just(new AddResult(f.toString(),
                                                          "128973123"));
                       });
    }

}
