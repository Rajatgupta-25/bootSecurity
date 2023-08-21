package com.boot.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.security.entities.User;

@Service
public class UserService {
	
	List<User> list = new ArrayList<>();
	
	public UserService() {
		list.add(new User("abc", "abc@gmail.com", "1234"));
		list.add(new User("xyz", "xyz@gmail.com", "1234"));
	}
	
	public List<User> getAllUser(){
		return list;
	}
	
	public User getUser(String userName) {
		return list.stream().filter(user->user.getUserName().equals(userName)).findAny().orElse(null);
	}
	
	public User addUser(User user) {
		list.add(user);
		return user;
	}
	
}
