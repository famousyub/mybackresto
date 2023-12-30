package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omnia.app.entity.RtingRecipe;

@Repository
public interface RatingRepository extends JpaRepository<RtingRecipe, Long> {
@Query("select r from RtingRecipe r  where r.recipeId =?1")

List<RtingRecipe> getRecipeRating(Long recid);
}
