package com.raynermdz.models;

import com.raynermdz.enums.Difficulty;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;

  @Lob
  private String directions;

  @Lob
  private Byte[] image;

  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;

  // cascade = CascadeType.ALL means that when a recipe is deleted, the notes are deleted as well.
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Notes notes;

  @ManyToMany
  @JoinTable(
          name = "recipe_category",
          joinColumns = @JoinColumn(name = "recipe_id"),
          inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients = new HashSet<>();

  public void setNotes(Notes notes) {
    this.notes = notes;
    notes.setRecipe(this);
  }

  public Recipe addIngredient(Ingredient ingredient) {
    ingredient.setRecipe(this);
    this.ingredients.add(ingredient);
    return this;
  }
}