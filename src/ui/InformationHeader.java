package ui;

import game.FactionData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class InformationHeader {
    private FlowPane header;
    private InputManager inputManager;
    private FactionData info;
    private Button endTurnButton;
    private Button settingsButton;

    public InformationHeader(FlowPane header, InputManager inputManager) {
        this.header = header;
        this.inputManager = inputManager;
        info = new FactionData();

        endTurnButton = new Button("End Turn");
        endTurnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.endTurn();
                }
            }
        });

        settingsButton = new Button("Settings");
        settingsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.openSettings();
                }
            }
        });
        header.getChildren().add(endTurnButton);
        header.getChildren().add(settingsButton);
    }

    public void update(FactionData factionData) {
        info = factionData;
    }
}
