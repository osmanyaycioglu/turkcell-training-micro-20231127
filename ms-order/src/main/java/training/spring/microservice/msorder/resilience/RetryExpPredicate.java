package training.spring.microservice.msorder.resilience;

import training.spring.microservice.mscommon.error.ErrorObj;
import training.spring.microservice.mscommon.error.RemoteException;

import java.util.function.Predicate;

public class RetryExpPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable throwableParam) {
        if (throwableParam instanceof RemoteException){
            RemoteException remoteExceptionLoc = (RemoteException) throwableParam;
            ErrorObj        errorObjLoc        = remoteExceptionLoc.getErrorObj();
            Integer         errorCodeLoc       = errorObjLoc.getErrorCode();
            return switch (errorCodeLoc) {
                case 1040, 1041, 1042, 1043, 1044 -> true;
                default -> false;
            };
        }
        return true;
    }

}
