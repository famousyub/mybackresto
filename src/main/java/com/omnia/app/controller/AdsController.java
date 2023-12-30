package com.omnia.app.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omnia.app.model.Ads;
import com.omnia.app.model.Company;
import com.omnia.app.repository.UserRepository;
import com.omnia.app.security.CurrentUser;
import com.omnia.app.security.UserPrincipal;
import com.omnia.app.service.IAdsService;
@RestController
@RequestMapping("/api/ads")
@CrossOrigin("*")
public class AdsController {

	@Autowired
	IAdsService iadsservice ;
	@Autowired 
	private UserRepository userRepository;
	
private static final Logger logger = LoggerFactory.getLogger(AdsController.class);
	
	private byte[] img ;
	
	
	@PostMapping("/upload")
	public String  uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		
		logger.info("upload image for  products");
		
		if(file.getSize()> 1024*1024*1) {
			return String.format("error  happednde file size too large %s",Long.toString(file.getSize())); 
		}
		this.img = file.getBytes();
		
		return "file successfully upload";
	}
	
	@GetMapping("/listads")
	List<Ads>  getAllArea(){
		return  iadsservice.getAllAds();
	}
	
	
	@PostMapping("/addadscompany")
	public ResponseEntity<?> createAdsence(@CurrentUser UserPrincipal currentUser,@RequestBody Ads ad)
	{
		if(this.img != null) {
			ad.setAsset(img);
		}
		Company company = userRepository.getOne(currentUser.getId()).getCompany();
		
		if(iadsservice.addAds(company.getId(),ad)==true) {
			return ResponseEntity.ok().body(ad);
		}
		return ResponseEntity.badRequest().body(ad);
		
	}
	
	@PostMapping("/addads/company/{companyId}")
	boolean addArea(@PathVariable(value = "companyId") Long id,@RequestBody Ads ar) {
		
		if(this.img != null) {
			ar.setAsset(img);
		}
		return iadsservice.addAds(id,ar);
		
		
	}
	@GetMapping("/adsdetail/{id}")
	Ads getAreaDEtails(@PathVariable Long id) {
		
		  return iadsservice.getAdsDetails(id);
		
	}
	
	
	
}
