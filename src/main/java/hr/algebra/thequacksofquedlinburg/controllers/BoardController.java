package hr.algebra.thequacksofquedlinburg.controllers;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.chat.RemoteChatService;
import hr.algebra.thequacksofquedlinburg.gameBoard.*;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import hr.algebra.thequacksofquedlinburg.jndi.ConfigurationReader;
import hr.algebra.thequacksofquedlinburg.networking.*;
import hr.algebra.thequacksofquedlinburg.thread.GetTheLatestMoveThread;
import hr.algebra.thequacksofquedlinburg.thread.SaveMoveThread;
import hr.algebra.thequacksofquedlinburg.utils.DocumentationUtil;
import hr.algebra.thequacksofquedlinburg.utils.XmlUtil;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static hr.algebra.thequacksofquedlinburg.gameBoard.PlayerPosition.getBoardPosition;

public class BoardController implements Serializable {
    @FXML
    private GridPane mainGridPane;
    @FXML
    private transient ImageView player1Icon;
    @FXML
    private transient ImageView player2Icon;
    @FXML
    private transient AnchorPane playerPointsPane;
    @FXML
    private TextField chatMessageTextField;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private Label theLastMoveLabel;
    private static PlayerPotController playerPotController1;
    private static PlayerPotController playerPotController2;
    private int player1CircleBoardPosX;
    private int player1CircleBoardPosY;
    private int player2CircleBoardPosX;
    private int player2CircleBoardPosY;
    private static int player1Points;
    private static int player2Points;
    private static transient Circle player1Circle;
    private static transient Circle player2Circle;
    private transient Stage stage;
    private static RemoteChatService chatServiceStub;
    private static int turnCount = 0;
    private static Team currentTurn = Team.Blue;
    private static String playerPosition;
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public GridPane getMainGridPane() {
        return mainGridPane;
    }
    public void initialize() {

        MainBoard mainBoard = new MainBoard(mainGridPane);
        mainBoard.createGridPane(mainGridPane);
        mainBoard.layoutGridPane(mainGridPane);

        player1Icon.setTranslateX(-627);
        player1Icon.setTranslateY(170);


        player2Icon.setTranslateX(-620);
        player2Icon.setTranslateY(136);

        if (player1CircleBoardPosX != 0 && player1CircleBoardPosY != 0) {
            player1Circle = new Circle(10);
            player1Circle.setFill(Color.BLUE);
            mainGridPane.add(player1Circle, player1CircleBoardPosX, player1CircleBoardPosY);
        }

        if (player2CircleBoardPosX != 0 && player2CircleBoardPosY != 0) {
            player2Circle = new Circle(10);
            player2Circle.setFill(Color.RED);
            mainGridPane.add(player2Circle, player2CircleBoardPosX, player2CircleBoardPosY);
        }

        if (!PlayerType.SINGLE_PLAYER.name().equals(MainApplication.playerLoggedIn.name())) {
            try {

                Registry registry = LocateRegistry.getRegistry(
                        ConfigurationReader.getStringValue(ConfigurationKey.HOSTNAME.getKeyName()).get()
                        ,ConfigurationReader.getIntergerValue(ConfigurationKey.RMI_PORT.getKeyName()).get());
                chatServiceStub = (RemoteChatService) registry.lookup(RemoteChatService.REMOTE_CHAT_OBJECT_NAME);

            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> refreshChatArea()));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.playFromStart();

            chatMessageTextField.setOnKeyPressed(
                    (e) -> {
                        if (e.getCode() == KeyCode.ENTER) {
                            sentChatMessage();
                        }
                    }
            );
        } else {
            GetTheLatestMoveThread theLatestMoveThread = new GetTheLatestMoveThread(theLastMoveLabel);
            Thread runnerThread = new Thread(theLatestMoveThread);
            runnerThread.start();
        }
    }
    public void sentChatMessage(){
        String chatMessage = chatMessageTextField.getText();
        try {
            chatServiceStub.sendChatMessage(
                    MainApplication.playerLoggedIn + ":" + chatMessage);
            chatMessageTextField.clear();
        }
        catch(RemoteException ex) {
            ex.printStackTrace();
        }

    }
    private void refreshChatArea(){
        chatTextArea.clear();

        try {
            List<String> listOfChatMessages = chatServiceStub.getAllMessages();
            for(String chatMessage :  listOfChatMessages) {
                chatTextArea.appendText(chatMessage + "\n");
            }
        }
        catch(RemoteException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void openPot1(ActionEvent event){
        if (currentTurn == Team.Blue){
            openPot(Team.Blue, "PotView1.fxml");
        }else {
            GameBoardUtils.showMessage("It is not your turn");
        }

    }
    @FXML
    public void openPot2(ActionEvent event){
        if (currentTurn == Team.Red){
            openPot(Team.Red, "PotView2.fxml");
        } else {
            GameBoardUtils.showMessage("It is not your turn");
        }
    }
    private void openPot(Team team, String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFileName));
            Parent root = fxmlLoader.load();

            PlayerPotController controller = fxmlLoader.getController();
            controller.setTeam(team);
            controller.setStage(new Stage());

            controller.setBoardController(this);

            Stage stage1 = new Stage();
            stage1.setTitle("Player " + team.name() + " Pot");
            stage1.setScene(new Scene(root));
            stage1.show();

            controller.setStage(stage1);

            if (team == Team.Blue) {
                controller.onbtnEndTurnPlayer1Clicked(null);
                playerPotController1 = controller;
            } else {
                controller.onbtnEndTurnPlayer2Clicked(null);
                playerPotController2 = controller;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        turnCount++;
        currentTurn = (currentTurn == Team.Blue) ? Team.Red : Team.Blue;

        if (PlayerType.SINGLE_PLAYER.name().equals(MainApplication.playerLoggedIn.name())){
            if (turnCount == 6) {
                int pointsPlayer1 = playerPotController1.getPlayer1Points();
                int pointsPlayer2 = playerPotController2.getPlayer2Points();
                GameBoardUtils.determineWinner(pointsPlayer1, pointsPlayer2);
                playerPotController1.getStage().close();
                playerPotController2.getStage().close();
                clearBoard();
                System.out.println(currentTurn);
            }

            GameMove gameMove = new GameMove(currentTurn, playerPosition, LocalDateTime.now());
            XmlUtil.saveGameMoveToXml(gameMove);
            SaveMoveThread saveMoveThread = new SaveMoveThread(gameMove);
            Thread newStarterThread = new Thread(saveMoveThread);
            newStarterThread.start();

        }
    }
    public void replayTheLastGame(){
        List<GameMove> gameMoveList = XmlUtil.readGameMovesFromXmlFile();

        AtomicInteger gameMoveCounter = new AtomicInteger();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),e -> {
            GameMove newGameMove =  gameMoveList.get(gameMoveCounter.get());

            Team playerColor = newGameMove.getPlayerColor();
            String playerPositionString = newGameMove.getPlayerPosition();

            String[] positionArray = playerPositionString.split(",");
            int posX = Integer.parseInt(positionArray[0]);
            int posY = Integer.parseInt(positionArray[1]);
            Pair<Integer, Integer> playerPosition = new Pair<>(posX, posY);

            if (playerColor == Team.Blue) {
                player1Circle = new Circle(10);
                player1Circle.setFill(Color.BLUE);
                player1CircleBoardPosX = playerPosition.getKey();
                player1CircleBoardPosY = playerPosition.getValue();
                mainGridPane.add(player1Circle, player1CircleBoardPosX, player1CircleBoardPosY);
            }
            else if (playerColor == Team.Red){
                player2Circle = new Circle(10);
                player2Circle.setFill(Color.RED);
                player2CircleBoardPosX = playerPosition.getKey();
                player2CircleBoardPosY = playerPosition.getValue();
                mainGridPane.add(player2Circle, player2CircleBoardPosX, player2CircleBoardPosY);
            }

            gameMoveCounter.set(gameMoveCounter.get() + 1);
        }));
        timeline.setCycleCount(gameMoveList.size());
        timeline.playFromStart();

    }
    public void sendGameStateToServer(){
        GameState gameStateCreated = GameStateUtils.createGameState(player1Points, player2Points, turnCount, currentTurn, playerPotController1, playerPotController2, player1Circle, player2Circle, player1CircleBoardPosX, player1CircleBoardPosY, player2CircleBoardPosX, player2CircleBoardPosY, stage);

        if (!MainApplication.playerLoggedIn.name().equals(PlayerType.SINGLE_PLAYER.name())){
            if (MainApplication.playerLoggedIn.name().equals(PlayerType.CLIENT.name())) {
                NetworkingUtils.sendGameBoardToServer(gameStateCreated);
                System.out.println("Player 1: " + turnCount);
            } else {
                NetworkingUtils.sendGameBoardToClient(gameStateCreated);
                System.out.println("Player 2: " + turnCount);
            }
        }
    }
    public void addPlayer1Points(int points){
        player1Points += points;
    }

    public void addPlayer2Points(int points){
        player2Points += points;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }
    public void movePlayer1(int movePlayer){
        player1Circle = new Circle(10);
        player1Circle.setFill(Color.BLUE);
        Pair<Integer, Integer> player1BoardPosition = getBoardPosition(movePlayer);
        player1CircleBoardPosX = player1BoardPosition.getKey();
        player1CircleBoardPosY = player1BoardPosition.getValue();
        playerPosition = PlayerPosition.formatPairAsString(player1BoardPosition);
        mainGridPane.add(player1Circle, player1CircleBoardPosX, player1CircleBoardPosY);
    };
    public void erasePlayer1(int erasePlayer){
        if (player1Circle != null) {
            mainGridPane.getChildren().remove(player1Circle);
            player1Circle = null;
        }
    };
    public void movePlayer2(int movePlayer){
        player2Circle = new Circle(10);
        player2Circle.setFill(Color.RED);
        Pair<Integer, Integer> player2BoardPosition = getBoardPosition(movePlayer);
        player2CircleBoardPosX = player2BoardPosition.getKey();
        player2CircleBoardPosY = player2BoardPosition.getValue();
        playerPosition = PlayerPosition.formatPairAsString(player2BoardPosition);
        mainGridPane.add(player2Circle, player2CircleBoardPosX,player2CircleBoardPosY);
    };
    public void erasePlayer2(int erasePlayer){
        if (player2Circle != null) {
            mainGridPane.getChildren().remove(player2Circle);
            player2Circle = null;
        }
    };
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeDouble(player1Circle.getCenterX());
        out.writeDouble(player1Circle.getCenterY());
        out.writeDouble(player1Circle.getRadius());

        out.writeDouble(player2Circle.getCenterX());
        out.writeDouble(player2Circle.getCenterY());
        out.writeDouble(player2Circle.getRadius());
    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        double player1CircleCenterX = in.readDouble();
        double player1CircleCenterY = in.readDouble();
        double player1CircleRadius = in.readDouble();

        player1Circle = new Circle(player1CircleRadius);
        player1Circle.setFill(Color.BLUE);
        player1Circle.setCenterX(player1CircleCenterX);
        player1Circle.setCenterY(player1CircleCenterY);

        double player2CircleCenterX = in.readDouble();
        double player2CircleCenterY = in.readDouble();
        double player2CircleRadius = in.readDouble();

        player2Circle = new Circle(player2CircleRadius);
        player2Circle.setFill(Color.RED);
        player2Circle.setCenterX(player2CircleCenterX);
        player2Circle.setCenterY(player2CircleCenterY);
    }
    public void clearBoard() {
        player1Points = 0;
        player2Points = 0;

        playerPointsPane.getChildren().clear();

        turnCount = 0;
        currentTurn = Team.Blue;

        if (player1Circle != null) {
            mainGridPane.getChildren().remove(player1Circle);
            player1Circle = null;
        }

        if (player2Circle != null) {
            mainGridPane.getChildren().remove(player2Circle);
            player2Circle = null;
        }

        playerPotController1 = null;
        playerPotController2 = null;
    }
    public void saveGame(){
        GameState gameStateCreated = GameStateUtils.createGameState(player1Points, player2Points, turnCount, currentTurn, playerPotController1, playerPotController2, player1Circle, player2Circle, player1CircleBoardPosX,player1CircleBoardPosY,player2CircleBoardPosX,player2CircleBoardPosY, stage);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gameState.dat"))) {
            oos.writeObject(gameStateCreated);
            oos.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save game successful!");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully saved the game!");

            alert.showAndWait();

            clearBoard();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void restoreGameBoard(GameState restoredGameState, GridPane mainGridPane){
        if (mainGridPane == null) {
            System.err.println("mainGridPane is null. Cannot restore game board.");
            return;
        }

        player1Points = restoredGameState.player1Points;
        player2Points = restoredGameState.player2Points;
        turnCount = restoredGameState.turnCount;
        currentTurn = restoredGameState.currentTurn;
        playerPotController1 = restoredGameState.playerPotController1;
        playerPotController2 = restoredGameState.playerPotController2;

        player1CircleBoardPosX = restoredGameState.player1CircleBoardPosX;
        player1CircleBoardPosY = restoredGameState.player1CircleBoardPosY;

        player2CircleBoardPosX = restoredGameState.player2CircleBoardPosX;
        player2CircleBoardPosY = restoredGameState.player2CircleBoardPosY;

        player1Circle = new Circle(10);
        player1Circle.setFill(Color.BLUE);
        mainGridPane.add(player1Circle, player1CircleBoardPosX,player1CircleBoardPosY);

        player2Circle = new Circle(10);
        player2Circle.setFill(Color.RED);
        mainGridPane.add(player2Circle, player2CircleBoardPosX,player2CircleBoardPosY);

        GameStateUtils.retoreGameState(mainGridPane, restoredGameState,player1Points, player2Points, turnCount, currentTurn, playerPotController1, playerPotController2, player1Circle, player2Circle, player1CircleBoardPosX,player1CircleBoardPosY,player2CircleBoardPosX,player2CircleBoardPosY);

    }
    public void loadGame(){
        clearBoard();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gameState.dat"))) {
            GameState gameState = (GameState) ois.readObject();
            ois.close();

            restoreGameBoard(gameState, mainGridPane);
            gameState.clearCircles();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Load game successful!");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully loaded the game!");

            alert.showAndWait();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDoc(){

        DocumentationUtil documentationUtil = new DocumentationUtil();

        documentationUtil.generateDocumentation(DocumentationUtil.THE_QUACKS_PATH, DocumentationUtil.HTML_QUACKS);
        documentationUtil.generateDocumentation(DocumentationUtil.GAME_BOARD_PATH, DocumentationUtil.HTML_GAME_BOARD);
        documentationUtil.generateDocumentation(DocumentationUtil.CONTROLLER_PATH, DocumentationUtil.HTML_CONTROLLERS);
        documentationUtil.generateDocumentation(DocumentationUtil.ENUM_PATH, DocumentationUtil.HTML_ENUMS);
        documentationUtil.generateDocumentation(DocumentationUtil.UTILS_PATH, DocumentationUtil.HTML_UTILS);

        GameBoardUtils.showMessage("Documentation created");
    }
}
