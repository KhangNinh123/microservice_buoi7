package ngduc.servicea.controller;

import ngduc.servicea.client.ServiceBClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class TestController {

    private final ServiceBClient serviceBClient;

    public TestController(ServiceBClient serviceBClient) {
        this.serviceBClient = serviceBClient;
    }

    @GetMapping("/call-b-cb")
    public String callServiceBCB() {
        return serviceBClient.callServiceWithCircuitBreakerOnly();
    }

    @GetMapping("/call-b-retry")
    public String callServiceBRetry() {
        return serviceBClient.callServiceWithRetryOnly();
    }
}