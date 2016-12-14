package org.wcy123;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoLoggerApplication {

	@PostConstruct
	void setup(){
		log.debug("debug setup");
		log.info("info setup");
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoLoggerApplication.class, args);
	}
}
