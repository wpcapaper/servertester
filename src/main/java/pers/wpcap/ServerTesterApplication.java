package pers.wpcap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import pers.wpcap.config.RedisContext;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
@Import(value = {
		RedisContext.class
})
@SpringBootApplication
public class ServerTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTesterApplication.class, args);
	}
}
