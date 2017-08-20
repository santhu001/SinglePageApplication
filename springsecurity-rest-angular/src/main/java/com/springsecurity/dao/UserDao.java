package com.springsecurity.dao;

import java.util.List;

import com.springsecurity.model.User;

public interface UserDao {
	void addUser(User user);
	void editUser(User user);
	void deleteUser(int userid);
	User findUser(int userid);
	User findUserByName(String username);
	List<User> getAllUsers();
}
