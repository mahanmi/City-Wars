package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label javaF;
    @FXML
    private Label kir;

    @FXML
    protected void onHelloButtonClick() {
        javaF.setText("Kir dovom to konet raft");
    }

    @FXML
    protected void onKir() {
        kir.setText("Kir Bokhor");
    }
}