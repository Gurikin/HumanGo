package ru.biv.model;

/**
 * Created by Игорь on 20.12.2016.
 */
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

public class StoneWhite extends Parent implements Stone{

    private double x;
    private double y;
    private boolean stateOnBoard;
    private ImageView imageView;

    public StoneWhite(double x, double y) {
        imageView = new ImageView();
        getChildren().add(imageView);
        imageView.setImage(Config.getImages().get(Config.IMAGE_STONE_WHITE));
        this.x = x;
        this.y = y;
        setTranslateX(x-Config.STONE_DIAMETER/2);
        setTranslateY(y-Config.STONE_DIAMETER/2);
        setMouseTransparent(true);
    }

    @Override
    public void toCatch() {
        if (stateOnBoard) {
            stateOnBoard = false;
        }
    }
}
