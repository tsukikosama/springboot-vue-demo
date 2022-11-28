package com.miku;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.miku.mapper")
@Slf4j
public class BlogApplication {
    public static void main(String[] args) {
        log.info("项目开始启动了。。。");
        SpringApplication.run(BlogApplication.class,args);
    }
}
