package hr.algebra.thequacksofquedlinburg.Conrollers;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.gameBoard.Player2Pot;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Player2PotController implements Initializable {
    private Team team;
    private Player2Pot player2Pot;

    private final Random random = new Random();
    @FXML
    private Pane pnPlayer2Pot;

    @FXML
    private ImageView potView;

    @FXML
    private Label waterDrop;
    @FXML
    private TextField tfEnterPoints;
    @FXML
    private Button btnEndTurn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image waterDropImg = new Image(MainApplication.class.getResource("images/waterDrop2-removebg-preview.png").toString());
        ImageView waterDropImageView = new ImageView(waterDropImg);
        waterDropImageView.setFitWidth(42);
        waterDropImageView.setFitHeight(42);
        waterDropImageView.setTranslateX(-10);
        waterDropImageView.setTranslateY(-11);
        waterDrop.setGraphic(waterDropImageView);

        player2Pot = new Player2Pot();
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
    void onbtnPullClicked2(MouseEvent event) {
        List<Label> ingredientLabels = player2Pot.getIngredientImages();

        if (!ingredientLabels.isEmpty()) {
            int randomIndex = random.nextInt(ingredientLabels.size());
            Label selectedIngredientLabel = ingredientLabels.remove(randomIndex);
            ImageView selectedIngredient = (ImageView) selectedIngredientLabel.getGraphic();

            selectedIngredient.setFitWidth(50);
            selectedIngredient.setFitHeight(50);
            selectedIngredient.setLayoutX(10);
            selectedIngredient.setLayoutY(10);
            setDraggable(selectedIngredientLabel);

            int selectedIngredientPoints = (int) selectedIngredientLabel.getUserData();

            int totalPoints = calculateTotalPoints(pnPlayer2Pot);

            if (totalPoints + selectedIngredientPoints > 7) {
                bust("Its a BUST!");
            } else {
                pnPlayer2Pot.getChildren().add(selectedIngredientLabel);
            }

        }
    }

    @FXML
    void onbtnEndTurnClicked(ActionEvent event) {

        //Stage stage2 = (Stage) btnEndTurn.getScene().getWindow();
        //stage2.close();
    }
    private void setDraggable (Label label){
        final Player2PotController.Delta dragDelta = new Player2PotController.Delta();

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

    private static class Delta {
        double x, y;
    }

    private int calculateTotalPoints(Pane pane) {
        int totalPoints = 0;
        for (Node node : pane.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                int points = (int) label.getUserData();
                totalPoints += points;
            }
        }
        return totalPoints;
    }

    private void bust(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("BUST");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
