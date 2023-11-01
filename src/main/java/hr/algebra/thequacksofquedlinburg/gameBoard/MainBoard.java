package hr.algebra.thequacksofquedlinburg.gameBoard;
import hr.algebra.thequacksofquedlinburg.MainApplication;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;


public class MainBoard{

    public void layoutGridPane(GridPane mainGridPane) {

        ColumnConstraints colMid = new ColumnConstraints();
        colMid.setPercentWidth(200);
        colMid.setHalignment(HPos.CENTER);

        mainGridPane.getColumnConstraints().addAll(colMid,colMid,colMid,colMid,colMid,colMid,colMid,colMid,colMid,colMid,colMid, colMid, colMid, colMid, colMid);

        RowConstraints rowsMid = new RowConstraints();
        rowsMid.setPercentHeight(200);

        mainGridPane.getRowConstraints().addAll(rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid);


        final StackPane imagePane = new StackPane();
        mainGridPane.add( imagePane, 1, 1, 13, 13 );

      final DoubleBinding multipliedHeight = mainGridPane.heightProperty().multiply( 0.72 );
        final DoubleBinding multipliedWidth = mainGridPane.widthProperty().multiply( 0.72 );

        imagePane.maxHeightProperty().bind( multipliedHeight );
        imagePane.maxWidthProperty().bind( multipliedWidth );
        imagePane.minHeightProperty().bind( multipliedHeight );
        imagePane.minWidthProperty().bind( multipliedWidth );
        imagePane.prefHeightProperty().bind( multipliedHeight );
        imagePane.prefWidthProperty().bind( multipliedWidth );

        final ImageView imageView =
                new ImageView(new Image(MainApplication.class.getResource("images/removed.png").toString()));
        imageView.setPreserveRatio( true );
        imageView.fitWidthProperty().bind( imagePane.widthProperty().divide( 2 ) );
        imageView.setScaleX(2.41);
        imageView.setScaleY(2.95);
        imageView.setTranslateX(0);
        imageView.setTranslateY(-21);


        imagePane.getChildren().add( imageView );

        mainGridPane.setGridLinesVisible( true );
    }

