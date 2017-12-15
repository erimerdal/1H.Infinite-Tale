package ui;

import javafx.scene.layout.GridPane;

public class CreditsManager {


        private CreditsMenu menu;

        public CreditsManager(GridPane creditsPane, InputManager inputManager) {
            menu = new CreditsMenu(creditsPane, inputManager);
        }

}
