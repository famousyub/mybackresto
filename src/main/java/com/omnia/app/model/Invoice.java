package com.omnia.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
public class Invoice extends DateAudit {
	
	



	    @Id
	    @GeneratedValue
	    private Long id;
	    private String name;
	    private String location;
	    private Double amount;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Invoice(Long id, String name, String location, Double amount) {
			super();
			this.id = id;
			this.name = name;
			this.location = location;
			this.amount = amount;
		}
		public Invoice() {
			super();
		}
	    
	    
	

}
