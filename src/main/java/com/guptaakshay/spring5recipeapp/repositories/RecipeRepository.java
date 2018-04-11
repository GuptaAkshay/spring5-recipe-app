package com.guptaakshay.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.guptaakshay.spring5recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
