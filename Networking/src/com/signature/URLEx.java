package com.signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLEx {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.example.org");

            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();

//            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
//            BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line = input.readLine();
//            while (line != null) {
//                System.out.println(line);
//                line = input.readLine();
//            }
//            input.close();

            Map<String , List<String>> headerFields = urlConnection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println("--key : " + key);
                for (String str : value) {
//                    System.out.println("value : " + str);
                    System.out.println("value : " + value);
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("Url Exception : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }
}
