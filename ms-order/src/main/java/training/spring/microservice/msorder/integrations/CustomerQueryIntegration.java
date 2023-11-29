package training.spring.microservice.msorder.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import training.spring.microservice.msorder.integrations.models.Customer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerQueryIntegration {
    private final RestTemplate          restTemplate;
    private final EurekaDiscoveryClient eurekaDiscoveryClient;
    private final IClientQueryInt       clientQueryInt;

    private int count = 0;


    public Customer getCustomer(String phone) {
        Customer customerLoc = restTemplate.getForObject("http://CUSTOMER/api/v1/customer/query/find/one?phone="
                                                         + phone,
                                                         Customer.class);
        return customerLoc;
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
