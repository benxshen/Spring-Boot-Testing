package com.howtodoinjava.employees.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.howtodoinjava.employees.model.Employee;
import com.howtodoinjava.employees.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeController_MockMvcTests {

	@MockBean
	EmployeeService employeeService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testFindAll() throws Exception {
		Employee employee = new Employee("Lokesh", "Gupta");
		List<Employee> employees = List.of(employee);

		Mockito.when(employeeService.findAll()).thenReturn(employees);

		mockMvc.perform(get("/employee"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", Matchers.is("Lokesh")));
	}

}
