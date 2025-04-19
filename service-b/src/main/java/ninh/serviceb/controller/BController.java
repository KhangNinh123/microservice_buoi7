package ngduc.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BController {

    @GetMapping("/b/hello")
    public String hello() {
        return "Hello from Service B";
    }
}