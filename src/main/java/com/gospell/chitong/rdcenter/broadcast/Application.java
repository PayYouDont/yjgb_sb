package com.gospell.chitong.rdcenter.broadcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@ServletComponentScan
@MapperScan("com.gospell.chitong.rdcenter.broadcast.*.dao")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}