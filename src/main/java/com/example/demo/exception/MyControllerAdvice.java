package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler
    public ModelAndView errorHandler(Exception ex){
        Map map = new HashMap();
        map.put("msg",ex.getMessage());
        ModelAndView mad = new ModelAndView();
        mad.setViewName("error");
        mad.addObject("msg",map);
        return mad;
    }
}
