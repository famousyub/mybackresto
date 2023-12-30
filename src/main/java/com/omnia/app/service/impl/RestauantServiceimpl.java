package com.omnia.app.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.entity.RestoTableRecipesResponse;
import com.omnia.app.enums.TableStatus;
import com.omnia.app.model.Company;
import com.omnia.app.model.Restaurant;
import com.omnia.app.model.TableResto;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RestaurantRepository;
import com.omnia.app.repository.TableRestoRepository;
import com.omnia.app.response.RestoResponse;
import com.omnia.app.service.Restarauntservice;
@Service
public class RestauantServiceimpl implements Restarauntservice {
	
	@Autowired
	CompanyRepository comprepo;
	
	@Autowired 
	RestaurantRepository  resrep;

	@Autowired 
	TableRestoRepository tblrepo;
	
	@Override
	public Restaurant createResto(Restaurant res, Long companyId) {
		// TODO Auto-generated method stub
		
		Company com = comprepo.getOne(companyId);
		System.out.print(com);
		
		if(!com.equals(null)) {
			
			 res.setCompany(com);
			 
			 resrep.save(res);
			 
			 
			 
			 return res;
		}
		
		return new Restaurant();
	}

	@Override
	public List<Restaurant> getAllResto(Long companyId) {
		// TODO Auto-generated method stub
		return resrep.getListRestobyCompanyId(companyId);
	}

	@Override
	public List<RestoResponse> getRestoswitcher(Long companyId) {
		// TODO Auto-generated method stub
		List<Restaurant> fg = resrep.findAll();
		List<RestoResponse> reres =new ArrayList<>() ;
		
		for(Restaurant r_ : fg) {
			
			RestoResponse _ui = new RestoResponse();
			
			_ui.setResto(r_);
			_ui.setRecipes(r_.getRecipes());
			
			reres.add(_ui);
			
		}
		
		return reres;
		
	}

	@Override
	public RestoResponse getRestoById(Long compId, Long restoId) {
		// TODO Auto-generated method stub
		RestoResponse t = new RestoResponse();
		
		List<RestoResponse> restos = this.getRestoswitcher(compId);
		for (RestoResponse restoResponse : restos) {
			
			if(restoResponse.getResto().getId()==restoId) {
				 t = restoResponse ;
				 return t;
			}
			
		}
		
		return t ;
	}

	@Override
	public List<RestoTableRecipesResponse> getRestoTablerecipes(Long compid, Long restoId, Long tableId) {
		// TODO Auto-generated method stub
		
		
		
		List<Restaurant> fg = resrep.findAll();
		List<RestoTableRecipesResponse> reres =new ArrayList<>() ;
		
		for(Restaurant r_ : fg) {
			
			RestoTableRecipesResponse _ui = new RestoTableRecipesResponse();
			
			_ui.setResto(r_);
			_ui.setRecipes(r_.getRecipes());
			_ui.setTables(this.getRestoTableAvaible(r_.getId()));
			
			reres.add(_ui);
			
		}
		
		return reres;
		
		
	}

	@Override
	public RestoTableRecipesResponse getRestoBytableId(Long compid, Long restoId, Long tableId) {
		RestoTableRecipesResponse t = new RestoTableRecipesResponse();
		
		List<RestoTableRecipesResponse> restos = this.getRestoTablerecipes(compid,restoId,tableId);
		for (RestoTableRecipesResponse restoResponse : restos) {
			
			if(restoResponse.getResto().getId()==restoId) {
				 t = restoResponse ;
				 return t;
			}
			
		}
		
		return t ;
	}

	@Override
	public Boolean isTableAvaible(TableResto resto) {
		
		
		return resto.getTableavaible().equals(TableStatus.AVAIBLE)==true ? true:false;
	}

	@Override
	public List<TableResto> getAvaibleTable(Long restoId) {
		
		return tblrepo.getTableInResturant(restoId).stream()
	            .filter(this::isTableAvaible)
	            .collect(Collectors.toList());
	}

	@Override
	public Collection<TableResto> getRestoTableAvaible(Long restoId) {
		
		return tblrepo.getTableInResturant(restoId).stream()
	            .filter(this::isTableAvaible)
	            .collect(Collectors.toList());
		
	}

	@Override
	public List<RestoTableRecipesResponse> getalltableinresto(Long comId) {
		List<Restaurant> fg = resrep.findAll();
		List<RestoTableRecipesResponse> reres =new ArrayList<>() ;
		
		for(Restaurant r_ : fg) {
			
			RestoTableRecipesResponse _ui = new RestoTableRecipesResponse();
			
			_ui.setResto(r_);
			_ui.setRecipes(r_.getRecipes());
			_ui.setTables(r_.getTablesresto());
			
			reres.add(_ui);
			
		}
		
		return reres;
	}
	

}
