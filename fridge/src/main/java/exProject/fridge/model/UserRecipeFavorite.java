package exProject.fridge.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(FavoriteId.class)
public class UserRecipeFavorite {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;
}
