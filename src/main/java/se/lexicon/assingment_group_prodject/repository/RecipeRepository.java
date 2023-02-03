package se.lexicon.assingment_group_prodject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.assingment_group_prodject.entity.Ingredient;
import se.lexicon.assingment_group_prodject.entity.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {


    List<Recipe> findAllByRecipeNameContains(String recipeName);
    //todo:
    /*
    List<Recipe> findAllByRecipeIngredientsContains(String ingredientName);
    List<Recipe> findAllByRecipeNameAndRecipeIngredientsContains(String ingredientName);

     */
}
