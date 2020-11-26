package com.ktc.base.controller;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
* 全局处理异常
*/
@ControllerAdvice
public class BaseExceptionHandler {
/*
//controller如果出现空指针异常则会跳转到这个方法处理
@ExceptionHandler(NullPointerException.class)
@ResponseBody
public Result nullPointerExceptionError(NullPointerException e){
return new Result(false, StatusCode.ERROR,e.getMessage());
}
//controller如果出现算数则会跳转到这个方法处理
@ExceptionHandler(ArithmeticException.class)
@ResponseBody
public Result arithmeticExceptionError(ArithmeticException e){
return new Result(false, StatusCode.ERROR,e.getMessage());
}
*/
//统一捕捉异常
@ExceptionHandler(Exception.class)
@ResponseBody
public Result error(Exception e){
return new Result(false,StatusCode.ERROR,e.getMessage());
}
}