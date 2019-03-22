package com.raynermdz.services;

import com.raynermdz.commands.RecipeCommand;
import com.raynermdz.converters.RecipeCommandToRecipe;
import com.raynermdz.converters.RecipeToRecipeCommand;
import com.raynermdz.models.Recipe;
import com.raynermdz.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeRepository recipeRepository,
                           RecipeCommandToRecipe recipeCommandToRecipe,
                           RecipeToRecipeCommand recipeToRecipeCommand) {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes() {

    log.debug("I'm in the service");

    Set<Recipe> recipes = new HashSet<>();

    recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

    return recipes;
  }

  @Override
  public Recipe findById(Long id) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(id);

    if (!recipeOptional.isPresent()) {
      throw new RuntimeException("Recipe Not Found!");
    }

    return recipeOptional.get();
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand command) {

    // Takes recipe coming from the frontend.
    // Coverts that Command to a Model.
    Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

    // Saves the Model detachedRecipe via Repository.
    Recipe savedRecipe = recipeRepository.save(detachedRecipe);

    log.debug("Saved RecipeId: " + savedRecipe.getId());

    // Returns back the the Model Converted to a Command.
    return recipeToRecipeCommand.convert(savedRecipe);
  }
}
