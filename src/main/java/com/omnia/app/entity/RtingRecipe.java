package com.omnia.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.omnia.app.model.DateAudit;

@Entity
@Table(name="ratingrecipe")
public class RtingRecipe extends DateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Integer numberStar=1 ;
	
	private Long recipeId;
	
	
	private Double  counterRating ;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Integer getNumberStar() {
		return numberStar;
	}


	public void setNumberStar(Integer numberStar) {
		this.numberStar = numberStar;
	}


	public Long getRecipeId() {
		return recipeId;
	}


	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}


	public Double getCounterRating() {
		return counterRating;
	}


	public void setCounterRating(Double counterRating) {
		this.counterRating = counterRating;
	}


	public RtingRecipe() {
		super();
	}
	
	
	public Double counterGlobal(Integer n) {
		
		 return    (numberStar  / counterRating )  * n; 
	}
	
	
	public  Integer incrementReciperating() {
		 return this.numberStar ++ ;
	}
	
	

}
