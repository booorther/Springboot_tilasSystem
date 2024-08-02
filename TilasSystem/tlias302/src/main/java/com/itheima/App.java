package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//先启动前端服务："D:\Exercise\PraticalTraining2\nginx-1.22.0-tlias\nginx.exe"
@SpringBootApplication
@ServletComponentScan//开启springboot项目对过滤器（filter），servelet，监听器  进行扫描
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
