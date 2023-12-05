package training.spring.microservice.msorder.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import training.spring.microservice.mscommon.error.ErrorObj;
import training.spring.microservice.mscommon.error.RemoteException;
import training.spring.microservice.mscustomerapi.Customer;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerQueryIntegration {
    private final RestTemplate          restTemplate;
    private final EurekaDiscoveryClient eurekaDiscoveryClient;
    private final IClientQueryInt       clientQueryInt;

    private int count = 0;

    @Retry(name = "customerQuery",fallbackMethod = "getCustomerFallback")
    @CircuitBreaker(name = "cq_cb")
    public Customer getCustomer(String phone) {
        Customer customerLoc = null;
        try {
            customerLoc = restTemplate.getForObject("http://CUSTOMER/api/v1/customer/query/find/one?phone="
                                                    + phone,
                                                    Customer.class);
        } catch (RestClientResponseException eParam) {
            ObjectMapper objectMapperLoc = new ObjectMapper();
            try {
                ErrorObj errorObjLoc = objectMapperLoc.readValue(eParam.getResponseBodyAsByteArray(),
                                                                 ErrorObj.class);
                throw new RemoteException(errorObjLoc);
            } catch (IOException exParam) {
                throw new RemoteException(ErrorObj.builder()
                                                  .withErrorDesc("IO Problemi : " + exParam.getMessage())
                                                  .withErrorCode(10033)
                                                  .build());
            }

        }
        return customerLoc;
    }

    public Customer getCustomerFallback(String phone,Exception exp) {
        System.out.println("get customer fallback");
        return new Customer();
    }

    public Customer getCustomer2(String phone) {
        List<ServiceInstance> customerServicesLoc = eurekaDiscoveryClient.getInstances("CUSTOMER");
        if (customerServicesLoc != null && !customerServicesLoc.isEmpty()) {
            RestTemplate    restTemplateLoc    = new RestTemplate();
            int             index              = count++ % customerServicesLoc.size();
            ServiceInstance serviceInstanceLoc = customerServicesLoc.get(index);
            Customer customerLoc = restTemplateLoc.getForObject("http://"
                                                                + serviceInstanceLoc.getHost()
                                                                + ":"
                                                                + serviceInstanceLoc.getPort()
                                                                + "/api/v1/customer/query/find/one?phone="
                                                                + phone,
                                                                Customer.class);
            return customerLoc;
        }
        return null;
    }

    public Customer getCustomer3(String phone) {
        return clientQueryInt.findCustomer(phone);
    }

}
