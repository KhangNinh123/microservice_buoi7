package ngduc.servicea.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceBClient {

    private static final Logger logger = LoggerFactory.getLogger(ServiceBClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "serviceB", fallbackMethod = "getDefaultResponse")
    public String callServiceWithCircuitBreakerOnly() {
        logger.info("[CB] Sending request to Service B...");
        return restTemplate.getForObject("http://localhost:8082/b/hello", String.class);
    }

    @Retry(name = "serviceB", fallbackMethod = "getDefaultResponse")
    public String callServiceWithRetryOnly() {
        logger.info("[Retry] Sending request to Service B...");
        return restTemplate.getForObject("http://localhost:8082/b/hello", String.class);
    }

    public String getDefaultResponse(Throwable t) {
        logger.warn("[Fallback] Service B failed: {}", t.getMessage());
        return "Fallback from Service A: Service B is down!";
    }
}