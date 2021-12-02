package com.atguigu.springmvc.curd.test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.springmvc.curd.dao.EmployeeDao;
import com.atguigu.springmvc.curd.entities.Employee;

@Controller
public class SpringMVCTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
