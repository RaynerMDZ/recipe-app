package com.raynermdz.converters;

import com.raynermdz.commands.IngredientCommand;
import com.raynermdz.models.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

  private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

  public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
    this.uomConverter = uomConverter;
  }

  @Synchronized
  @Nullable
  @Override
  public IngredientCommand convert(Ingredient source) {
    if (source == null) return null;

    final IngredientCommand command = new IngredientCommand();
    command.setId(source.getId());

    if (source.getRecipe() != null) {
      command.setRecipeId(source.getRecipe().getId());
    }

    command.setAmount(source.getAmount());
    command.setDescription(source.getDescription());
    command.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));

    return command;
  }
}
