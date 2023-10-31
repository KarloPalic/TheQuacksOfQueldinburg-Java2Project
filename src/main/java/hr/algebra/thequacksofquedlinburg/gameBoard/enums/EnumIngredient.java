package hr.algebra.thequacksofquedlinburg.gameBoard.enums;

import java.util.Arrays;
import java.util.Collection;

public enum EnumIngredient {
    Bloom1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png", 1, IngredientGroup.Bloom),
    Bloom2("/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom2.png", 2, IngredientGroup.Bloom),
    Bloom3("/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom3.png", 3, IngredientGroup.Bloom),
    Crowskull1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Crowskull1.png", 1, IngredientGroup.Crowskull),
    Crowskull2("/hr/algebra/thequacksofquedlinburg/images/ingredients/Crowskull2.png", 2, IngredientGroup.Crowskull),
    Crowskull4("/hr/algebra/thequacksofquedlinburg/images/ingredients/Crowskull4.png", 4, IngredientGroup.Crowskull),
    GardenSpider1("/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider1.png", 1, IngredientGroup.GardenSpider),
    GardenSpider2("/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider2.png", 2, IngredientGroup.GardenSpider),
    GardenSpider4("/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider4.png", 4, IngredientGroup.GardenSpider),
    Hawkmoth1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Hawkmoth1.png", 1, IngredientGroup.Hawkmoth),
    Mandrake1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Mandrake1.png", 1, IngredientGroup.Mandrake),
    Mandrake2("/hr/algebra/thequacksofquedlinburg/images/ingredients/Mandrake2.png", 2, IngredientGroup.Mandrake),
    Mandrake4("/hr/algebra/thequacksofquedlinburg/images/ingredients/Mandrake4.png", 4, IngredientGroup.Mandrake),
    pumpkin1("/hr/algebra/thequacksofquedlinburg/images/ingredients/pumpkin1.png", 1, IngredientGroup.pumpkin);

    private String filePath;
    private int points;
    private IngredientGroup group;

    EnumIngredient(String filePath, int points, IngredientGroup group) {
        this.filePath = filePath;
        this.points = points;
        this.group = group;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getPoints() {
        return points;
    }

    public IngredientGroup getGroup() {
        return group;
    }
}
