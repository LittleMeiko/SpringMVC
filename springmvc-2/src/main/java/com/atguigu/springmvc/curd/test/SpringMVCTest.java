package com.atguigu.springmvc.curd.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springmvc.curd.dao.EmployeeDao;
import com.atguigu.springmvc.curd.entities.Employee;

@Controller
public class SpringMVCTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping(value = "/testConversionServiceConverter")
	public String testConverter(@RequestParam(value = "employee")Employee employee) {
		System.out.println("save:" + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
