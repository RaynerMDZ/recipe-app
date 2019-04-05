package com.raynermdz.services;

import com.raynermdz.commands.RecipeCommand;
import com.raynermdz.models.Recipe;

import java.util.Set;

public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long id);

  RecipeCommand findCommandById(Long id);

  RecipeCommand saveRecipeCommand(RecipeCommand command);

  void deleteById(Long id);
}
