module hr.algebra.thequacksofquedlinburg {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.algebra.thequacksofquedlinburg to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg;
    exports hr.algebra.thequacksofquedlinburg.Controllers;
    opens hr.algebra.thequacksofquedlinburg.Controllers to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.GameBoard;
    opens hr.algebra.thequacksofquedlinburg.GameBoard to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.GameBoard.enums;
    opens hr.algebra.thequacksofquedlinburg.GameBoard.enums to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.Utils;
    opens hr.algebra.thequacksofquedlinburg.Utils to javafx.fxml;
}