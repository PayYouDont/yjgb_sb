package com.gospell.chitong.rdcenter.broadcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.gospell.chitong.rdcenter.broadcast.*.dao")
public class Application {
	 private static String[] args;
	 private static ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		Application.args = args;
		Application.context = SpringApplication.run(Application.class, args);
		
	}
	public static void restart() {
        context.close();
        Application.context = SpringApplication.run(Application.class, args); 
    }
}

