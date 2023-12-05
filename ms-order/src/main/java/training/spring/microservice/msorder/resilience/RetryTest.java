package training.spring.microservice.msorder.resilience;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
@RequiredArgsConstructor
public class RetryTest implements CommandLineRunner {
    private final CallMe        callMe;
    private final RetryRegistry retryRegistry;

    @Override
    public void run(final String... args) throws Exception {
        Retry retryLoc = retryRegistry.retry("customerQuery");
        retryLoc.getEventPublisher()
                .onRetry(re -> System.out.println("retry yapıldı"));

        Retry.Metrics metricsLoc = retryLoc.getMetrics();

        for (int i = 0; i < 50; i++) {

            try {
                callMe.testSomething();
            } catch (Exception eParam) {
                System.out.println("Retry error : " + eParam.getClass()
                                                            .getName() + " m:" + eParam.getMessage());

            }
            System.out.println("Real call : "
                               + i
                               + " s : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " sr : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " fr : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()
                               + " f : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
            );
        }
    }
}
