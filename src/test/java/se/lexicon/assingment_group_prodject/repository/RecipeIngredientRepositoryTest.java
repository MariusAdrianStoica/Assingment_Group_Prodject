package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.Ingredient;
import se.lexicon.assingment_group_prodject.entity.Measurement;
import se.lexicon.assingment_group_prodject.entity.RecipeIngredient;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeIngredientRepositoryTest{

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Test
    public void test_add(){

        Ingredient ingredientDataTest = new Ingredient("Sugar");
        Ingredient createdIngredientTest = ingredientRepository.save(ingredientDataTest);

        RecipeIngredient recipeIngredientData = new RecipeIngredient(createdIngredientTest, 1, Measurement.HG);
        RecipeIngredient createdRecipeIngredient = recipeIngredientRepository.save(recipeIngredientData);

        assertNotNull(createdRecipeIngredient);
        assertNotNull(createdRecipeIngredient.getIngredient());
        assertNull(createdRecipeIngredient.getRecipe());



    }
}
