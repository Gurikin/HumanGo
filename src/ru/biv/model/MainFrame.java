package ru.biv.model;

import javafx.scene.Group;

/**
 * Created by Игорь on 15.01.2017.
 */
public class MainFrame {
    // Instance of scene root node
    private Group root;

    // Instance of gameParty (if exists)
    private GameParty gameParty;

    public MainFrame(Group root) {
        this.root = root;
    }

    public void startGame() throws NullPointerException {
        try {
            gameParty = new GameParty();
            root.getChildren().add(gameParty);
            gameParty.start();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
