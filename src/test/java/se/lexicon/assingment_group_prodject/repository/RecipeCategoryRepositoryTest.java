package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.Ingredient;
import se.lexicon.assingment_group_prodject.entity.RecipeCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeCategoryRepositoryTest {

    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;

    RecipeCategory createdRecipeCategory1;
    RecipeCategory createdRecipeCategory2;
    RecipeCategory createdRecipeCategory3;

    RecipeCategory recipeCategoryData1;
    RecipeCategory recipeCategoryData2;
    RecipeCategory recipeCategoryData3;

    @BeforeEach
    public void setup(){
        recipeCategoryData1 = new RecipeCategory("Weekend");
        createdRecipeCategory1 = recipeCategoryRepository.save(recipeCategoryData1);

        recipeCategoryData2 = new RecipeCategory("Spicy");
        createdRecipeCategory2 = recipeCategoryRepository.save(recipeCategoryData2);

        recipeCategoryData3 = new RecipeCategory("Mexican");
        createdRecipeCategory3 = recipeCategoryRepository.save(recipeCategoryData3);
    }


    @Test
    public void test_add(){

        RecipeCategory recipeCategory = new RecipeCategory("cakes");
        RecipeCategory createdrecipeCategory = recipeCategoryRepository.save(recipeCategory);

        assertNotNull(createdrecipeCategory);
        assertNotNull(createdrecipeCategory.getCategory());
        assertEquals(0, createdrecipeCategory.getRecipes().size());

    }

    @Test
    public void test_findByCategoryIgnoreCase(){

        Optional<RecipeCategory> optionalRecipeCategory =  recipeCategoryRepository.findByCategoryIgnoreCase("MEXICAN");

        assertTrue(optionalRecipeCategory.isPresent());

        RecipeCategory actual = optionalRecipeCategory.get();
        RecipeCategory expected =createdRecipeCategory3;

        assertEquals(expected, actual);

    }


    @Test
    public void test_findAll(){

        int expected = 3;
        int actual = recipeCategoryRepository.findAll().size();

        List<RecipeCategory> actualRecipeCategories = recipeCategoryRepository.findAll();

        List<RecipeCategory> expectedRecipeCategories = new ArrayList<>();
        expectedRecipeCategories.add(recipeCategoryData1);
        expectedRecipeCategories.add(recipeCategoryData2);
        expectedRecipeCategories.add(recipeCategoryData3);

        assertEquals(expected, actual);
        assertEquals(expectedRecipeCategories, actualRecipeCategories);



    }



}
