package ru.biv.utils;

import java.net.HttpCookie;
import java.util.List;

/**
 * Created by Игорь on 15.12.2016.
 */
public class TextUtils {
    public static String join(String separator, List<HttpCookie> list) {
        String result = "";
        for (HttpCookie event : list) {
            result += event.getValue()+separator;
        }
        return "";
    }
}
