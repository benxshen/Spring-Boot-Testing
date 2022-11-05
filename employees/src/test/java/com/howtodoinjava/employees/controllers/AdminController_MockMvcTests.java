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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.howtodoinjava.employees.model.Employee;
import com.howtodoinjava.employees.services.EmployeeService;

/**
 * ref - https://docs.spring.io/spring-security/reference/servlet/test/method.html
 */
@WebMvcTest({AdminController.class, AdminController_MockMvcTests.Config.class})
public class AdminController_MockMvcTests {

	@TestConfiguration
	@EnableGlobalMethodSecurity(prePostEnabled = true)  // https://stackoverflow.com/questions/61428063/spring-boot-hasanyauthority-is-not-working-correctly
	static class Config {}

	@Autowired
	MockMvc mockMvc;


	@Test
	@WithMockUser(roles = "ADMIN")
	public void testGetAdminData() throws Exception {

		mockMvc.perform(get("/admin"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				;
	}

	@Test
	public void testGetAdminData_Unauthorized_401() throws Exception {

		mockMvc.perform(get("/admin"))
				.andExpect(status().is(401))
		;
	}

	@Test
	@WithMockUser  // default role is USER
	public void testGetAdminData_Unauthorized_403() throws Exception {

		mockMvc.perform(get("/admin"))
				.andExpect(status().is(403))
		;
	}

	@Test
	@WithMockUser
	public void testGetInfoData() throws Exception {
		mockMvc.perform(get("/info"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
		;
	}

	@Test
	public void testGetInfoData_Unauthorized_401() throws Exception {
		mockMvc.perform(get("/info"))
				.andExpect(status().is(401))
		;
	}

}
