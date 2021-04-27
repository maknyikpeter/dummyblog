package com.dummyblog.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorPageController implements ErrorController{
	
private static final String ERROR_PATH = "/error";
	
	@GetMapping(ERROR_PATH)
	public String errorHandler(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "404";
	        }
	    }
	    return "error";
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
