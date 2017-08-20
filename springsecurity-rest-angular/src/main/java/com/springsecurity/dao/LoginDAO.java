package com.springsecurity.dao;

import java.io.Serializable;

import com.springsecurity.model.MyUser;

public interface LoginDAO {
	MyUser login(String username,String password);
	Serializable register(MyUser user);
}
