package com.raynermdz.services;

import com.raynermdz.Models.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {

  Set<Recipe> getRecipes();
}
