package se.lexicon.assingment_group_prodject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.assingment_group_prodject.entity.Recipe;
import se.lexicon.assingment_group_prodject.entity.RecipeIngredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, String> {


    Optional<RecipeIngredient> findByIngredient_IngredientName(String ingredientName);
}
