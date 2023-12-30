package com.omnia.app.controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.model.Order;
import com.omnia.app.model.OrderProduct;
import com.omnia.app.model.Recipe;
import com.omnia.app.model.Restaurant;
import com.omnia.app.repository.OrderRepository;
import com.omnia.app.repository.RecipeRepository;
import com.omnia.app.repository.RestaurantRepository;
import com.omnia.app.service.RecipeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/data")
public class PingController {
	
	
	@Autowired
	private  RecipeRepository  reciprepo;
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private  RestaurantRepository retrpo;
	
	
	@Autowired
	OrderRepository orderRepo ;
	
	
	
	@GetMapping("/datarecipe")
	public ResponseEntity<?> getAllRecipes()
	{
		 List<Recipe> allrec = reciprepo.findAll();
		 
		 return ResponseEntity.ok().body(allrec);
	}
	
	@GetMapping("/dataresto")
	public ResponseEntity<?> getAllResto()
	{
		 List<Restaurant> allrec = retrpo.findAll();
		 
		  Map<String,Integer> restorecipescount =new HashMap<>() ;
		  for (Restaurant restaurant : allrec) {
			 
				  restorecipescount.put(Long.toString( restaurant.getId()), restaurant.getRecipes().size());
				
			
			
		}
		  
		  return ResponseEntity.ok().body(restorecipescount);
	}
	
	@GetMapping("/oredresl")
	public ResponseEntity<?> getAllOrdersrepo()
	{
		List<Order> orders =  (List<Order>) orderRepo.findAll();
		
		return ResponseEntity.ok().body(orders);
		
	}
	
	@GetMapping("/orederrecipecount")
	public ResponseEntity<?> getrecipeorders()
	{
		List<Order> orders =  (List<Order>) orderRepo.findAll();
		
		Map<String , Integer> recipescount =new HashMap<>();
		for (Order orderproduct: orders) {
			
			for (OrderProduct orpro : orderproduct.getOrderProducts()) {
			               
				recipescount.put(Long.toString( orpro.getProduct().getId()),orpro.getQuantity());
				
				
				
			}
		}
		
		return ResponseEntity.ok().body(recipescount);
		
	}
	

	
	@GetMapping("/ping")
	public ResponseEntity<?> getUsersIp(){
		
		HashMap<String, String> m =new HashMap<>();
		
		try {
			
			InetAddress ip = InetAddress.getLocalHost();
			String user_ip= ip.getCanonicalHostName();
			String con_ip = ip.getHostAddress();
			String con_adre =ip.getCanonicalHostName();
			m.put("ip", user_ip);
			m.put("con_ip", con_ip);
			m.put("con_adree", con_adre);
			
			return  ResponseEntity.ok().body(m);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.toString());
			
		}
		 
	}
	
	@GetMapping("/ping/v6")
	public ResponseEntity<?> getUsersIpv6(){
		
		HashMap<String, String> m =new HashMap<>();
		
		try {
			
			InetAddress ip = InetAddress.getLocalHost();
			String user_ip= ip.getCanonicalHostName();
			String con_ip = ip.getHostAddress();
			String con_adre =ip.getCanonicalHostName();
			m.put("ip", user_ip);
			m.put("con_ip", con_ip);
			m.put("con_adree", con_adre);
			
			return  ResponseEntity.ok().body(m);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.toString());
			
		}
		 
	}
}
