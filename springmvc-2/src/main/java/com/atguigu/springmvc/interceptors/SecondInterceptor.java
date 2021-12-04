package com.atguigu.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * 
 * 执行顺序：preHandle -> 目标方法 -> postHandle -> 渲染视图 -> afterCompletion
 * 
 * @author Meiko
 *
 */
public class SecondInterceptor implements HandlerInterceptor {
	
	/**
	 * 此方法在渲染视图之后调用
	 * 
	 * 此方法可用作释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("[SecondInterceptor] afterCompletion");
	}
	
	/**
	 * 此方法在调用目标方法之后，渲染视图之前被调用
	 * 
	 * 此方法可对请求域中的属性或视图做处理
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("[SecondInterceptor] postHandle");
	}
	
	/**
	 * 在目标方法之前被调用
	 * 若返回值为true，则继续调用后续的拦截器和目标方法
	 * 若返回值为false，则不再调用其它的拦截器和目标方法
	 * 
	 * 此方法可以考虑做权限控制、事务、日志等操作
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("[SecondInterceptor] preHandle");
		return false;
	}

}
