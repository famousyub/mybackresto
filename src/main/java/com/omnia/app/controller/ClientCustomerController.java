package com.omnia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.omnia.app.entity.Greeting;
import com.omnia.app.entity.HelloMessage;
import com.omnia.app.entity.RestoTableRecipesResponse;
import com.omnia.app.model.Ads;
import com.omnia.app.model.Area;
import com.omnia.app.model.Company;
import com.omnia.app.model.Order;
import com.omnia.app.model.OrderProduct;
import com.omnia.app.model.OrderStatus;
import com.omnia.app.model.Recipe;
import com.omnia.app.model.TableResto;
import com.omnia.app.payload.CompanyListResponse;
import com.omnia.app.payload.OrderProductDto;
import com.omnia.app.payload.RatingPayload;
import com.omnia.app.repository.AdsRepository;
import com.omnia.app.repository.AreaRepository;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RecipeRepository;
import com.omnia.app.repository.RestaurantRepository;
import com.omnia.app.response.RestoResponse;
import com.omnia.app.service.DeviceuserService;
import com.omnia.app.service.EmailService;
import com.omnia.app.service.IAdsService;
import com.omnia.app.service.IAreaService;
import com.omnia.app.service.MyRecipeService;
import com.omnia.app.service.OrderProductService;
import com.omnia.app.service.OrderService;
import com.omnia.app.service.RatingService;
import com.omnia.app.service.RecipeService;
import com.omnia.app.service.Restarauntservice;
import com.omnia.app.service.TableRestoService;
import com.omnia.app.util.CompanyMapper;
import com.omnia.app.util.EmailDetails;
import com.twilio.Twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class ClientCustomerController {
	
	
	
	
	
	private  static  final String TWILIO_ACCOUNT_SID="AC40f6fcd25a141d1818196c5fcd8bd598";
	private  static  final String TWILIO_AUTH_TOKEN ="49c1bd44d268caf9778590dcc5930532";

	 public static final String TWILIO_NUMBER = "+19896757856";

	
	
	 public static class OrderForm {

	        private List<OrderProductDto> productOrders;

	        public List<OrderProductDto> getProductOrders() {
	            return productOrders;
	        }

	        public void setProductOrders(List<OrderProductDto> productOrders) {
	            this.productOrders = productOrders;
	        }
	    }
	
	 
	 
	 
	 @Autowired
	 DeviceuserService deviceservice;
	 
	 
	@Autowired
	Restarauntservice resserv;
	
	@Autowired
	CompanyRepository comorepo;
	
	@Autowired 
	RatingService rateservice ;
	
	
	
	@Autowired
	IAreaService iareaservice ;
	
	@Autowired
	AreaRepository arepo;
	
	
	
	
	
	
	@Autowired
	OrderService orderService;

	@Autowired
	    OrderProductService orderProductService;
	
	
	@Autowired
	MyRecipeService reserv;
	
	
	@Autowired
	RecipeRepository reciperepo;
	
	@Autowired
	TableRestoService tblserv;
	@Autowired 
	private RestaurantRepository restorepo;
	
	
	@Autowired
	private AdsRepository adsrepo ;
	
	
	
	
	@Autowired
	IAdsService iadsservice ;
	
	
	@GetMapping("/recipes")
	public ResponseEntity<?>  getAllRecipes ()
	{
		
		List<Recipe> recs = reciperepo.findAll();
		return ResponseEntity.ok().body(recs);
	}
	
	
	@GetMapping("/recipe/{name}")
	public ResponseEntity<?> hrecipebyname(@PathVariable("name") String name, @RequestParam("price") float price )
	{
		 Recipe r = new Recipe();
		List<Recipe> recs = reciperepo.findAll();
		if(price >0) {
			List<Recipe> r_ = recs
					  .stream()
					  .filter(c -> c.getPrice() < price && c.getName().startsWith(name))
					  .collect(Collectors.toList());
			
			
			return ResponseEntity.ok().body(r_);
			
		}
		else {
			List<Recipe> r_ = recs
					  .stream()
					  .filter(c ->  c.getName().startsWith(name))
					  .collect(Collectors.toList());
			
			
			return ResponseEntity.ok().body(r_);
		}
		
		
//		recs.forEach(rex->{
//			  if(rex.getName().contains(name)) {
//				 
//			  }
//		});
		
		
	}
	@GetMapping("/lesads")
	ResponseEntity<?> getListAds()
	{
		
		 List<Ads> allads = adsrepo.findAll();
		 
		 return ResponseEntity.ok().body(allads);
	}
	
	@GetMapping("/listads")
	List<Ads>  getads(){
		return  iadsservice.getAllAds();
	}
	

	@GetMapping("/adsdetail/{id}")
	Ads getAreaDEtails(@PathVariable Long id) {
		
		  return iadsservice.getAdsDetails(id);
		
	}
	
	
	@MessageMapping("/hello")
	  @SendTo("/topic/greetings")
	  public Greeting greeting(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	  }
	@GetMapping("/allrestoId")
	public ResponseEntity<?>  getAllRestoById()
	{
		
		List<Long>  allId = restorepo.getRestoId();
		
		return ResponseEntity.ok().body(allId);
	}
	
	
	@PostMapping("/psorder/{tableId}")
	 public ResponseEntity<Order> create(@RequestBody OrderForm form,@PathVariable("tableId") Long tableId) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        //validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);
        

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, reserv.getProrecipe(dto
              .getProduct()
              .getId()),
            		
            dto.getQuantity(),tableId) ));
        }
        

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
	
	
	
	@PostMapping("/psorder")
	 public ResponseEntity<Order> createorder(@RequestBody OrderForm form) {
       List<OrderProductDto> formDtos = form.getProductOrders();
       //validateProductsExistence(formDtos);
       Order order = new Order();
       order.setStatus(OrderStatus.PAID.name());
       order = this.orderService.create(order);

       List<OrderProduct> orderProducts = new ArrayList<>();
       for (OrderProductDto dto : formDtos) {
           orderProducts.add(orderProductService.create(new OrderProduct(order, reserv.getProrecipe(dto
             .getProduct()
             .getId()),
           		
           dto.getQuantity())));
       }

       order.setOrderProducts(orderProducts);

       this.orderService.update(order);

       String uri = ServletUriComponentsBuilder
         .fromCurrentServletMapping()
         .path("/orders/{id}")
         .buildAndExpand(order.getId())
         .toString();
       HttpHeaders headers = new HttpHeaders();
       headers.add("Location", uri);

       return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
   }	
	
	
	@GetMapping("/companies")
	
	public ResponseEntity<?>  getAllcmpanies(){
		
		 List<Company>  allcompanies = comorepo.findAll();
		 
			List<CompanyListResponse> companyListResponse = allcompanies.stream().map(company -> {
				// Employee employee = userRepository.findById(company.getUpdatedBy())
				// .orElseThrow(() -> new ResourceNotFoundException("User", "id",
				// company.getUpdatedBy()));
				return CompanyMapper.mapCompanyToCompanyListResponse(company);
			}).collect(Collectors.toList());

		
		 return ResponseEntity.ok().body(companyListResponse);
	}
	@GetMapping("/all/{compId}")
	public ResponseEntity<?>  getRestoAll(@PathVariable("compId") Long comId){
		List<RestoResponse>  allresto =  resserv.getRestoswitcher(comId);
		
		return  ResponseEntity.ok().body(allresto);
		
		
	}
	
	@GetMapping("/allfoods/{compId}")
	public ResponseEntity<?>  getRestoAllfoodwithtables(@PathVariable("compId") Long comId){
		List<RestoTableRecipesResponse>  allresto =  resserv.getalltableinresto(comId);
		
		return  ResponseEntity.ok().body(allresto);
		
		
	}
	
	@GetMapping("/allresto/{compId}/{RestoId}")
	public ResponseEntity<?>  getRestoByid(@PathVariable("compId") Long comId , @PathVariable("RestoId") Long RestoId){
		RestoResponse rt = resserv.getRestoById(comId, RestoId);
		
		return ResponseEntity.ok().body(rt);
		
		
		
		
	}
	
	
	@GetMapping(path = "/v1/getqrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public BufferedImage generateQRCodeImage(@RequestParam String url) throws Exception {
	//QRcode generator logic
	QRCodeWriter qrCodeWriter = new QRCodeWriter();
	BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 250, 250);
	return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
	
	@PostMapping("/cli/{recId}")
	public ResponseEntity<?> incrementRating (@RequestBody RatingPayload numrating ,@PathVariable("recId") Long recid)
	{
		
	Integer t = 	rateservice.incrementRating(numrating.getNumberStar(),recid);
	return ResponseEntity.ok().body(t);
	}
	
	
	@GetMapping("/ret/{recId}")
	public ResponseEntity<?>  getRecipeRatings (@PathVariable("recId") Long recid)
	{
		
		Double dl = rateservice.Ratingfix(recid);
		return ResponseEntity.ok().body(dl);
	}
	
	@PutMapping("/meTable/{restoId}/{tableId}")
	public ResponseEntity<?> reservedTable(@PathVariable("restoId") Long restoId,@PathVariable("tableId") Long tableId )
	{
		TableResto tresto = tblserv.updateCurentTable(tableId, restoId);
		return ResponseEntity.ok().body(tresto);
	}
	
	@GetMapping("/alresto/{compId}/{ResttoId}/{TableId}")
	
	public ResponseEntity<?>  getRestTablesoByid(@PathVariable("compId") Long comId , @PathVariable("ResttoId") Long RestoId,@PathVariable("TableId") Long tableId){
		RestoTableRecipesResponse rt = resserv.getRestoBytableId(comId, RestoId, tableId);
		
		return ResponseEntity.ok().body(rt);
		
		
		
		
	}

	
	
	
	
