package com.omnia.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.omnia.app.entity.UserExcelExporter;
import com.omnia.app.model.Employee;
import com.omnia.app.model.Recipe;
import com.omnia.app.repository.RecipeRepository;
import com.omnia.app.repository.UserRepository;


@Controller
public class ExceluserController {
	
	 @Autowired
	    private RecipeRepository service;
	     
	     
	    @GetMapping("/users/export/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Recipe> listUsers = service.findAll();
	         
	        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
	         
	        excelExporter.export(response);    
	    }  

}
