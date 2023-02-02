package se.lexicon.assingment_group_prodject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.assingment_group_prodject.exception.DataDuplicateException;
import se.lexicon.assingment_group_prodject.exception.DataNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();



    public RecipeCategory(String category) {
        this.category = category;
    }

    public void addRecipe(Recipe recipe) {
        if (recipes.contains(recipe)) throw new DataDuplicateException("Data Duplicate Exception");
        recipes.add(recipe);

    }

    public void removeRecipe(Recipe recipe) {
        if (!recipes.contains(recipe)) throw new DataNotFoundException("Data Not Found Exception");
        recipes.remove(recipe);
    }
}
