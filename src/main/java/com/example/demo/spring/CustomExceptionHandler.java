package com.example.demo.spring;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseBody
    @ExceptionHandler
    public Object exceptionHandler(MethodArgumentNotValidException bindException) {
        //((BeanPropertyBindingResult) bindException.bindingResult).errors.get(0).defaultMessage
        bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindException.getBindingResult().getAllErrors()) {
            msg.append(error.getDefaultMessage()).append("，");
        }

        return msg.substring(0, msg.length() - 1);
    }

    @ResponseBody
    @ExceptionHandler
    public Object exceptionHandler(Exception bindException) {
        return bindException.getMessage();
    }
}
