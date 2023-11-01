package hr.algebra.thequacksofquedlinburg.Conrollers;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.EnumIngredient;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.IngredientGroup;
import hr.algebra.thequacksofquedlinburg.gameBoard.PlayerPot;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private TextField tfPlayer1EnterPoints;

    @FXML
    private TextField tfPlayer2EnterPoints;

    private BoardController boardController;


    private int pointsPlayer1;
    private int pointsPlayer2;
    private HashMap<IngredientGroup, Integer> pointCounter = new HashMap<>();
    private Stage stage;

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

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

            if (totalPoints >= 7) {
                bust("Its a BUST!");
                pnPlayer1Pot.getChildren().add(selectedIngredientLabel);
            } else {
                pnPlayer1Pot.getChildren().add(selectedIngredientLabel);
            }

        }
    }
    @FXML
    void onbtnEndTurnPlayer1Clicked(ActionEvent event) {
        try {
            pointsPlayer1 = Integer.parseInt(tfPlayer1EnterPoints.getText());

            if (pointsPlayer1 == 1){
                Label player1Label = new Label("Player 1 has to move: " + pointsPlayer1 + " place forward");
                boardController.removePlayer2Label();
                player1Label.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                boardController.addLabelToPlayerPointsPane(player1Label);
            }else {
                Label player1Label = new Label("Player 1 has to move: " + pointsPlayer1 + " places forward");
                boardController.removePlayer2Label();
                player1Label.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                boardController.addLabelToPlayerPointsPane(player1Label);
            }

            stage.close();
        } catch (NumberFormatException e) {
            System.err.println("Invalid points value in the text field.");
        }
    }
    @FXML
    void onbtnEndTurnPlayer2Clicked(ActionEvent event) {
        try {
            pointsPlayer2 = Integer.parseInt(tfPlayer2EnterPoints.getText());

            if (pointsPlayer2 == 1){
                Label player2Label = new Label("Player 2 has to move: " + pointsPlayer2 + " place forward");
                boardController.removePlayer1Label();
                player2Label.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                boardController.addLabelToPlayerPointsPane(player2Label);
            }else {
                Label player2Label = new Label("Player 2 has to move: " + pointsPlayer2 + " places forward");
                boardController.removePlayer1Label();
                player2Label.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                boardController.addLabelToPlayerPointsPane(player2Label);
            }

            stage.close();
        } catch (NumberFormatException e) {
            System.err.println("Invalid points value in the text field.");
        }
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

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public int getPlayer1Points() {
        return pointsPlayer1;
    }
    public int getPlayer2Points() {
        return pointsPlayer2;
    }
}
