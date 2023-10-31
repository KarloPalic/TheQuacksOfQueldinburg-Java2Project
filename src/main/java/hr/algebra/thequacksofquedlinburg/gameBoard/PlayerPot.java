package hr.algebra.thequacksofquedlinburg.gameBoard;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.EnumIngredient;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

public class PlayerPot {
    private Team team;
    private final List<Label> ingredientLabels;

    public PlayerPot(Team team) {
        this.team = team;
        ingredientLabels = new ArrayList<>();
        getIngredients();
    }

    public List<Label> getIngredientImages() {
        return ingredientLabels;
    }

    public Team getTeam() {
        return team;
    }

    private void getIngredients() {
        for (EnumIngredient ingredient : EnumIngredient.values()) {
            int iters = 1;

            if (ingredient.getPoints() == 1) iters = 3;
            if (ingredient.getPoints() == 2) iters = 2;

            for (int i = 0; i < iters; i++) {
                String path = ingredient.getFilePath();
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
                ImageView imageView = new ImageView(image);

                Label label = new Label("", imageView);
                label.setId(ingredient.name());
                label.setUserData(ingredient);
                ingredientLabels.add(label);

            }
        }
    }
}



