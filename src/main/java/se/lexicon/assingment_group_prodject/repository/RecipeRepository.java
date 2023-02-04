package se.lexicon.assingment_group_prodject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.assingment_group_prodject.entity.Ingredient;
import se.lexicon.assingment_group_prodject.entity.Recipe;
import se.lexicon.assingment_group_prodject.entity.RecipeIngredient;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,  Integer> {


    List<Recipe> findAllByRecipeNameContains(String recipeName);
    //todo:


    //List<Recipe> findAllByRecipeIngredientsContains(RecipeIngredient recipeIngredient);


    /*
    List<Recipe> findAllByRecipeNameAndRecipeIngredientsContains(String ingredientName);

     */
}
