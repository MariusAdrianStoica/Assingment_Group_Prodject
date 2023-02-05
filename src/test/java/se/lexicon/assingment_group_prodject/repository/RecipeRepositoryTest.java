package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.*;
import se.lexicon.assingment_group_prodject.exception.DataNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

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

    RecipeIngredient createdRecipeIngredient1;
    RecipeIngredient createdRecipeIngredient2;

    List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    Set<RecipeCategory> recipeCategories = new HashSet<>();

    @BeforeEach
    public void setup() {

        //1st ingredient
        Ingredient ingredientData1 = new Ingredient("Sugar");
        Ingredient createdIngredient1 = ingredientRepository.save(ingredientData1);

        //1st recipeIngredient
        RecipeIngredient recipeIngredient1 = new RecipeIngredient(createdIngredient1, 1, Measurement.HG);
        createdRecipeIngredient1 = recipeIngredientRepository.save(recipeIngredient1);
        recipeIngredients.add(createdRecipeIngredient1);

        //2nd ingredient
        Ingredient ingredientData2 = new Ingredient("Potato");
        Ingredient createdIngredient2 = ingredientRepository.save(ingredientData2);

        //2nd recipeIngredient
        RecipeIngredient recipeIngredient2 = new RecipeIngredient(createdIngredient2, 1, Measurement.KG);
        createdRecipeIngredient2 = recipeIngredientRepository.save(recipeIngredient2);
        recipeIngredients.add(createdRecipeIngredient2);

        // 1st category
        RecipeCategory recipeCategory1 = new RecipeCategory("cakes");
        RecipeCategory createdRecipeCategory1 = recipeCategoryRepository.save(recipeCategory1);
        recipeCategories.add(createdRecipeCategory1);

        //2nd category
        RecipeCategory recipeCategory2 = new RecipeCategory("Weekend");
        RecipeCategory createdRecipeCategory2 = recipeCategoryRepository.save(recipeCategory2);
        recipeCategories.add(createdRecipeCategory2);

        //1st instruction
        RecipeInstruction recipeInstruction1 = new RecipeInstruction("InstructionCake");

        //2nd  instruction
        RecipeInstruction recipeInstruction2 = new RecipeInstruction("InstructionBread");

        //1st recipe
        Recipe recipeData1 = new Recipe("Cake", recipeInstruction1);
        createdRecipe1 = testObject.save(recipeData1);
        createdRecipe1.addRecipeIngredient(createdRecipeIngredient1);
        createdRecipe1.addRecipeCategory(createdRecipeCategory1);

        //2nd recipe
        Recipe recipeData2 = new Recipe("Bread", recipeInstruction2);
        createdRecipe2 = testObject.save(recipeData2);
        createdRecipe2.addRecipeIngredient(createdRecipeIngredient2);
        //createdRecipe2.addRecipeCategory(createdRecipeCategory2);
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




    @Test
    public void test_findAllByRecipeIngredients() {

        int expectedResult = 2;

        RecipeIngredient found = recipeIngredientRepository.findByIngredient_IngredientName("Potato").get();
        RecipeIngredient expectedRI = createdRecipeIngredient2;

        assertEquals(expectedRI, found);

        //todo: List<Recipe>
        //int actualResult = testObject.findAllByRecipeIngredients(found);
        //assertEquals(expectedResult, actualResult);
    }





    @Test
    public void test_findAllByCategoriesContains(){

        int expected = 1;
        int actual = testObject.findAllByCategoriesContains(recipeCategoryRepository.findByCategoryIgnoreCase("cakes").get()).size();

        assertEquals(expected, actual);

    }



    @Test
    public void test_findAllByCategoriesIsNotNull(){

        int expected = 1;
        int actual = testObject.findAllByCategoriesIsNotNull().size();

        assertEquals(expected, actual);

    }
}











