package com.atguigu.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.atguigu.springmvc.curd.entities.Department;
import com.atguigu.springmvc.curd.entities.Employee;

@Component
public class EmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String source) {
		if (source != null) {
			String[] valArr = source.split("-");
			if (valArr != null && valArr.length == 4) {
				String lastName = valArr[0];
				String email = valArr[1];
				Integer gender = Integer.parseInt(valArr[2]);
				Department department = new Department();
				department.setId(Integer.parseInt(valArr[3]));
				
				Employee employee = new Employee(gender, lastName, email, gender, department);
				System.out.println(source + "--convert--" + employee);
				return employee;
			}
		}
		
		return null;
	}

}
