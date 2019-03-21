package com.raynermdz.services;

import com.raynermdz.models.Recipe;
import com.raynermdz.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

  RecipeServiceImpl service;

  @Mock
  RecipeRepository repository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    service = new RecipeServiceImpl(repository);
  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(repository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = service.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(repository, times(1)).findById(anyLong());
    verify(repository, never()).findAll();
  }

  @Test
  public void getRecipes() throws Exception {

    Recipe recipe = new Recipe();

    HashSet<Recipe> recipesData = new HashSet<>();

    recipesData.add(recipe);

    when(repository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = service.getRecipes();

    assertEquals(recipes.size(), 1);
    verify(repository, times(1)).findAll();
  }
}