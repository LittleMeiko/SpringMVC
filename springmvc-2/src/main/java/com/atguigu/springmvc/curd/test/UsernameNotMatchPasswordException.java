package com.atguigu.springmvc.curd.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * SpringMVC处理异常的HandlerExceptionResolver的常用实现类二：ResponseStatusExceptionResolver
 * @ResponseStatus 注解既可修饰类也可用于修饰方法
 * 
 * @author Meiko
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户名和密码不匹配")
public class UsernameNotMatchPasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
