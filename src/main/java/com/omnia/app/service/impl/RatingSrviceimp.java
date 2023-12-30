package com.omnia.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.entity.RtingRecipe;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Recipe;
import com.omnia.app.repository.RatingRepository;
import com.omnia.app.repository.RecipeRepository;
import com.omnia.app.service.RatingService;

@Service
public class RatingSrviceimp  implements RatingService {
	
	
	@Autowired
	private RatingRepository raterepo;
	
	@Autowired
	private RecipeRepository reciperepo ;
	

	@Override
	public Integer incrementRating(int n ,Long RecipeId) {
		
		Recipe rec = reciperepo.findById(RecipeId).orElseThrow(() -> new ResourceNotFoundException("Product not found"," with id",RecipeId));
		
		RtingRecipe recs = new RtingRecipe();
		recs.setRecipeId(rec.getId());
		
		//recs.incrementReciperating();
		recs.setNumberStar(n);
		recs.setCounterRating(0.0);
		raterepo.save(recs);
		
		return recs.getNumberStar();
	}

	@Override
	public Double Ratingfix(Long recipeId) {
		
		Double sum =0.0;
		List<RtingRecipe> lrec = raterepo.getRecipeRating(recipeId);
		int counter = 0;
		for (RtingRecipe rt : lrec) {
			   sum += rt.getNumberStar() ;
			   counter++;
		}
		
		sum =  sum  /counter;
		
		return  sum;
		
		
	}
	
	
	

}
