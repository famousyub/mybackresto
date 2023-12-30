package com.omnia.app.response;

import java.io.Serializable;

public class Cheflist implements Serializable {
	
	
	 Long chef_id ;
	 String chef_name;
	 byte[] description;
	 String phone;
	 String email;
	 Long getChef_id() {
		return chef_id;
	}
	public void setChef_id(Long chef_id) {
		this.chef_id = chef_id;
	}
	public String getChef_name() {
		return chef_name;
	}
	public void setChef_name(String chef_name) {
		this.chef_name = chef_name;
	}
	public byte[] getDescription() {
		return description;
	}
	public void setDescription(byte[] description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
