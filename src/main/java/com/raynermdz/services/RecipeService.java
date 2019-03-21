package com.raynermdz.services;

import com.raynermdz.models.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {

  Set<Recipe> getRecipes();

  Recipe findById(Long id);
}
