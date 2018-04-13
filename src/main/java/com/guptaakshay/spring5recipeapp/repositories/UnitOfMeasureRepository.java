package com.guptaakshay.spring5recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.guptaakshay.spring5recipeapp.domain.UnitOfMeasure;


public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

	Optional<UnitOfMeasure> findByUom(String uom);
}
