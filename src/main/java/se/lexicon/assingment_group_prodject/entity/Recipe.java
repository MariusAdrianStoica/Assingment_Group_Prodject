package se.lexicon.assingment_group_prodject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.assingment_group_prodject.exception.DataDuplicateException;
import se.lexicon.assingment_group_prodject.exception.DataNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String recipeName;


    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    private RecipeInstruction instruction;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_recipe_category",
            joinColumns = @JoinColumn(name = "recipe_category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<RecipeCategory> categories = new HashSet<>();




    public Recipe(String recipeName, RecipeInstruction instruction) {
        this.recipeName = recipeName;
        this.instruction = instruction;
        }


    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredients.contains(recipeIngredient)) throw new DataDuplicateException("Data Duplicate Exception");
        recipeIngredients.add(recipeIngredient);

    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if (!recipeIngredients.contains(recipeIngredient)) throw new DataNotFoundException("Data Not Found Exception");
        recipeIngredients.remove(recipeIngredient);
    }

    public void addRecipeCategory(RecipeCategory recipeCategory){
        if (categories.contains(recipeCategory)) throw new DataDuplicateException("Data Duplicate Exception");
        categories.add(recipeCategory);

    }

    public void removeRecipeCategory(RecipeCategory recipeCategory){
        if (!categories.contains(recipeCategory)) throw new DataNotFoundException("Data Not Found Exception");
        categories.remove(recipeCategory);
    }


}
