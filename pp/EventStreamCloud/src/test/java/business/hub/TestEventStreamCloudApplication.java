package business.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;


@TestConfiguration(proxyBeanMethods = false)
public class TestEventStreamCloudApplication {


	public static void main(String[] args) {
		SpringApplication.from(EventStreamCloudApplication::main).with(TestEventStreamCloudApplication.class).run(args);
	}

}

