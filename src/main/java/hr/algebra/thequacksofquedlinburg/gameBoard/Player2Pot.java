package hr.algebra.thequacksofquedlinburg.gameBoard;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player2Pot {
    private final List<Label> ingredientLabels;

    public Player2Pot() {
        ingredientLabels = new ArrayList<>();
        getIngredients();
    }

    public List<Label> getIngredientImages() {
        return ingredientLabels;
    }

    public int getIngredientPoints(String path) {
        switch (path) {
            case "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png":
                return 1;
            case "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom2.png":
                return 2;
            case "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom3.png":
                return 3;
            case "/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider1.png":
                return 1;
            case "/hr/algebra/thequacksofquedlinburg/images/ingredients/pumpkin1.png":
                return 1;
            default:
                return 0; // Set a default value if the path is not recognized
        }
    }

    private void getIngredients() {
        String[] startIngredients = {
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom2.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom2.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom3.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider1.png",
                "/hr/algebra/thequacksofquedlinburg/images/ingredients/pumpkin1.png",
        };

        for (int i = 0; i < startIngredients.length; i++) {
            String path = startIngredients[i];
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            ImageView imageView = new ImageView(image);
            Label label = new Label("", imageView);
            label.setId("ingredientLabel" + i);
            label.setUserData(getIngredientPoints(path));
            ingredientLabels.add(label);
        }
    }
}
