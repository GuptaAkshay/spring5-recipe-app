package com.guptaakshay.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import com.guptaakshay.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.guptaakshay.spring5recipeapp.domain.UnitOfMeasure;

import lombok.Synchronized;

public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

	@Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setUom(source.getDescription());
        return uom;
    }
}
