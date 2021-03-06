package ui;

import game.TileInfo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TileInfoWindow {
    private GridPane tileInfoPane;
    private InputManager inputManager;
    private TileInfo tileInfo;
    private Label provinceId;
    private Label tileId;
    private Label owner;
    private Label soldiers;
    private TextField recruitField;
    private Button recruitButton;

    public TileInfoWindow(GridPane tileInfoPane, InputManager inputManager) {
        this.tileInfoPane = tileInfoPane;
        this.inputManager = inputManager;

        tileInfoPane.setPadding(new Insets(15));
        tileInfoPane.setHgap(10);
        tileInfoPane.setVgap(10);
        tileInfoPane.setAlignment(Pos.TOP_LEFT);

        provinceId = new Label("?");
        tileId = new Label("?");
        owner = new Label("???");
        soldiers = new Label("??");
        recruitField = new TextField();
        recruitButton = new Button("Recruit");
        recruitButton.setDisable(true);

        tileInfoPane.add(new Label("Province"),0, 1);
        tileInfoPane.add(new Label("Tile"),0, 2);
        tileInfoPane.add(new Label("Soldiers"),0, 3);
        tileInfoPane.add(new Label(":"),1, 1);
        tileInfoPane.add(new Label(":"),1, 2);
        tileInfoPane.add(new Label(":"),1, 3);

        tileInfoPane.add(owner,1,0);
        tileInfoPane.add(provinceId,2,1);
        tileInfoPane.add(tileId,2,2);
        tileInfoPane.add(soldiers,2,3);

        tileInfoPane.add(recruitField,0,6, 2, 1);
        tileInfoPane.add(recruitButton,2,6);

        recruitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    int num = 0;
                    try {
                        num = Integer.parseInt(recruitField.getText());
                    } catch(NumberFormatException e){}

                    if(num > 0)
                        inputManager.recruitUnits(num, tileInfo.tile.getId());
                }
            }
        });
    }

    public void update(TileInfo tileInfo) {
        this.tileInfo = tileInfo;
        owner.setText(tileInfo.owner);
        provinceId.setText(tileInfo.tile.getOwner().getId() + "");
        tileId.setText(tileInfo.tile.getId() + "");
        if(tileInfo.isVisible)
        {
            soldiers.setText(tileInfo.tile.getTroops().size() + "");
            if(tileInfo.owner.equals("Player")) {
                if (tileInfo.tile.getTroops().size() < 1) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"0poptown.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else if (tileInfo.tile.getTroops().size() < 2) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"1poptown.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else if (tileInfo.tile.getTroops().size() < 3) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"2poptown.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else if (tileInfo.tile.getTroops().size() < 4) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"3poptown.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else {
                    tileInfoPane.setStyle("-fx-background-image: url(\"bigpoptown.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                }

            }
            else
            {
                if (tileInfo.tile.getTroops().size() < 1) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"0poptownenemy.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else if (tileInfo.tile.getTroops().size() < 2) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"1poptownenemy.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else if (tileInfo.tile.getTroops().size() < 3) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"2poptownenemy.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else if (tileInfo.tile.getTroops().size() < 4) {
                    tileInfoPane.setStyle("-fx-background-image: url(\"3poptownenemy.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                } else {
                    tileInfoPane.setStyle("-fx-background-image: url(\"bigpoptownenemy.jpg\");-fx-background-repeat: stretch;   \n" +
                            "    -fx-background-size: 200 100;\n" +
                            "    -fx-background-position: center center;\n" +
                            "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
                }
            }
        }

        else
        {
            soldiers.setText("??");

            tileInfoPane.setStyle("-fx-background-image: url(\"unknownpoptown.jpg\");-fx-background-repeat: stretch;   \n" +
                    "    -fx-background-size: 200 100;\n" +
                    "    -fx-background-position: center center;\n" +
                    "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
        }
        if(tileInfo.tile.getOwner().getOwnerId() == 0)
            recruitButton.setDisable(false);
        else
            recruitButton.setDisable(true);
    }
}
