package com.signature;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
//            URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
//            URI uri = new URI("https://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            URI baseUri = new URI("http://username:password@myserver.com:5000");
            URI uri1 = new URI("/catalogue/phones?os=android#samsung");
            URI uri2 = new URI("/catalogue/tvs?os=samsung");
            URI uri3 = new URI("/stores/locations?zip=224201");

            URI resolvedUri1 = baseUri.resolve(uri1);
            URI resolvedUri2 = baseUri.resolve(uri2);
            URI resolvedUri3 = baseUri.resolve(uri3);

//            URL url = uri.toURL();
            URL url1 = resolvedUri1.toURL();
            System.out.println(url1);
            URL url2 = resolvedUri2.toURL();
            System.out.println(url2);
            URL url3 = resolvedUri3.toURL();
            System.out.println(url3);

//            URI uritemp  url1.toURI();

            URI relativisedUri = baseUri.relativize(resolvedUri1);
            System.out.println(relativisedUri);


//            System.out.println("Schema = " + uri.getScheme());
//            System.out.println("Schema-specific part = " + uri.getSchemeSpecificPart());
//            System.out.println("Authority = " + uri.getAuthority());
//            System.out.println("User info = " + uri.getUserInfo());
//            System.out.println("Host = " + uri.getHost());
//            System.out.println("Port = " + uri.getPort());
//            System.out.println("Path = " + uri.getPath());
//            System.out.println("Query = " + uri.getQuery());
//            System.out.println("Fragments = " + uri.getFragment());

        } catch (URISyntaxException e) {
            System.out.println("URI bad syntax Exception : " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Url malformed exception : " + e.getMessage());
        }
    }
}
