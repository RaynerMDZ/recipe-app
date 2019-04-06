package com.raynermdz.controllers;

import com.raynermdz.commands.IngredientCommand;
import com.raynermdz.commands.RecipeCommand;
import com.raynermdz.services.IngredientService;
import com.raynermdz.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

  @Mock
  private IngredientService ingredientService;

  @Mock
  private RecipeService recipeService;

  private IngredientController ingredientController;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    ingredientController = new IngredientController(recipeService, ingredientService);
    mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    // Given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    // When
    mockMvc.perform(get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredients/list"))
            .andExpect(model().attributeExists("recipe"));

    //Then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  public void testShowIngredients() throws Exception {
    // Given
    IngredientCommand ingredientCommand = new IngredientCommand();

    // When
    when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

    // Then
    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredients/show"))
            .andExpect(model().attributeExists("ingredient"));
  }
}