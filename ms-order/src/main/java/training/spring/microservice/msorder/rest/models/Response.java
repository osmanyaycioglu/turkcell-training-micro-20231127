package training.spring.microservice.msorder.rest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private boolean errorOccured;
    private String errorDesc;
    private String errorRootCause;
    private Integer errorCode;
    private T body;

}