@GetMapping("/listareasp/{compId}")
	
	public ResponseEntity<?> getAreaslisp(@PathVariable("compId") Long compId){
		
		List<Area> areas =arepo.getAreabyId(compId);
		
		
		
		return ResponseEntity.ok().body(areas);
		
		
		
	}



@Autowired private EmailService emailService;

// Sending a simple Email
@PostMapping("/sendMail")
public String
sendMail(@RequestBody EmailDetails details)
{
    String status
        = emailService.sendSimpleMail(details);

    return status;
}





@GetMapping(value = "/sendSMS/{tableId}")
public ResponseEntity<String> sendSMS(@PathVariable("tableId") Long tableId) {
	
	
	
	    

        Twilio.init(TWILIO_ACCOUNT_SID,TWILIO_AUTH_TOKEN);

        Message.creator(new PhoneNumber("+21629966019"),
                        new PhoneNumber(TWILIO_NUMBER), "new order detected from table" + tableId).create();

        return ResponseEntity.ok().body("Message sent successfully");
        		//new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
}














@GetMapping("/asendmail/{TableId}/{restiOid}")


public String
sendMailwithTableid(@PathVariable("TableId") Long tableid,@PathVariable("restiOid") Long restiOid)
{
	
	TableResto t = tblserv.getTable(restiOid, tableid);
	
	EmailDetails details=new EmailDetails();
	
	details.setMsgBody("table" +  tableid+"  "+ t.getTableclass() +"table Nomero "+t.getTablenumber() );
	details.setRecipient("ayoubjobs.2019@gmail.com");
	details.setAttachment(t.getTablename());
	details.setSubject("order servive for restauranrt   for  table  id" + tableid );
	
	
    String status
        = emailService.sendSimpleMail(details);

    return status;
}


// Sending email with attachment
@PostMapping("/sendMailWithAttachment")
public String sendMailWithAttachment(
    @RequestBody EmailDetails details)
{
    String status
        = emailService.sendMailWithAttachment(details);

    return status;
}

@GetMapping("/listarea")
List<Area>  getAllArea(){
	return  iareaservice.getAllArea();
}

@GetMapping("/cool-recipes")
public Collection<Recipe> coolCars() {
    return reciperepo.findAll().stream()
            .filter(this::isCool)
            .collect(Collectors.toList());
}

private boolean isCool(Recipe car) {
    return !car.getName().contains("spicy")&&
    		!car.getName().contains("chimique") &&
            !car.getName().contains("caramel") &&
            !car.getName().contains("choclate");
    		
           
}



	
	

}
