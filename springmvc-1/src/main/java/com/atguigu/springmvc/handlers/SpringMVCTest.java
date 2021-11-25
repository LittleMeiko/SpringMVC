package com.atguigu.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 标准的HTTP请求头包含如下部分：
 * 	1.请求方法
 *  2.请求的URL
 *  3.请求的协议及版本
 *  4.请求头
 *  5.请求参数
 * 
 * @RequestMapping除了可以使用请求URL映射请求外，还可以使用请求方法、请求参数、请求头来映射请求
 * 	value:请求URL
 *  method:请求方法
 *  params:请求参数
 *    -- param1:表示请求必须包含名为param1的请求参数
 *    -- !param1:表示请求不能包含名为param1的请求参数
 *    -- parma1 != value1:表示请求必须包含名为param1的请求参数,但其值不能为value1
 *    -- {"parma1=value1","param2"}:表示请求必须包含名为param1和param2的请求参数，且param1参数的值必须为value1
 *  headers:请求头
 * 联合使用多个条件可以让请求更加精确化
 * 
 * @RequestMapping的URL支持三种形式的通配符：
 * 	？:匹配一个字符
 * 	   /user/createUser??可以匹配:/user/createUseraa、/user/createUserbb等URL
 *  *:匹配任意字符
 *  **:匹配多层路径
 *  
 *  @PathVariable映射URL绑定的占位符：可以将URL中占位符参数绑定到控制器方法的入参中
 *  
 *  REST:Representational State Transfer即(资源)表现层状态转化。是一种互联网软件架构。
 *    -- 资源(Resources):网络上的一个实体，或者是网络上的一个具体信息。URI(统一资源定位符)为每一个资源的独一无二的识别符
 *    -- 表现层(Representation):把资源具体呈现出来的形式，叫做它的表现层
 *    -- 状态转化(State Tranafer):如果客户端想要操作服务器，必须通过某种手段，让服务器端发生状态转化。而这种转化就是建立在
 *    	 表现层之上的，所以就是"表现层状态转化"。
 *    	 HTTP协议里四个表示操作方式的动词：
 *    	  -- GET:获取资源
 *        -- POST:新建资源
 *        -- PUT:更新资源
 *        -- DELETE:删除资源
 *    HiddenHttpMethodFilter过滤器可将POST请求转化为PUT/DELETE请求
 *  
 * 
 * @author Meiko
 *
 */
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	// DELETE 和 PUT 是不支持转发的，只支持重定向，所以发送put和delete请求时浏览器会报405错误
	@RequestMapping(value = "/testRestPut/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable("id")Integer id) {
		System.out.println("testRestPut:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestDelete/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id")Integer id) {
		System.out.println("testRestDelete:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestPost", method = RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRestPost");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestGet/{id}", method = RequestMethod.GET)
	public String testRestGet(@PathVariable("id")Integer id) {
		System.out.println("testRestGet:" + id);
		return SUCCESS;
	}
	
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable:" + id);
		return SUCCESS;
	}
	
	@RequestMapping("/testAntPath/**/mvc??")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testParamsAndHeaders", 
			params = {"username", "age!=11"}, headers = "Accept-Language=en-US,zh;q=0.9")
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	/**
	 * 较为常用：使用请求方法来映射请求
	 * @return
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 1.@RequestMapping不仅可以修饰方法还可以修饰类
	 * 2.类定义处：提供初步的请求映射信息，相对于WEB应用的根目录
	 *   方法处：提供进一步的细分映射信息，相对于类定义处的URL。若类定义处未标注@RequestMapping，则方法标记
	 *   	   的URL相对于WEB应用的根目录。
	 *         
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}

}