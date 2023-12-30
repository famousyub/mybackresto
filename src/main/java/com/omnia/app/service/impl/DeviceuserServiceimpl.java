package com.omnia.app.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Deviceuser;
import com.omnia.app.repository.DeviceuserRepository;
import com.omnia.app.service.DeviceuserService;
@Service
public class DeviceuserServiceimpl implements DeviceuserService {
	

	@Autowired
	private DeviceuserRepository deviceuserRepo;

	@Override
	public Deviceuser createDevice(Deviceuser dev) {
		dev.setDeviceuuid(UUID.randomUUID());
		
		 return deviceuserRepo.save(dev);
	}

	@Override
	public List<Deviceuser> getAllDevice() {
		
		return deviceuserRepo.findAll();
	}

	@Override
	public Deviceuser getOnedevice(Long id) {
		Deviceuser deuser = deviceuserRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

		return deuser;
	}

}
