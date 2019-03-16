package com.raynermdz.controllers;

import com.raynermdz.Models.Category;
import com.raynermdz.Models.UnitOfMeasure;
import com.raynermdz.repositories.CategoryRepository;
import com.raynermdz.repositories.UnitOfMeasureRepository;
import com.raynermdz.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

  private final RecipeService recipeService;

  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping({"", "/", "/index"})
  public String getIndexPage(Model model) {

    log.debug("Getting index page.");

    model.addAttribute("recipes", recipeService.getRecipes());

    return "index";
  }
}
