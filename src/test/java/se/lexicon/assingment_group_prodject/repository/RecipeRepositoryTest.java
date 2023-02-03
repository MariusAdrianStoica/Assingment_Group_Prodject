package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.*;
import se.lexicon.assingment_group_prodject.exception.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeRepositoryTest {



    @Autowired
    RecipeRepository testObject;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

     Recipe createdRecipe1;
     Recipe createdRecipe2;

     /*
    List<Ingredient> ingredientList = new ArrayList<>();

      */
    List<RecipeIngredient> recipeIngredients = new ArrayList<>();




    @BeforeEach
    public void setup(){
        RecipeInstruction recipeInstruction1 = new RecipeInstruction("InstructionCake");
        Recipe recipeData1 = new Recipe("Cake", recipeInstruction1);
        createdRecipe1 = testObject.save(recipeData1);

        RecipeInstruction recipeInstruction2 = new RecipeInstruction("InstructionBread");
        Recipe recipeData2 = new Recipe("Bread", recipeInstruction2);
        createdRecipe2 = testObject.save(recipeData2);
    }


    @Test
    public void test_add(){

        RecipeInstruction recipeInstructionTest = new RecipeInstruction("InstructionTest");
        Recipe recipeDataTest = new Recipe("Test", recipeInstructionTest);
        Recipe createdRecipeTest = testObject.save(recipeDataTest);

        assertNotNull(createdRecipeTest);
        assertNotNull(createdRecipeTest.getId());

    }


    @Test
    public void test_remove(){

        Optional<Recipe> optionalRecipe = testObject.findById(createdRecipe2.getId());

        if (optionalRecipe.isPresent()) testObject.delete(optionalRecipe.get());
        else throw new DataNotFoundException("Data NotFound Exception");

    }

    @Test
    public void test_findAllByRecipeNameContains(){

        int expectedResult =2;
        int actualResult  = testObject.findAllByRecipeNameContains("a").size();
        assertEquals(expectedResult, actualResult);
    }


/*

    @Test
    public void test_findAllByRecipeNameAndRecipeIngredientsContains(){

        Ingredient ingredientData1 = new Ingredient("Salt");
        Ingredient createdIngredient1 = ingredientRepository.save(ingredientData1);

        RecipeIngredient recipeIngredientData1 = new RecipeIngredient(createdIngredient1, 2, Measurement.TSP);

        assertNotNull();
        /*
        recipeIngredients.add(recipeIngredientData1);

        createdRecipe1.addRecipeIngredient(recipeIngredientData1);
        createdRecipe2.addRecipeIngredient(recipeIngredientData1);

        assertNotNull(createdRecipe1.getRecipeIngredients());

        /*
        int expectedResult =2;
        int actualResult  = testObject.findAllByRecipeIngredientsContains("Salt").size();
        assertEquals(expectedResult, actualResult);

         */
    }