    public void createGridPane(GridPane mainGridPane){
        mainGridPane.setGridLinesVisible(true);
        int numRows = 14;
        int numCols = 11;

        for (int row = 0; row <= numRows; row++) {
            for (int col = 0; col <= numCols; col++) {

                //LEFT SIDE
                Label one = new Label("1");
                one.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                one.setId(String.valueOf(1));
                mainGridPane.add(one, 0, 11);


                Label two = new Label("2");
                two.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                two.setId(String.valueOf(2));
                mainGridPane.add(two, 0, 10);

                Label three = new Label("3");
                three.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                three.setId(String.valueOf(3));
                mainGridPane.add(three, 0, 9);

                Label four = new Label("4");
                four.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                four.setId(String.valueOf(4));
                mainGridPane.add(four, 0, 8);

                Label five = new Label("5");
                five.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                five.setId(String.valueOf(5));
                mainGridPane.add(five, 0, 7);

                Label six = new Label("6");
                six.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                six.setId(String.valueOf(6));
                mainGridPane.add(six, 0, 6);

                Label seven = new Label("7");
                seven.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                seven.setId(String.valueOf(7));
                mainGridPane.add(seven, 0, 5);

                Label eight = new Label("8");
                eight.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                eight.setId(String.valueOf(8));
                mainGridPane.add(eight, 0, 4);

                Label nine = new Label("9");
                nine.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                nine.setId(String.valueOf(9));
                mainGridPane.add(nine, 0, 3);

                Label ten = new Label("10");
                ten.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                ten.setId(String.valueOf(10));
                mainGridPane.add(ten, 0, 2);

                Label eleven = new Label("11");
                eleven.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                eleven.setId(String.valueOf(11));
                mainGridPane.add(eleven, 0, 1);

                Label twelve = new Label("12");
                twelve.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twelve.setId(String.valueOf(12));
                mainGridPane.add(twelve, 0, 0);

                //TOP SIDE
                Label thirteen = new Label("13");
                thirteen.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirteen.setId(String.valueOf(13));
                mainGridPane.add(thirteen, 1, 0);

                Label fourteen = new Label("14");
                fourteen.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourteen.setId(String.valueOf(14));
                mainGridPane.add(fourteen, 2, 0);

                Label fifteen = new Label("15");
                fifteen.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                fifteen.setId(String.valueOf(15));
                mainGridPane.add(fifteen, 3, 0);

                Label sixteen = new Label("16");
                sixteen.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                sixteen.setId(String.valueOf(16));
                mainGridPane.add(sixteen, 4, 0);

                Label seventeen = new Label("17");
                seventeen.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                seventeen.setId(String.valueOf(17));
                mainGridPane.add(seventeen, 5, 0);

                Label eighteen = new Label("18");
                eighteen.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                eighteen.setId(String.valueOf(18));
                mainGridPane.add(eighteen, 6, 0);

                Label nineteen = new Label("19");
                nineteen.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                nineteen.setId(String.valueOf(19));
                mainGridPane.add(nineteen, 7, 0);

                Label twenty = new Label("20");
                twenty.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                twenty.setId(String.valueOf(20));
                mainGridPane.add(twenty, 8, 0);

                Label twentyOne = new Label("21");
                twentyOne.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentyOne.setId(String.valueOf(21));
                mainGridPane.add(twentyOne, 9, 0);

                Label twentyTwo = new Label("22");
                twentyTwo.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentyTwo.setId(String.valueOf(22));
                mainGridPane.add(twentyTwo, 10, 0);

                Label twentyThree = new Label("23");
                twentyThree.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentyThree.setId(String.valueOf(23));
                mainGridPane.add(twentyThree, 11, 0);

                Label twentyFour = new Label("24");
                twentyFour.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentyFour.setId(String.valueOf(24));
                mainGridPane.add(twentyFour, 12, 0);

                Label twentyFive = new Label("25");
                twentyFive.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                twentyFive.setId(String.valueOf(25));
                mainGridPane.add(twentyFive, 13, 0);

                Label twentySix = new Label("26");
                twentySix.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentySix.setId(String.valueOf(26));
                mainGridPane.add(twentySix, 14, 0);

                //RIGHT SIDE
                Label twentySeven = new Label("27");
                twentySeven.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentySeven.setId(String.valueOf(27));
                mainGridPane.add(twentySeven, 14, 1);

                Label twentyEight = new Label("28");
                twentyEight.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentyEight.setId(String.valueOf(28));
                mainGridPane.add(twentyEight, 14, 2);

                Label twentyNine = new Label("29");
                twentyNine.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                twentyNine.setId(String.valueOf(29));
                mainGridPane.add(twentyNine, 14, 3);

                Label thirty = new Label("30");
                thirty.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                thirty.setId(String.valueOf(30));
                mainGridPane.add(thirty, 14, 4);

                Label thirtyOne = new Label("31");
                thirtyOne.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtyOne.setId(String.valueOf(31));
                mainGridPane.add(thirtyOne, 14, 5);

                Label thirtyTwo = new Label("32");
                thirtyTwo.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtyTwo.setId(String.valueOf(32));
                mainGridPane.add(thirtyTwo, 14, 6);

                Label thirtyThree = new Label("33");
                thirtyThree.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtyThree.setId(String.valueOf(33));
                mainGridPane.add(thirtyThree, 14, 7);

                Label thirtyFour = new Label("34");
                thirtyFour.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtyFour.setId(String.valueOf(34));
                mainGridPane.add(thirtyFour, 14, 8);

                Label thirtyFive = new Label("35");
                thirtyFive.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                thirtyFive.setId(String.valueOf(35));
                mainGridPane.add(thirtyFive, 14, 9);

                Label thirtySix = new Label("36");
                thirtySix.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtySix.setId(String.valueOf(36));
                mainGridPane.add(thirtySix, 14, 10);

                Label thirtySeven = new Label("37");
                thirtySeven.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtySeven.setId(String.valueOf(37));
                mainGridPane.add(thirtySeven, 14, 11);

                //BOTTOM SIDE
                Label thirtyEight = new Label("38");
                thirtyEight.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtyEight.setId(String.valueOf(38));
                mainGridPane.add(thirtyEight, 13, 11);

                Label thirtyNine = new Label("39");
                thirtyNine.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                thirtyNine.setId(String.valueOf(39));
                mainGridPane.add(thirtyNine, 12, 11);

                Label fourty = new Label("40");
                fourty.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                fourty.setId(String.valueOf(40));
                mainGridPane.add(fourty, 11, 11);

                Label fourtyOne = new Label("41");
                fourtyOne.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtyOne.setId(String.valueOf(41));
                mainGridPane.add(fourtyOne, 10, 11);

                Label fourtyTwo = new Label("42");
                fourtyTwo.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtyTwo.setId(String.valueOf(42));
                mainGridPane.add(fourtyTwo, 9, 11);

                Label fourtyThree = new Label("43");
                fourtyThree.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtyThree.setId(String.valueOf(43));
                mainGridPane.add(fourtyThree, 8, 11);

                Label fourtyFour = new Label("44");
                fourtyFour.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtyFour.setId(String.valueOf(44));
                mainGridPane.add(fourtyFour, 7, 11);

                Label fourtyFive = new Label("45");
                fourtyFive.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                fourtyFive.setId(String.valueOf(45));
                mainGridPane.add(fourtyFive, 6, 11);

                Label fourtySix = new Label("46");
                fourtySix.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtySix.setId(String.valueOf(46));
                mainGridPane.add(fourtySix, 5, 11);

                Label fourtySeven = new Label("47");
                fourtySeven.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtySeven.setId(String.valueOf(47));
                mainGridPane.add(fourtySeven, 4, 11);

                Label fourtyEight = new Label("48");
                fourtyEight.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtyEight.setId(String.valueOf(48));
                mainGridPane.add(fourtyEight, 3, 11);

                Label fourtyNine = new Label("49");
                fourtyNine.setStyle("-fx-font-size: 17px; -fx-font-weight: bold;");
                fourtyNine.setId(String.valueOf(49));
                mainGridPane.add(fourtyNine, 2, 11);

                Label fifthy = new Label("50");
                fifthy.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");
                fifthy.setId(String.valueOf(50));
                mainGridPane.add(fifthy, 1, 11);



            }
        }
    }



}
