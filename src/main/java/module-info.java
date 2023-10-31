module hr.algebra.thequacksofquedlinburg {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.algebra.thequacksofquedlinburg to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg;
    exports hr.algebra.thequacksofquedlinburg.Conrollers;
    opens hr.algebra.thequacksofquedlinburg.Conrollers to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.gameBoard;
    opens hr.algebra.thequacksofquedlinburg.gameBoard to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.gameBoard.enums;
    opens hr.algebra.thequacksofquedlinburg.gameBoard.enums to javafx.fxml;
}