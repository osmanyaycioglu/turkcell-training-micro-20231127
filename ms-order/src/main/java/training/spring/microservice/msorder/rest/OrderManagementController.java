package training.spring.microservice.msorder.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import training.spring.microservice.msorder.rest.mappings.IOrderMapping;
import training.spring.microservice.msorder.rest.models.OrderDto;
import training.spring.microservice.msorder.services.OrderManagementService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/order/management")
@RequiredArgsConstructor
public class OrderManagementController {
    private final OrderManagementService orderManagementService;
//    private       List<String>           stringList      = Collections.synchronizedList(new ArrayList<>());
//    private       Map<String, OrderDto>  stringStringMap = new ConcurrentHashMap<>();

    @PostMapping("/place")
    public String placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        orderManagementService.placeOrder(IOrderMapping.INSTANCE.toOrder(orderDtoParam));
        return "OK";
    }

}
