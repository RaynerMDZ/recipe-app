package com.raynermdz.services;

import com.raynermdz.commands.IngredientCommand;

public interface IngredientService {

  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
}
