package training.spring.microservice.mscommon.error;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;


public class GneralErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String sParam,
                            final Response responseParam) {

        try {
            InputStream inputStreamLoc = responseParam.body()
                                                      .asInputStream();
            ObjectMapper objectMapperLoc = new ObjectMapper();
            ErrorObj errorObjLoc = objectMapperLoc.readValue(inputStreamLoc,
                                                             ErrorObj.class);
            return new RemoteException(errorObjLoc);
        } catch (IOException eParam) {
            return new RemoteException(ErrorObj.builder()
                                               .withErrorDesc("IO problem")
                                               .withErrorCode(1099)
                                               .build());
        }
    }
}
