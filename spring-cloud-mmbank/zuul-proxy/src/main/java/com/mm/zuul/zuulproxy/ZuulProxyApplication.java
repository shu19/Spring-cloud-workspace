package com.mm.zuul.zuulproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.mm.zuul.zuulproxy.filters.ErrorFilter;
import com.mm.zuul.zuulproxy.filters.PostFilter;
import com.mm.zuul.zuulproxy.filters.PreFilter;
import com.mm.zuul.zuulproxy.filters.RouteFilter;

@EnableZuulProxy
@SpringBootApplication

public class ZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
