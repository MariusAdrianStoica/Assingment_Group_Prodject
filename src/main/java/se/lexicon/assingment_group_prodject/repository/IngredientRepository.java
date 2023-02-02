package se.lexicon.assingment_group_prodject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.assingment_group_prodject.entity.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {


    Optional<Ingredient> findByIngredientName(String ingredientName);

    List<Ingredient> findAllByIngredientNameContains(String ingredientName);
}
