package se.lexicon.assingment_group_prodject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecipeInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String instructions;

    public RecipeInstruction(String instructions) {
        this.instructions = instructions;
    }
}
