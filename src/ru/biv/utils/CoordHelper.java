package ru.biv.utils;

import ru.biv.model.Config;

/**
 * Created by Игорь on 24.12.2016.
 */
public class CoordHelper {
    public static double getCoordinatesCreate(double coord) {
        double newCoord = 0D;
        if ((coord % 100) <= 30) {
            System.out.println((coord % 100));
            newCoord = coord - ((coord % 100)) + Config.WINDOW_BORDER;//.STONE_DIAMETER/0.9/2;
            return newCoord;
        }
        if ((coord % 100) >= 70) {
            newCoord = coord + (100-(coord % 100)) + Config.WINDOW_BORDER;//.STONE_DIAMETER/0.9/2;
            return newCoord;
        }
        return -1.0;

    }
}
