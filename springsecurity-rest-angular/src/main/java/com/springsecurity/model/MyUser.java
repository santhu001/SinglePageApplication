package com.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="khub_user")
public class MyUser {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;
@Column(name="EMAIL_ADDRESS")
private String emailAddress;
private String name;
private String phone;
private String company;
private String country;
private String password;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getEmailAddress() {
	return emailAddress;
}
public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}

