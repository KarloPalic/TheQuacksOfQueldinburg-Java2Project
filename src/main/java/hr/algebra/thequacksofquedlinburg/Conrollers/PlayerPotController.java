package hr.algebra.thequacksofquedlinburg.Conrollers;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.EnumIngredient;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.IngredientGroup;
import hr.algebra.thequacksofquedlinburg.gameBoard.PlayerPot;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;


public class PlayerPotController implements Initializable {
    private Team team;

    private PlayerPot playerPot;

    private final Random random = new Random();

    @FXML
    private Pane pnPlayer1Pot;

    @FXML
    private ImageView potView;

    @FXML
    private Label waterDrop;
    @FXML
    private TextField tfEnterPoints;
    @FXML
    private Button btnEndTurn;
    private BoardController boardController;
    private HashMap<IngredientGroup, Integer> pointCounter = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image waterDropImg = new Image(MainApplication.class.getResource("images/waterDrop1-removebg-preview.png").toString());
        ImageView waterDropImageView = new ImageView(waterDropImg);
        waterDropImageView.setFitWidth(42);
        waterDropImageView.setFitHeight(42);
        waterDropImageView.setTranslateX(-10);
        waterDropImageView.setTranslateY(-11);
        waterDrop.setGraphic(waterDropImageView);

        playerPot = new PlayerPot(team);
        potView.toBack();
        setDraggable(waterDrop);

        tfEnterPoints.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int points = Integer.parseInt(newValue);
                if (points >= 0) {
                    btnEndTurn.setDisable(false);
                } else {
                    btnEndTurn.setDisable(true);
                }
            } catch (NumberFormatException e) {
                btnEndTurn.setDisable(true);
            }
        });

    }


    @FXML
    void onbtnPullClicked1(MouseEvent event) {
        List<Label> ingredientLabels = playerPot.getIngredientImages();

        if (!ingredientLabels.isEmpty()) {
            int randomIndex = random.nextInt(ingredientLabels.size());
            Label selectedIngredientLabel = ingredientLabels.remove(randomIndex);
            ImageView selectedIngredient = (ImageView) selectedIngredientLabel.getGraphic();

            selectedIngredient.setFitWidth(50);
            selectedIngredient.setFitHeight(50);
            selectedIngredient.setLayoutX(10);
            selectedIngredient.setLayoutY(10);
            setDraggable(selectedIngredientLabel);

            EnumIngredient ingredient = (EnumIngredient) selectedIngredientLabel.getUserData();
            pointCounter.put(ingredient.getGroup(),
                    pointCounter.getOrDefault(ingredient.getGroup(), 0) + ingredient.getPoints());


            int totalPoints = pointCounter.get(ingredient.getGroup());

      //      System.out.println(totalPoints + " for " + ingredient.getGroup() + " - " + ingredient);

            if (totalPoints >= 7) {
                bust("Its a BUST!");
            } else {
                pnPlayer1Pot.getChildren().add(selectedIngredientLabel);
            }

        }
    }
    @FXML
    void onbtnEndTurnClicked(ActionEvent event) {

    }
    private void setDraggable (Label label){
        final Delta dragDelta = new Delta();

        label.setOnMousePressed(event -> {
            dragDelta.x = event.getSceneX() - label.getLayoutX();
            dragDelta.y = event.getSceneY() - label.getLayoutY();
            label.setCursor(Cursor.MOVE);
        });

        label.setOnMouseDragged(event -> {
            label.setLayoutX(event.getSceneX() - dragDelta.x);
            label.setLayoutY(event.getSceneY() - dragDelta.y);
        });

        label.setOnMouseReleased(event -> {
            label.setCursor(Cursor.HAND);
        });
    }

    static class Delta {
        double x, y;
    }

    private void bust(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("BUST");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
