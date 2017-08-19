package com.niit.task_backend.dao;

import org.springframework.stereotype.Component;

import com.niit.task_backend.model.UserDetails;
@Component
public interface UserDAO {

	void addUser(UserDetails user);
	UserDetails login(UserDetails user);
	void update(UserDetails user);
	UserDetails getUserById(long id);
}
