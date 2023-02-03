package se.lexicon.assingment_group_prodject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.RecipeCategory;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeCategoryRepositoryTest {

    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;


    @Test
    public void test_add(){

        RecipeCategory recipeCategory = new RecipeCategory("cakes");
        RecipeCategory createdrecipeCategory = recipeCategoryRepository.save(recipeCategory);

        assertNotNull(createdrecipeCategory);
        assertNotNull(createdrecipeCategory.getCategory());
        assertEquals(0, createdrecipeCategory.getRecipes().size());

    }

}
