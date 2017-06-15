package pers.wpcap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
@SpringBootApplication
public class ServerTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTesterApplication.class, args);
	}
}
