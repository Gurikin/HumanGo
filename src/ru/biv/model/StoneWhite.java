package ru.biv.model;

/**
 * Created by Игорь on 20.12.2016.
 */
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

public class StoneWhite extends Parent implements Stone{

    private int x;
    private int y;
    private boolean stateOnBoard;
    private ImageView imageView;

    public StoneWhite(int x, int y) {
        imageView = new ImageView();
        getChildren().add(imageView);
        imageView.setImage(Config.getImages().get(Config.IMAGE_STONE_WHITE));
        this.x = x;
        this.y = y;
        setMouseTransparent(true);
    }

    @Override
    public void toCatch() {
        if (stateOnBoard) {
            stateOnBoard = false;
        }
    }
}
