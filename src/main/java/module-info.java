module hr.algebra.thequacksofquedlinburg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.naming;
    requires java.xml;


    opens hr.algebra.thequacksofquedlinburg to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg;
    exports hr.algebra.thequacksofquedlinburg.controllers;
    opens hr.algebra.thequacksofquedlinburg.controllers to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.gameBoard;
    opens hr.algebra.thequacksofquedlinburg.gameBoard to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.gameBoard.enums;
    opens hr.algebra.thequacksofquedlinburg.gameBoard.enums to javafx.fxml;
    exports hr.algebra.thequacksofquedlinburg.utils;
    opens hr.algebra.thequacksofquedlinburg.utils to javafx.fxml;

    exports hr.algebra.thequacksofquedlinburg.chat to java.rmi;


}