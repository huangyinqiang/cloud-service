package com.cloud.user.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//全局的异常处理器，如果没有你抛异常的时候，只会返回500
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	//如果抛出IllegalArgumentException.class 异常，客户端响应（HttpStatus.BAD_REQUEST）400状态码
	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> badRequestException(IllegalArgumentException exception) {
		Map<String, Object> data = new HashMap<>();
		data.put("code", HttpStatus.BAD_REQUEST.value());
		data.put("message", exception.getMessage());

		return data;
	}
}
