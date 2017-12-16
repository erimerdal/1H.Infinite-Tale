package ui;

import game.FactionData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InformationHeader {
    private FlowPane header;
    private InputManager inputManager;
    private FactionData info;
    private Button endTurnButton;
    private Button settingsButton;
    private Button mapViewButtton;
    private Label treasury;
    private Label netIncome;
    private Label totalProv;
    private Label totalUnit;

    public InformationHeader(FlowPane header, InputManager inputManager) {
        this.header = header;
        this.inputManager = inputManager;
        info = new FactionData();

        treasury = new Label("Treasury : 0");
        netIncome = new Label("Net Income : 0");
        totalProv = new Label("Provinces : 0");
        totalUnit = new Label("Units : 0");


        final Pane spacer = new Pane();
        HBox.setHgrow(
                spacer,
                Priority.SOMETIMES
        );
        final Pane spacer2 = new Pane();
        HBox.setHgrow(
                spacer2,
                Priority.SOMETIMES
        );

        endTurnButton = new Button("End Turn");
        endTurnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.endTurn();
                }
            }
        });

        mapViewButtton = new Button("Show Geographic View");
        mapViewButtton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.swapMapView();
                    if(mapViewButtton.getText().equals("Show Geographic View"))
                        mapViewButtton.setText("Hide Geographic View");
                    else
                        mapViewButtton.setText("Show Geographic View");
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

        ToolBar toolBar = new ToolBar(
                settingsButton,
                spacer,
                treasury,
                netIncome,
                totalProv,
                totalUnit,
                spacer2,
                mapViewButtton,
                endTurnButton
        );
        toolBar.setMinWidth(header.getWidth());
        header.getChildren().add(toolBar);

    }

    public void update(FactionData factionData) {
        if(factionData == null)
            return;

        info = factionData;
        treasury.setText("Treasury : " + info.treasury);
        netIncome.setText("Net Income : " + (info.income - info.expense));
        totalProv.setText("Provinces : " + info.totalProvinces);
        totalUnit.setText("Units : " + info.totalUnits);

    }
}
