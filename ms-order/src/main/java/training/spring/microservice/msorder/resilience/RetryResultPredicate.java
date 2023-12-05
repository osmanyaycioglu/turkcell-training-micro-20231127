package training.spring.microservice.msorder.resilience;

import training.spring.microservice.mscustomerapi.Customer;

import java.util.function.Predicate;

public class RetryResultPredicate implements Predicate<Customer> {
    @Override
    public boolean test(final Customer customerParam) {
        if (customerParam.getPhoneNumber().length() > 10){
            return true;
        }
        return false;
    }
}
