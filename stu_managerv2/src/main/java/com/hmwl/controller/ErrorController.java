package com.hmwl.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Api(value = "ErrorController",description = "处理没有权限的时候,避免异常")
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(org.apache.shiro.authz.AuthorizationException.class)
    public String nopermission(){
        return "nopermission";
    }
}
