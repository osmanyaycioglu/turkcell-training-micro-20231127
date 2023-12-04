package training.spring.microservice.mscommon.error;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class ErrorObj {
    private String         boundedContext;
    private String         microservice;
    private List<ErrorObj> subErrors;
    private String         errorDesc;
    private Integer        errorCode;
}
