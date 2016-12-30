package ru.biv.network;

import ru.biv.utils.TextUtils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;



import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


/**
 * Created by Игорь on 25.12.2016.
 */
public class JSONRequestSender {
    private static CookieManager cookieManager = new CookieManager();
    //private static String requestParameters = "";
    static final String COOKIES_HEADER = "Set-Cookie";
    private static final String LOG_URL = "http://localhost:8080/hello";

    public static String sendGetRequest(String endpoint, String requestParameters) {
        //RequestSender.requestParameters = requestParameters;
        String result = null;
        if (endpoint.startsWith("http://"))
        {
            try
            {
                String urlStr = endpoint;
                if (requestParameters != null && requestParameters.length () > 0)
                {
                    urlStr += "?" + requestParameters;
                }
                CookieHandler.setDefault(cookieManager);
                URL url = new URL(urlStr);

                try (InputStream inputStream = url.openStream();
                    JsonReader jsonReader = Json.createReader(inputStream)) {
                    JsonObject responseObj = jsonReader.readObject();
                    JsonArray responseResults = responseObj.getJsonArray("data");
                    for (JsonObject responseResult : responseResults.getValuesAs(JsonObject.class)) {
                        System.out.println(responseResult.toString());
                        result += responseResult.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                URLConnection conn = url.openConnection ();

                //Put the cookie to the request
                if (cookieManager.getCookieStore().getCookies().size() > 0) {
                    System.out.println(TextUtils.join(";", cookieManager.getCookieStore().getCookies()));
                    // While joining the Cookies, use ',' or ';' as needed. Most of the servers are using ';'
                    conn.setRequestProperty("Cookie",
                            TextUtils.join(";", cookieManager.getCookieStore().getCookies()));
                }

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

                //Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null)
                {
                    sb.append(line);
                }
                rd.close();
                result = sb.toString();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}
