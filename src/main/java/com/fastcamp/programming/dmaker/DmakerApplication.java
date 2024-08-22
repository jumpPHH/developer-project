package com.fastcamp.programming.dmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
/*
@SpringBootApplication => 3가지 어노테이션의 조합

@SpringBootConfiguration
: @Configuration 와 동일한 역활을 한다.
  하나 이상의 @Bean 메서드를 포함하고 있고 , Spring 컨테이너의 의해 빈 정의 소스로 사용된다.

@EnableAutoConfiguration
: Spring Boot의 자동 구성 기능을 활성화 한다.
Application의 class path를 스캔하고 기본 bean들을 자동으로 등록한다.

@ComponentScan
: 지정된 패키지와 그 하위 패키지를 스캔하여 , @Component, @Service, @Repository, @Controller
등 어노테이션 이 적용된 bean들을 Spring IoC 컨테이너에 등록한다.

*/
public class DmakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmakerApplication.class, args);
    }

}
