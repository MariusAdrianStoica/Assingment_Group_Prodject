package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.*;
import se.lexicon.assingment_group_prodject.exception.DataNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeRepositoryTest {


    @Autowired
    RecipeRepository testObject;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    RecipeInstructionRepository recipeInstructionRepository;

    Recipe createdRecipe1;
    Recipe createdRecipe2;

    List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    Set<RecipeCategory> recipeCategories = new HashSet<>();

    @BeforeEach
    public void setup() {

        Ingredient ingredientDataTest = new Ingredient("Sugar");
        Ingredient createdIngredientTest = ingredientRepository.save(ingredientDataTest);

        RecipeIngredient recipeIngredientData = new RecipeIngredient(createdIngredientTest, 1, Measurement.HG);
        RecipeIngredient createdRecipeIngredient = recipeIngredientRepository.save(recipeIngredientData);
        recipeIngredients.add(createdRecipeIngredient);

        RecipeCategory recipeCategory = new RecipeCategory("cakes");
        RecipeCategory createdrecipeCategory = recipeCategoryRepository.save(recipeCategory);
        recipeCategories.add(createdrecipeCategory);

        RecipeInstruction recipeInstruction1 = new RecipeInstruction("InstructionCake");

        Recipe recipeData1 = new Recipe("Cake", recipeInstruction1);

        createdRecipe1 = testObject.save(recipeData1);

        createdRecipe1.setRecipeIngredients(recipeIngredients);
        createdRecipe1.setCategories(recipeCategories);

        RecipeInstruction recipeInstruction2 = new RecipeInstruction("InstructionBread");
        Recipe recipeData2 = new Recipe("Bread", recipeInstruction2);
        createdRecipe2 = testObject.save(recipeData2);

        createdRecipe2.setRecipeIngredients(recipeIngredients);
        createdRecipe2.setCategories(recipeCategories);
    }


    @Test
    public void test_add() {

        RecipeInstruction recipeInstructionTest = new RecipeInstruction("InstructionTest");
        Recipe recipeDataTest = new Recipe("Test", recipeInstructionTest);
        Recipe createdRecipeTest = testObject.save(recipeDataTest);


        assertNotNull(createdRecipeTest);
        assertNotNull(createdRecipeTest.getId());
        assertNotNull(createdRecipeTest.getInstruction());

        assertEquals(0, createdRecipeTest.getRecipeIngredients().size());
        assertEquals(0, createdRecipeTest.getCategories().size());

        assertEquals(1, createdRecipe1.getRecipeIngredients().size());
        assertEquals(1, createdRecipe1.getCategories().size());
    }


    @Test
    public void test_remove() {

        Optional<Recipe> optionalRecipe = testObject.findById(createdRecipe2.getId());

        if (optionalRecipe.isPresent()) testObject.delete(optionalRecipe.get());
        else throw new DataNotFoundException("Data NotFound Exception");

    }

    @Test
    public void test_findAllByRecipeNameContains() {

        int expectedResult = 2;
        int actualResult = testObject.findAllByRecipeNameContains("a").size();
        assertEquals(expectedResult, actualResult);
    }


    /*
    @Test
    public void test_findAllByRecipeIngredientsContains() {

        int expectedResult = 2;
        int actualResult = testObject.findAllByRecipeIngredientsContains(recipeIngredientRepository.findByIngredient_IngredientName("Sugar").get()).size();

        assertEquals(expectedResult, actualResult);


    }

     */
}











