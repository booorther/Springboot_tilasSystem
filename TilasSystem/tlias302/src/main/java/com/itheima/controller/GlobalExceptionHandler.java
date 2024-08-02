package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice//声明当前类是一个全局异常处理器，捕获整个项目的异常
public class GlobalExceptionHandler {
   /*ExceptionHandler当前方法获取异常类型
     Exception.class捕获所有异常
     Return：
    */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.info("报错："+e.getMessage());
        return Result.error(e.getMessage());
    }
}
