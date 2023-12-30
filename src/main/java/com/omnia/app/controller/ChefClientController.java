package com.omnia.app.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.exception.InvoiceNotFoundException;
import com.omnia.app.model.Chef;
import com.omnia.app.model.Invoice;
import com.omnia.app.model.Order;
import com.omnia.app.model.OrderProduct;
import com.omnia.app.repository.ChefRepository;
import com.omnia.app.repository.OrderProductRepository;
import com.omnia.app.repository.OrderRepository;
import com.omnia.app.response.Cheflist;
import com.omnia.app.response.OrderResponse;
import com.omnia.app.service.IInvoiceService;
import com.omnia.app.service.OrderService;
import com.omnia.app.util.GeneratePdfReport;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/chef")
public class ChefClientController {
	
	@Autowired
	OrderRepository orderRepo ;
	
	@Autowired
	OrderProductRepository orderprodRepo;
	
	@Autowired 
	private OrderService orderservice;
	
	
	@Autowired
	private ChefRepository chefrepo;
	
	
	
	
	
	
	
	@GetMapping("/not")
	public ResponseEntity<?> getNotify()
	{
		
		
		
		return ResponseEntity.ok().body("new order created");
		
	}
	
	@GetMapping("/allchefs")
	public ResponseEntity<?> getAllcheffs()
	{
		List<Chef> allchefs  =chefrepo.findAll();
		
		return ResponseEntity.ok().body(allchefs);
	}
	
	
	
	@GetMapping("/chefid/{chef_id}")
	public ResponseEntity<?> getAllcheffsbyId(@PathVariable("chef_id") Long chef_id)
	{
		Chef allchefs  =chefrepo.findById(chef_id).get();
		
		return ResponseEntity.ok().body(allchefs);
	}
	
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

		List<OrderProduct> ordpro = (List<OrderProduct>) orderprodRepo.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(ordpro);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	@RequestMapping(value = "/pdfreport/{OrderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> orderreport(@PathVariable("OrderId") Long OrderId) {

		//List<OrderProduct> ordpro = (List<OrderProduct>) orderprodRepo.findAll();
		List<OrderProduct> getallproducts = new ArrayList<>();
Order or = orderRepo.findById(OrderId).get();
		
		
		
		
        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(or.getOrderProducts());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	
	
   @DeleteMapping("/chef/del/{OrderId}")
   
   public ResponseEntity<?> deleteOrder(@PathVariable("OrderId") Long OrderId){
	   
	   
	   try {
		   
		   Order l = orderRepo.findById(OrderId).get();
		   
		   
		   l.setOrderProducts(new ArrayList<>());
		   
		   orderRepo.delete(l);
		   
		   return ResponseEntity.ok().body("deleted succesfully");
		   
	   }catch(Exception ex) {
		   return ResponseEntity.badRequest().body("something wrong  succesfully");
		   
	   }
	   
	   
   }
	

	
	@GetMapping("/cheff")
	public ResponseEntity<?>  getCheff()
	{
		
		  String chef = "ayubo";
		  
		  return ResponseEntity.ok().body(chef);
	}
	
	
	@GetMapping("/oredres")
	public ResponseEntity<?> getAllOrders()
	{
		List<Order> orders = (List<Order>) orderservice.getAllOrders();
		
		return ResponseEntity.ok().body(orders);
		
	}
	
	@GetMapping("/oredresl")
	public ResponseEntity<?> getAllOrdersrepo()
	{
		List<Order> orders =  (List<Order>) orderRepo.findAll();
		
		return ResponseEntity.ok().body(orders);
		
	}
	
	
	
	@GetMapping("/orderspricing/{orderId}")
	public ResponseEntity<?>  getOrderpriceByorderid(@PathVariable("orderId") Long orderid)
	{
		List<OrderProduct> orderproducts =(List<OrderProduct>) orderprodRepo.findAll();
		return ResponseEntity.ok().body(orderproducts);
	}
	
	
	@GetMapping("/clientorder/{OrderId}")
	public ResponseEntity<?> getOrdersclientById(@PathVariable("OrderId")Long OrderId)
	{
		//OrderProduct orderprod ;
		Order or = orderRepo.findById(OrderId).get();
		
		try {
			
			List<OrderProduct> getallproducts = new ArrayList<>();
			
			for (OrderProduct o : or.getOrderProducts()) {
				getallproducts.add(o);
				
			}
			 return ResponseEntity.ok().body(getallproducts);
		}catch(Exception ex) {
			  return ResponseEntity.ok().body(ex.toString());
		}
	
	}
	
	
	@GetMapping("/prodorder")
	public ResponseEntity<?> getPrdorders()
	{
		List<OrderProduct> orderproducts =(List<OrderProduct>) orderprodRepo.findAll();
		return ResponseEntity.ok().body(orderproducts);
	}
	
	
	@PreAuthorize("hasRole('ROLE_SCREEN_USER')")
	@GetMapping("/greet/user")
	public ResponseEntity<String> greetingUser() {
		return new ResponseEntity<String>("Welcome, you have USER role", HttpStatus.OK);
	}

	
}
