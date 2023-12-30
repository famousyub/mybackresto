package com.omnia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.config.FileStorageProperties;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = { AppApplication.class, Jsr310JpaConverters.class })
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class AppApplication  extends SpringBootServletInitializer  {
	
	
	
	 @Override
	 
	    // Configuring method has to be overridden
	    protected SpringApplicationBuilder
	    configure(SpringApplicationBuilder application)
	    {
	        return application.sources(
	        		AppApplication.class);
	    }

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
	
	
	
	
	@RestController
	@CrossOrigin("*")
	class GreetingController {
		
		@RequestMapping("/hello")
		public ResponseEntity<?> getMe()
		{
			return ResponseEntity.ok().body("hello there");
		}
		
	    
	    @RequestMapping("/hello/{name}")
	    String hello(@PathVariable String name) {
	        return "Hello, " + name + "!";
	    }
	}

}
