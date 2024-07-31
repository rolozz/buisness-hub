package business.hub.cancellationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CancellationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CancellationServiceApplication.class, args);
    }

}