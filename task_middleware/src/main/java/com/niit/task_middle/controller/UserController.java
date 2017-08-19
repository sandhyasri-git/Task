package com.niit.task_middle.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.task_backend.dao.UserDAO;
import com.niit.task_backend.model.UserDetails;
import com.niit.task_backend.model.Error;
@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	@RequestMapping(value="/newuser" , method=RequestMethod.POST)
	public ResponseEntity<?>registerUser(@RequestBody UserDetails user)
	{
		try
		{
		userDAO.addUser(user);
			return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error=new Error(1,"Unable to register new User ");
    		//Error error=new Error();
    		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	@PostMapping("/login")
	public ResponseEntity<?> checkLogin(@RequestBody UserDetails user, HttpSession session)
	{
		UserDetails checkUser=userDAO.login(user);
		if(checkUser==null)
		{
			Error error=new Error(4,"Invalid username/password.. please enter valid username/password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);

		}
		checkUser.setStatus("true");
		userDAO.update(checkUser);
		session.setAttribute("username", checkUser.getUsername());
    	session.setAttribute("userid", checkUser.getId());
    	return new ResponseEntity<UserDetails>(checkUser,HttpStatus.OK);
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
    public ResponseEntity<?> logout(HttpSession session){
    	if(session.getAttribute("username")==null){
    		Error error=new Error(5,"UnAuthorized User");
    		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
    	}
    	
    	Long userid=(Long)session.getAttribute("userid");
    	UserDetails user=userDAO.getUserById(userid);
    	user.setStatus("false");
    	userDAO.update(user);
    	session.removeAttribute("username");
    	session.removeAttribute("userid");
    	session.invalidate();
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
