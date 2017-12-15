package ui;

import game.FactionData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FactionInfoWindow {
    private GridPane factionInfoPane;
    private InputManager inputManager;
    private FactionData factionData;
    private Label name;
    private Label income;
    private Label expense;
    private Label soldiers;

    public FactionInfoWindow(GridPane factionInfoPane, InputManager inputManager) {
        this.factionInfoPane = factionInfoPane;
        this.inputManager = inputManager;

        factionInfoPane.setPadding(new Insets(15));
        factionInfoPane.setHgap(10);
        factionInfoPane.setVgap(10);
        factionInfoPane.setAlignment(Pos.TOP_LEFT);

        name = new Label("???");
        income = new Label("?");
        expense = new Label("?");
        soldiers = new Label("??");

        factionInfoPane.add(new Label("Income"),0, 1);
        factionInfoPane.add(new Label("Expense"),0, 2);
        factionInfoPane.add(new Label("Soldiers"),0, 3);
        factionInfoPane.add(new Label(":"),1, 1);
        factionInfoPane.add(new Label(":"),1, 2);
        factionInfoPane.add(new Label(":"),1, 3);

        factionInfoPane.add(name,1,0);
        factionInfoPane.add(income,2,1);
        factionInfoPane.add(expense,2,2);
        factionInfoPane.add(soldiers,2,3);
    }

    public void update(FactionData factionData) {
        if(factionData == null)
            return;

        this.factionData = factionData;
        name.setText(factionData.name);
        if(factionData.id == 0) {
            income.setText(factionData.income + "");
            expense.setText(factionData.expense + "");
            soldiers.setText(factionData.totalUnits + "");


        }
        else {
            income.setText("?");
            expense.setText("?");
            soldiers.setText("??");
        }
    }
}
