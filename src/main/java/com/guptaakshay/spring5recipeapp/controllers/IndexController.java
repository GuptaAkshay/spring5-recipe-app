package com.guptaakshay.spring5recipeapp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guptaakshay.spring5recipeapp.domain.Category;
import com.guptaakshay.spring5recipeapp.domain.UnitOfMeasure;
import com.guptaakshay.spring5recipeapp.repositories.CategoryRepository;
import com.guptaakshay.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.guptaakshay.spring5recipeapp.services.RecipeService;

@Controller
public class IndexController {
	
	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}
