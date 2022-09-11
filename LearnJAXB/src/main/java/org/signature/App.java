package org.signature;

import org.signature.model.Book;
import org.signature.model.Bookstore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class App {

    private static final String BOOKSTORE_XML = "bookstore.xml";

    public static void main(String[] args) {
        xmlToObject();
    }

    private static void xmlToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Bookstore.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Bookstore bookstore = (Bookstore) unmarshaller.unmarshal(new File(BOOKSTORE_XML));
            for (Book book : bookstore.getBooksList()) {
                System.out.println(book);
            }
        } catch (JAXBException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
