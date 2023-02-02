package se.lexicon.assingment_group_prodject.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.assingment_group_prodject.entity.Ingredient;
import se.lexicon.assingment_group_prodject.exception.DataNotFoundException;

import java.util.Optional;

@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository testObject;

    Ingredient createdIngredient1;
    Ingredient createdIngredient2;

    @BeforeEach
    public void setup(){
        Ingredient ingredientData1 = new Ingredient("Salt");
        createdIngredient1 = testObject.save(ingredientData1);
        Ingredient ingredientData2 = new Ingredient("Sugar");
        createdIngredient2 = testObject.save(ingredientData2);

    }


    @Test
    public void test_add(){

        Ingredient ingredientDataTest = new Ingredient("Pepper");
        Ingredient createdIngredientTest = testObject.save(ingredientDataTest);

        assertNotNull(createdIngredientTest);
        assertNotNull(createdIngredientTest.getId());

    }

    @Test
    public void test_remove(){

        Optional<Ingredient> optionalIngredient = testObject.findById(createdIngredient1.getId());

        if (optionalIngredient.isPresent()) testObject.delete(optionalIngredient.get());
        else throw new DataNotFoundException("Data NotFound Exception");

    }



}
