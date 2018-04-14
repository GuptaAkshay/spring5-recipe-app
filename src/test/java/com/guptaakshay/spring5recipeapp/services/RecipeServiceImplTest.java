package com.guptaakshay.spring5recipeapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.guptaakshay.spring5recipeapp.domain.Recipe;
import com.guptaakshay.spring5recipeapp.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeServiceImpl;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
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
