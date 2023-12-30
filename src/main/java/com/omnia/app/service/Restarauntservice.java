package com.omnia.app.service;

import java.util.Collection;
import java.util.List;

import com.omnia.app.entity.RestoTableRecipesResponse;
import com.omnia.app.model.Restaurant;
import com.omnia.app.model.TableResto;
import com.omnia.app.response.RestoResponse;

public interface Restarauntservice {
	
	public Restaurant  createResto(Restaurant res , Long companyId);
	
	public List<Restaurant>  getAllResto(Long companyId);
	
	
	
	public List<RestoResponse>  getRestoswitcher(Long companyId);
	public RestoResponse getRestoById(Long compId , Long restoId);
	
	public RestoTableRecipesResponse  getRestoBytableId(Long compid, Long restoId, Long tableId);
	
    public  List<RestoTableRecipesResponse> getRestoTablerecipes(Long compid, Long restoId, Long tableId);
    
	public  Boolean isTableAvaible(TableResto resto);
	
	public List<TableResto> getAvaibleTable(Long restoId);
	public Collection<TableResto>  getRestoTableAvaible(Long restoId);
	
	
	  public  List<RestoTableRecipesResponse> getalltableinresto(Long comId);
	  
	
	

}
