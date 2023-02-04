package se.lexicon.assingment_group_prodject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.assingment_group_prodject.entity.Recipe;
import se.lexicon.assingment_group_prodject.entity.RecipeCategory;
import se.lexicon.assingment_group_prodject.entity.RecipeIngredient;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,  Integer> {


    /**Search for recipes where recipe name contains specified String.*/
    List<Recipe> findAllByRecipeNameContains(String recipeName);
    //todo:


    /**Search for all recipes that contains a specified ingredient name.
    e.g. potato, tomato, salt, etc */

    //List<Recipe> findAllByRecipeIngredientsContains(RecipeIngredient recipeIngredient);


    /**Search for all recipes that belong to a specific recipe category.
    e.g. Chicken, Vegan, Celebration, Weekend etc. */
   List<Recipe> findAllByCategoriesContains(RecipeCategory recipeCategory);


   /**  Search for all recipes that match one or more categories.
   //e.g. {”Spicy”,”Mexican”,”Weekend”}*/
   //List<Recipe> findAll



/*
    @Query("SELECT R FROM Recipe R WHERE R.categories = (SELECT RC FROM RecipeCategory RC WHERE RC.category =: ctg) ")
    List<Recipe> findAllRecipesByCategoryName(@Param("ctg") String category);

 */
}
