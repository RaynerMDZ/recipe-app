package com.raynermdz.controllers;

import com.raynermdz.commands.IngredientCommand;
import com.raynermdz.services.IngredientService;
import com.raynermdz.services.RecipeService;
import com.raynermdz.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

  private final RecipeService recipeService;
  private final IngredientService ingredientService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(RecipeService recipeService,
                              IngredientService ingredientService,
                              UnitOfMeasureService unitOfMeasureService) {
    this.recipeService = recipeService;
    this.ingredientService = ingredientService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @GetMapping
  @RequestMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {

    log.debug("Getting ingredient list for recipe id: " + recipeId);

    // Use command object to avoid lazy load errors in thymeleaf.
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
    return "recipe/ingredients/list";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
  public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
    model.addAttribute("ingredient",
            ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
    return "recipe/ingredients/show";
  }

  @GetMapping
  @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
  public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
    model.addAttribute("ingredient",
            ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredient/ingredientform";
  }

  @PostMapping
  @RequestMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand command) {

    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    log.debug("Saved recipe id: " + savedCommand.getRecipeId());
    log.debug("Saved ingredient id: " + savedCommand.getId());

    return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/";
  }
}
