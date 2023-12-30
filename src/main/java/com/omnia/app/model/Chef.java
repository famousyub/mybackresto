package com.omnia.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chef")
public class Chef  extends DateAudit{
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chef_id;
	
	 String chef_name;
	 byte[] description;
	 String phone;
	 String email;
	 
	 private String salt;

	public Long getChef_id() {
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Chef(Long chef_id, String chef_name, byte[] description, String phone, String email, String salt) {
		super();
		this.chef_id = chef_id;
		this.chef_name = chef_name;
		this.description = description;
		this.phone = phone;
		this.email = email;
		this.salt = salt;
	}

	public Chef() {
		super();
	}
	 
	 
	 
	 

}
