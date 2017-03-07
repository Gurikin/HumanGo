package ru.biv.model;

/**
 * Created by Игорь on 21.12.2016.
 */
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class Config {

    public static final Duration ANIMATION_TIME = Duration.millis(40);

    public static final String IMAGE_DIR = "/ru/biv/images/";

    public static final int WINDOW_BORDER = 50; // on desktop platform
    public static final int TITLE_BAR_HEIGHT = 19; // on desktop platform
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;

    public static final int INFO_TEXT_SPACE = 10;

    // Game field info
    public static final int STONE_DIAMETER = 76;
    public static final int SHADOW_DIAMETER = 48;

    //public static final int FIELD_WIDTH = FIELD_BRICK_IN_ROW * BRICK_WIDTH;
    //public static final int FIELD_HEIGHT = FIELD_WIDTH;
    //public static final int FIELD_Y = SCREEN_HEIGHT - FIELD_HEIGHT;

    public static final int IMAGE_BACKGROUND = 0;
    public static final int IMAGE_STONE_BLACK = 1;
    public static final int IMAGE_STONE_WHITE = 2;
    //public static final int IMAGE_LOGO = 3;
    //public static final int IMAGE_READY = 4;
    //public static final int IMAGE_GAMEOVER = 5;

    private static final String[] IMAGES_NAMES = new String[] {
            "background.png",
            "stoneBlack.png",
            "stoneWhite.png"
            //"logo.png",
            //"ready.png",
            //"gameover.png"
    };

    private static ObservableList<Image> images = javafx.collections.FXCollections.<Image>observableArrayList();

    public static ObservableList<Image> getImages() {
        return images;
    }

    public static void initialize() {
        for (String imageName : IMAGES_NAMES) {

            try {
                Image image = new Image(Config.class.getResourceAsStream(IMAGE_DIR+imageName));
                images.add(image);
                System.out.println(IMAGE_DIR+imageName);
            } catch (Exception e) {
                System.out.println("Image "+imageName+" not found");
            }
        }
    }
    private Config() {}

}