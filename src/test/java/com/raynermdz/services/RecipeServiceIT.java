package com.raynermdz.services;

import com.raynermdz.commands.RecipeCommand;
import com.raynermdz.converters.RecipeCommandToRecipe;
import com.raynermdz.converters.RecipeToRecipeCommand;
import com.raynermdz.models.Recipe;
import com.raynermdz.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

  private static final String NEW_DESCRIPTION = "New Description";

  @Autowired
  RecipeService service;

  @Autowired
  RecipeRepository repository;

  @Autowired
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Autowired
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Test
  @Transactional
  public void testSaveOfDescription() throws Exception {

    // Given
    Iterable<Recipe> recipes = repository.findAll();
    Recipe testRecipe = recipes.iterator().next();
    RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

    // When
    testRecipeCommand.setDescription(NEW_DESCRIPTION);
    RecipeCommand savedRecipeCommand = service.saveRecipeCommand(testRecipeCommand);

    // Then
    assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
    assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
    assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
    assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
  }
}
