package hr.algebra.thequacksofquedlinburg.GameBoard.enums;

public enum EnumIngredient {
    Bloom1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom1.png", 1, IngredientGroup.Bloom, "Bloom1"),
    Bloom2("/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom2.png", 2, IngredientGroup.Bloom, "Bloom2"),
    Bloom3("/hr/algebra/thequacksofquedlinburg/images/ingredients/Bloom3.png", 3, IngredientGroup.Bloom, "Bloom3"),
    Crowskull1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Crowskull1.png", 1, IngredientGroup.Crowskull, "Crowskull1"),
    Crowskull2("/hr/algebra/thequacksofquedlinburg/images/ingredients/Crowskull2.png", 2, IngredientGroup.Crowskull, "Crowskull2"),
    Crowskull4("/hr/algebra/thequacksofquedlinburg/images/ingredients/Crowskull4.png", 4, IngredientGroup.Crowskull, "Crowskull4"),
    GardenSpider1("/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider1.png", 1, IngredientGroup.GardenSpider, "GardenSpider1"),
    GardenSpider2("/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider2.png", 2, IngredientGroup.GardenSpider, "GardenSpider2"),
    GardenSpider4("/hr/algebra/thequacksofquedlinburg/images/ingredients/GardenSpider4.png", 4, IngredientGroup.GardenSpider, "GardenSpider4"),
    Hawkmoth1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Hawkmoth1.png", 1, IngredientGroup.Hawkmoth, "Hawkmoth1"),
    Mandrake1("/hr/algebra/thequacksofquedlinburg/images/ingredients/Mandrake1.png", 1, IngredientGroup.Mandrake, "Mandrake1"),
    Mandrake2("/hr/algebra/thequacksofquedlinburg/images/ingredients/Mandrake2.png", 2, IngredientGroup.Mandrake, "Mandrake2"),
    Mandrake4("/hr/algebra/thequacksofquedlinburg/images/ingredients/Mandrake4.png", 4, IngredientGroup.Mandrake, "Mandrake4"),
    pumpkin1("/hr/algebra/thequacksofquedlinburg/images/ingredients/pumpkin1.png", 1, IngredientGroup.pumpkin, "pumpkin1");

    private String filePath;
    private int points;
    private IngredientGroup group;

    private String name;


    EnumIngredient(String filePath, int points, IngredientGroup group, String name) {
        this.filePath = filePath;
        this.points = points;
        this.group = group;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
