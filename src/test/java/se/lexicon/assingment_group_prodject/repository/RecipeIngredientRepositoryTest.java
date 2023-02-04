package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.Ingredient;
import se.lexicon.assingment_group_prodject.entity.Measurement;
import se.lexicon.assingment_group_prodject.entity.RecipeIngredient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeIngredientRepositoryTest{

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;


    RecipeIngredient createdRecipeIngredient1;
    RecipeIngredient createdRecipeIngredient2;

    @BeforeEach
    public void setup(){
        Ingredient ingredientData1 = new Ingredient("Salt");
        Ingredient createdIngredient1 = ingredientRepository.save(ingredientData1);
        RecipeIngredient recipeIngredientData1 = new RecipeIngredient(createdIngredient1, 1, Measurement.TSP);
        createdRecipeIngredient1 = recipeIngredientRepository.save(recipeIngredientData1);


        Ingredient ingredientData2 = new Ingredient("Sugar");
        Ingredient createdIngredient2 = ingredientRepository.save(ingredientData2);
        RecipeIngredient recipeIngredientData2 = new RecipeIngredient(createdIngredient2, 2, Measurement.HG);
        createdRecipeIngredient2 = recipeIngredientRepository.save(recipeIngredientData2);

    }
    @Test
    public void test_add(){

        Ingredient ingredientDataTest = new Ingredient("milk");
        Ingredient createdIngredientTest = ingredientRepository.save(ingredientDataTest);

        RecipeIngredient recipeIngredientData = new RecipeIngredient(createdIngredientTest, 1, Measurement.HG);
        RecipeIngredient createdRecipeIngredient = recipeIngredientRepository.save(recipeIngredientData);

        assertNotNull(createdRecipeIngredient1);
        assertNotNull(createdRecipeIngredient1.getIngredient());
        assertNull(createdRecipeIngredient1.getRecipe());

    }

    @Test
    public void test_findByIngredient_IngredientName(){

        Optional<RecipeIngredient> found = recipeIngredientRepository.findByIngredient_IngredientName("Sugar");
        assertTrue(found.isPresent());

        RecipeIngredient actual = found.get();

        assertEquals(createdRecipeIngredient2, actual);

    }
}
