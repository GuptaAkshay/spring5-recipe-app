package com.guptaakshay.spring5recipeapp.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.guptaakshay.spring5recipeapp.services.RecipeService;

public class IndexControllerTest {
	
	@Mock
	RecipeService recipeService;

	@Mock
	Model model;
	
	IndexController indexController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		indexController = new IndexController(recipeService);
	}
	
	@Test
	public void getIndexPage() {
		
		String viewName = indexController.getIndexPage(model);
		
		assertEquals("index", viewName);
	
		Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
		Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), Mockito.anySet());
		
	}
}
