package com.guptaakshay.spring5recipeapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.guptaakshay.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.guptaakshay.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.guptaakshay.spring5recipeapp.domain.Recipe;
import com.guptaakshay.spring5recipeapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeServiceImpl;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}
	
	@Test
	public void getRecipeByIdTest() throws Exception{
	
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		Recipe recipeReturned = recipeServiceImpl.findById(1L);
		
		assertNotNull("Null recipe retuned", recipeReturned);
		
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
		
	}
	
	@Test
	public void getRecipes() throws Exception{
		
		Recipe recipe= new Recipe();
		
		HashSet<Recipe> recipeData = new HashSet<Recipe>();
		recipeData.add(recipe);
		
		when(recipeServiceImpl.getRecipes()).thenReturn(recipeData);
		
		Set<Recipe> recipes = recipeServiceImpl.getRecipes();
		
		assertEquals(recipes.size(), 1 );
		verify(recipeRepository, times(1)).findAll();
	}

}
