package com.atguigu.springmvc.curd.test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.swing.text.rtf.RTFEditorKit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.springmvc.curd.dao.EmployeeDao;
import com.atguigu.springmvc.curd.entities.Employee;

@Controller
public class SpringMVCTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam(value = "i")int i) {
		String[] strArr = new String[10];
		System.out.println(strArr[i]);
		return "success";
	}
	
	/**
	 * SpringMVC处理异常的HandlerExceptionResolver的常用实现类三：DefaultHandlerExceptionResolver
	 * 用于处理SpringMVC的特殊异常 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testDefaultHandlerExceptionResolver", method = RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver() {
		System.out.println("testDefaultHandlerExceptionResolver...");
		return "success";
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "测试")
	@RequestMapping(value = "/testResponseStatusExceptionHandler")
	public String testResponseStatusExceptionHandler(@RequestParam(value = "i")int i) {
		if (i == 13) {
			throw new UsernameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionHandler...");
		return "success";
	}
	
//	@ExceptionHandler(value = {RuntimeException.class})
//	public ModelAndView handlerArithmeticException2(Exception ex) {
//		System.out.println("[出异常了]：" + ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
	
	/**
	 * SpringMVC处理异常的HandlerExceptionResolver的常用实现类一：
	 *   ExceptionHandlerExceptionResolver
	 *   
	 * 若希望将异常信息返回到页面上可使用ModleAndView对象作为返回值
	 * 
	 * 异常处理方法的优先级：最近原则，即与异常类最匹配的执行
	 * 
	 * @ControllerAdvice：如果在当前Handler方法中找不到@ExceptionHandler标注的方法，则@ControllerAdvice标注
	 * 的类中去找@ExceptionHandler标注的方法处理异常
	 * 
	 * @param ex
	 * @return
	 */
//	@ExceptionHandler(value = {ArithmeticException.class})
//	public ModelAndView handlerArithmeticException(Exception ex) {
//		System.out.println("出异常了：" + ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
	
	@RequestMapping(value = "/testExceptionHandlerExceptionReslover")
	public String testExceptionHandlerExceptionReslover(@RequestParam("i")int i) {
		System.out.println("result:" + 10/i);
		return "success";
	}
	
	@RequestMapping(value = "/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file")MultipartFile file) throws IOException {
		System.out.println("desc:" + desc);
		System.out.println("originalFilename:" + file.getOriginalFilename());
		System.out.println("inputStream:" + file.getInputStream());
		return "success";
	}
	
	@RequestMapping(value = "/i18n")
	public String testI18n(Locale locale) {
		String message = messageSource.getMessage("i18n.user", null, locale);
		System.out.println(message);
		return "i18n";
	}
	
	/**
	 * 文件下载
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/abc.txt");
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {
		System.out.println(body);
		return "Hello World!" + LocalDateTime.now();
	}
	
	//加上@ResponseBody注解可返回json数据
	@ResponseBody
	@RequestMapping(value = "/testJson")
	public Collection<Employee> testJson() {
		
		return employeeDao.getAll();
	}
	
	@RequestMapping(value = "/testConversionServiceConverter")
	public String testConverter(@RequestParam(value = "employee")Employee employee) {
		System.out.println("save:" + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
