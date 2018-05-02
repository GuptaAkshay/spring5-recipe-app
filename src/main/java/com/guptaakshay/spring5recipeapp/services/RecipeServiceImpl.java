package com.guptaakshay.spring5recipeapp.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guptaakshay.spring5recipeapp.commands.RecipeCommand;
import com.guptaakshay.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.guptaakshay.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.guptaakshay.spring5recipeapp.domain.Recipe;
import com.guptaakshay.spring5recipeapp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;


	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}


	@Override
	public Set<Recipe> getRecipes() {
		log.info("Recipe Service Implementation");
		Set<Recipe> recipes = new HashSet<Recipe>();
		
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		return recipes;
	}


	@Override
	public Recipe findById(long anyLong) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepository.findById(anyLong);
		
		if(!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		return recipeOptional.get();
	}


	@Transactional
	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {

		Recipe detahedRecipe = recipeCommandToRecipe.convert(command);
		
		Recipe savedRecipe = recipeRepository.save(detahedRecipe);
		log.debug("Saved Recipe: " + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}

}
