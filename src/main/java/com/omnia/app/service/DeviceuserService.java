package com.omnia.app.service;

import java.util.List;


import com.omnia.app.model.Deviceuser;



public interface DeviceuserService {
	
	public Deviceuser createDevice(Deviceuser dev);
	
	public List<Deviceuser> getAllDevice();
	
	public Deviceuser getOnedevice(Long id);
	
	
	

}
