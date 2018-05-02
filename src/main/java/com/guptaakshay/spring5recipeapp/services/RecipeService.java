package com.guptaakshay.spring5recipeapp.services;

import java.util.Set;

import com.guptaakshay.spring5recipeapp.commands.RecipeCommand;
import com.guptaakshay.spring5recipeapp.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> getRecipes();

	public Recipe findById(long anyLong);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand command);
}
