package com.wcx.excelword;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.SpringApplication;

@SpringBootApplication
@MapperScan("com.wcx.excelword.dao")
public class ExcelwordApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelwordApplication.class, args);
    }

}
