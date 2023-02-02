package se.lexicon.assingment_group_prodject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.assingment_group_prodject.entity.Recipe;

@Repository
public interface RecipeInstructionRepository extends CrudRepository<Recipe, Integer> {


}
