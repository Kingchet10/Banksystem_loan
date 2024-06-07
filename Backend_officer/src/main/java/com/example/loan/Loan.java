package com.example.loan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn
@SpringBootApplication
@MapperScan("com.example.loan.mapper")
public class Loan {

	public static void main(String[] args) {
		SpringApplication.run(Loan.class, args);
	}
}
