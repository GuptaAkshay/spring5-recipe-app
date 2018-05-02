package com.guptaakshay.spring5recipeapp.services;

import static org.junit.Assert.assertEquals;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.guptaakshay.spring5recipeapp.commands.RecipeCommand;
import com.guptaakshay.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.guptaakshay.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.guptaakshay.spring5recipeapp.domain.Recipe;
import com.guptaakshay.spring5recipeapp.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

	public static final String NEW_DESCRIPTION = "New Description";
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Transactional
	@Test
	public void testSaveOfDecription() throws Exception{
		
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);
		
		testRecipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand savedRecipeCommand  =recipeService.saveRecipeCommand(testRecipeCommand);
		
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
		assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}
}
