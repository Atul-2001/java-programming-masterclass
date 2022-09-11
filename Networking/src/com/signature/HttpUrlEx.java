package com.signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlEx {

    public static void main(String[] args) {
        try {
//            URL url = new URL("http://www.example.org/page.html");
            URL url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=india");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setConnectTimeout(15000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            if (responseCode != 200) {
                System.out.println("Error loading webpage");
                System.out.println(connection.getResponseMessage());
                return;
            }

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
            inputReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
