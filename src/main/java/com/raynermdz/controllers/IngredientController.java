package com.raynermdz.controllers;

import com.raynermdz.commands.IngredientCommand;
import com.raynermdz.commands.RecipeCommand;
import com.raynermdz.commands.UnitOfMeasureCommand;
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

  private final IngredientService ingredientService;
  private final RecipeService recipeService;
  private final UnitOfMeasureService unitOfMeasureService;

  public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
    this.ingredientService = ingredientService;
    this.recipeService = recipeService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @GetMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model){
    log.debug("Getting ingredient list for recipe id: " + recipeId);

    // use command object to avoid lazy load errors in Thymeleaf.
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

    return "recipe/ingredients/list";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
  public String showRecipeIngredient(@PathVariable String recipeId,
                                     @PathVariable String id, Model model){
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
    return "recipe/ingredients/show";
  }

  @GetMapping("recipe/{recipeId}/ingredient/new")
  public String newIngredient(@PathVariable String recipeId, Model model){

    //make sure we have a good id value
    RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
    //todo raise exception if null

    //need to return back parent id for hidden form property
    IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setRecipeId(Long.valueOf(recipeId));
    model.addAttribute("ingredient", ingredientCommand);

    //init uom
    ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

    model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

    return "recipe/ingredients/ingredientform";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
  public String updateRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

    model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
    return "recipe/ingredients/ingredientform";
  }

  @PostMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute IngredientCommand command){
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    log.debug("saved recipe id:" + savedCommand.getRecipeId());
    log.debug("saved ingredient id:" + savedCommand.getId());

    return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
  public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {
    log.debug("Deleting ingredient id: " + id);
    ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
    return "redirect:/recipe/" + recipeId + "/ingredients";
  }
}
