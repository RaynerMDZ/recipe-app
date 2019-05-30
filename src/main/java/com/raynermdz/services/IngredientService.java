package com.raynermdz.services;

import com.raynermdz.commands.IngredientCommand;

public interface IngredientService {

  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
  IngredientCommand saveIngredientCommand(IngredientCommand command);
  void deleteById(Long recipeId, Long idToDelete);
}
