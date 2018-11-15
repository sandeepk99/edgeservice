package io.uslab.edgeservice;

import io.uslab.edgeservice.filter.ZuulPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class EdgeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeserviceApplication.class, args);
	}

	@Bean
	public ZuulPreFilter preFilter() {
		return new ZuulPreFilter();
	}
}
