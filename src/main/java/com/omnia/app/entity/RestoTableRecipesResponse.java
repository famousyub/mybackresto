package com.omnia.app.entity;

import java.util.Collection;
import java.util.List;

import com.omnia.app.model.Recipe;
import com.omnia.app.model.Restaurant;
import com.omnia.app.model.TableResto;

public class RestoTableRecipesResponse {
	
	
private Restaurant  resto ;
	
	private List<Recipe> recipes ;
	
	
	private Collection<TableResto> tables;


	public Restaurant getResto() {
		return resto;
	}


	public void setResto(Restaurant resto) {
		this.resto = resto;
	}


	public List<Recipe> getRecipes() {
		return recipes;
	}


	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}


	public Collection<TableResto> getTables() {
		return tables;
	}


	public void setTables(Collection<TableResto> tables) {
		this.tables = tables;
	}


	public RestoTableRecipesResponse() {
		super();
	}
	
	
	
	
	

}
