package com.raynermdz.services;

import com.raynermdz.Models.Recipe;
import com.raynermdz.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

  private RecipeServiceImpl recipeServiceImpl;

  @Mock
  RecipeRepository recipeRepository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
  }

  @Test
  public void getRecipes() throws Exception {

    Recipe recipe = new Recipe();

    HashSet<Recipe> recipesData = new HashSet<>();

    recipesData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeServiceImpl.getRecipes();

    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
  }
}