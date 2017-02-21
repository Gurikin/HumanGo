package ru.biv.network;

import ru.biv.msgSystem.UserSession;
import ru.biv.utils.TextUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Игорь on 07.12.2016.
 */
public class ObjectRequestSender {
    private static CookieManager cookieManager = new CookieManager();
    static final String COOKIES_HEADER = "Set-Cookie";


    public static UserSession sendGetRequest(String endpoint, UserSession userSession) {
        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        if (endpoint.startsWith("http://")) {
            try
            {
                String urlStr = endpoint;
                //if (requestParameters != null && requestParameters.length () > 0)
                //{
                //    urlStr += "?" + requestParameters;
                //}
                CookieHandler.setDefault(cookieManager);
                URL url = new URL(urlStr);


                URLConnection conn = url.openConnection ();
                conn.setDoOutput(true);
                conn.setDoInput(true);


                //Put the cookie to the request
                if (cookieManager.getCookieStore().getCookies().size() > 0) {
                    System.out.println(TextUtils.join(";", cookieManager.getCookieStore().getCookies()));
                    // While joining the Cookies, use ',' or ';' as needed. Most of the servers are using ';'
                    conn.setRequestProperty("Cookie",
                            TextUtils.join(";", cookieManager.getCookieStore().getCookies()));
                }

                //Отправляем объект на сервер
                objectOutputStream = new ObjectOutputStream(conn.getOutputStream());
                objectOutputStream.writeObject(userSession);
                objectOutputStream.flush();
                objectOutputStream.close();

                //Get the cookie from the response
                Map<String, List<String>> headerFields = conn.getHeaderFields();
                List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
                if (cookiesHeader != null) {
                    for (String cookie : cookiesHeader) {
                        cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
                    }
                }

                //Send the request
                conn.connect();


                objectInputStream = new ObjectInputStream(conn.getInputStream());
                userSession = (UserSession) objectInputStream.readObject();
                objectInputStream.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return userSession;
    }
}
