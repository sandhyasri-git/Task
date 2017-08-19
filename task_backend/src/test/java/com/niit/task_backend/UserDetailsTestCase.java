package com.niit.task_backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.task_backend.dao.UserDAO;
import com.niit.task_backend.model.UserDetails;

public class UserDetailsTestCase {
	
	
	static AnnotationConfigApplicationContext context;

	
	static UserDAO userDAO;
	
	
	static UserDetails user;
	
	@BeforeClass
	public static  void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
		
	}



	@Test
	public void test() {
		user=new UserDetails();
		user.setUsername("admin");
		user.setFirstname("Admin");
		user.setLastname("ADMIN");
		user.setEmail("admin@gmail.com");
		user.setPass("admin@gmail.com");
		user.setPhonenumber("9908638763");
		user.setRole("ROLE_ADMIN");
		userDAO.addUser(user);
		assertEquals("saved",true);
	

	}

}
