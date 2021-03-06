package com.guptaakshay.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guptaakshay.spring5recipeapp.services.RecipeService;

@Controller
public class RecipeController {

	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		// TODO Auto-generated constructor stub
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		
		return "recipe/show";
	}
}
