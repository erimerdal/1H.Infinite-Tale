package ui;

import javafx.scene.layout.GridPane;

public class LoreManager {
    private LoreMenu menu;

    public LoreManager(GridPane loreMenuPane, InputManager inputManager) {
        menu = new LoreMenu(loreMenuPane, inputManager);
    }

    public void setLore(){
        String lore = "";
        lore += "This is a never-ending battle of four kingdoms. As the legend says: “If this war is destined to be finished, it will be \n" +
                "by the hands of the Chosen One.” But nobody knows which kingdom does this Chosen One belongs to, whether he\n" +
                "will bring peace to the four kingdoms or utter destruction for the other three. However, recent signs of the Chosen\n" +
                "One coming to change the balance of this war has been seen by many prophets by reading stars. Now is the time to end this \n" +
                "infinite tale for good.\n" +
                "\n" +
                "The Lightbringer: By the help of the God of Light and they raised the first Paladins. They have always been on the peace \n" +
                "and harmony side. They are protectors of the realm and stoppers of The Great Undead March.\n" +
                "\n" +
                "The Vengeance: The Vengeance is formed after the treason of Ironfist to Lightbringer. The Vengeance were among the \n" +
                "Lightbringer but after Ironfist invasion many of them were killed or enslaved. This made them angry against God of Light\n" +
                "and they pledged to kill every single follower of Ironfist before they stop.\n" +
                "\n" +
                "The Silence: In lands of The Silence, necromancy is a mastery to be thought to every mage. They are known for \n" +
                "bringing dead back to life and forming the biggest army ever created of the dead, which is also known as The\n" +
                "Great Undead March.\n" +
                "\n" +
                "The Ironfist: They were the first kingdom to declare war against another, they have always been on the aggressive side\n" +
                "and they believe that Ironfist is the rightful owner of the throne of four kingdoms. They take lands as trophy and enemies\n" +
                "as slaves. ";
    }

}
