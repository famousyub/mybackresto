package com.omnia.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

@CrossOrigin(origins = "*")

public class MyAppController {
	
	
	@GetMapping("/me")
	
	public ResponseEntity<?> getMe()
	{
		
		return ResponseEntity.ok().body("ok");
	}

}
