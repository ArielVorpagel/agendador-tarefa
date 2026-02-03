package com.javanauta.agendadortarefa;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendadorTarefaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadorTarefaApplication.class, args);
	}

}
