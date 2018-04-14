package com.guptaakshay.spring5recipeapp.controllers;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.guptaakshay.spring5recipeapp.domain.Recipe;
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
	public void testMockMvc() throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.view().name("index"));
		
	}
	
	@Test
	public void getIndexPage() {
		//given 
		Set<Recipe> recipes = new HashSet<Recipe>();
		recipes.add(new Recipe());
		
		Recipe recipe = new Recipe();
		recipe.setId(4L);
		recipes.add(recipe);
		
		Mockito.when(recipeService.getRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor =  ArgumentCaptor.forClass(Set.class);
		
		//when
		String viewName = indexController.getIndexPage(model);
		
		//then
		assertEquals("index", viewName);
	
		Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
		Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setController = argumentCaptor.getValue();
		assertEquals(2, setController.size());
		
	}
}
