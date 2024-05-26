package com.david.ecommercecrm.exception.controller;

import com.david.ecommercecrm.exception.domain.InvalidPriceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ModelAndView modelAndView;

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
        log.error("SQLException Occurred: URL = {}", request.getRequestURL());
        return "database_error";
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occurred")
    @ExceptionHandler(IOException.class)
    public void handleIOException(HttpRequest request, Exception ex){
        log.error("IOException handler executed");
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ModelAndView handleInvalidPriceException(HttpServletRequest request, Exception ex){
        log.error("Requested URL = {}", request.getRequestURL());
        log.error("Exception Raised = {}", ex.getMessage());

        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName(request.getRequestURL().toString());
        return modelAndView;
    }

}
