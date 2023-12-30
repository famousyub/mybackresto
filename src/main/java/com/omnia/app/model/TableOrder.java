package com.omnia.app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="tableorders")
public class TableOrder extends DateAudit {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    private String status;
    
    private Long tableId ;
    private Long RestoId;
    
    private Double pricing ;
    private Double total ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	public Long getRestoId() {
		return RestoId;
	}
	public void setRestoId(Long restoId) {
		RestoId = restoId;
	}
	public Double getPricing() {
		return pricing;
	}
	public void setPricing(Double pricing) {
		this.pricing = pricing;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public TableOrder() {
		super();
	}
    

}
