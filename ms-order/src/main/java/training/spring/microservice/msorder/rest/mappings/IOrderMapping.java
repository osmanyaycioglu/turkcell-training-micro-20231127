package training.spring.microservice.msorder.rest.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import training.spring.microservice.msorder.rest.models.OrderDto;
import training.spring.microservice.msorder.services.models.Order;

@Mapper
public interface IOrderMapping {

    IOrderMapping INSTANCE = Mappers.getMapper(IOrderMapping.class);

    @Mapping(source = "address.city",target = "city")
    @Mapping(source = "address.street",target = "street")
    @Mapping(source = "address.addressDesc",target = "addressDesc")
    @Mapping(source = "firstName",target = "name")
    @Mapping(source = "lastName",target = "surname")
    Order toOrder(OrderDto orderDtoParam);



}
