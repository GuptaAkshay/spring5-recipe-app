package com.guptaakshay.spring5recipeapp.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.guptaakshay.spring5recipeapp.domain.Recipe;
import com.guptaakshay.spring5recipeapp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}


	@Override
	public Set<Recipe> getRecipes() {
		log.info("Recipe Service Implementation");
		Set<Recipe> recipes = new HashSet<Recipe>();
		
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		return recipes;
	}

}
