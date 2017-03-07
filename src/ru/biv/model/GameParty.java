package ru.biv.model;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.biv.msgSystem.UserSession;
import ru.biv.utils.CoordHelper;

/**
 * Created by Игорь on 23.12.2016.
 */
public class GameParty extends Parent {

    private Group group;
    private Stone currentStone;
    private ImageView background;

    public GameParty() {
        group = new Group();
        getChildren().add(group);
        initContent();
    }

    public void start() {
        group.getChildren().get(0).requestFocus();
    }

    private void initContent() {

        background = new ImageView();
        background.setFocusTraversable(true);
        background.setImage(Config.getImages().get(Config.IMAGE_BACKGROUND));
        background.setFitWidth(Config.SCREEN_WIDTH);
        background.setFitHeight(Config.SCREEN_HEIGHT);
        background.setTranslateX(Config.WINDOW_BORDER);
        background.setTranslateY(Config.WINDOW_BORDER);


        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                /*if ((ke.getCode() == KeyCode.POWER) || (ke.getCode() == KeyCode.X)) {
                    Platform.exit();
                }
                if (state == BALL_CATCHED && (ke.getCode() == KeyCode.SPACE ||
                        ke.getCode() == KeyCode.ENTER || ke.getCode() == KeyCode.PLAY)) {
                    state = PLAYING;
                }
                if (state == GAME_OVER) {
                    mainFrame.changeState(MainFrame.SPLASH);
                }
                if (state == PLAYING && ke.getCode() == KeyCode.Q) {
                    // Lost life
                    lostLife();
                    return;
                }
                if ((ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.TRACK_PREV)) {
                    batDirection = - Config.BAT_SPEED;
                }
                if ((ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.TRACK_NEXT)) {
                    batDirection = Config.BAT_SPEED;
                }*/
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                /*if (ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.RIGHT ||
                        ke.getCode() == KeyCode.TRACK_PREV || ke.getCode() == KeyCode.TRACK_NEXT) {
                    batDirection = 0;
                }*/
            }
        });
        group.getChildren().add(background);
    }
    public void addStone(double x, double y, UserSession userSession) {
        if (userSession.getLastStep().equals(null)) {
            return;
        } else {
            if (userSession.getStone().equals("BLACK")) {
                currentStone = new StoneBlack(x,y);
                group.getChildren().add((StoneBlack)currentStone);
            } else {
                currentStone = new StoneWhite(x,y);
                group.getChildren().add((StoneWhite)currentStone);
            }
        }
        double[] step = new double[2];
        step[0] = x;
        step[1] = y;
        userSession.setLastStep(step);

    }

    public void setMousePress(UserSession userSession) {
        background.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                double x = CoordHelper.getCoordinatesCreate(me.getX());
                double y = CoordHelper.getCoordinatesCreate(me.getY());
                if (x != -1 && y != -1) {
                    System.out.println("x: " + me.getX() + "\ty: " + me.getY());
                    System.out.println("newX: " + x + "\tnewY: " + y);
                    addStone(x,y,userSession);
                }
            }
        });
    }
}
