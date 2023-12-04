package training.spring.microservice.mscommon.error;

public class RemoteException extends RuntimeException {
    private ErrorObj errorObj;

    public RemoteException(final ErrorObj errorObjParam) {
        super("Remote Error");
        errorObj = errorObjParam;
    }

    public ErrorObj getErrorObj() {
        return errorObj;
    }
}
