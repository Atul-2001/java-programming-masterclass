package org.signature;

import org.signature.model.Book;
import org.signature.model.Bookstore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final String BOOKSTORE_XML = "bookstore.xml";

    public static void main(String[] args) throws JAXBException {
        List<Book> bookList = new ArrayList<>();
        Random random = new Random();

        String[] authors = new String[] {"Dennis Ritchie", "James Gosling", "Joshua Bloch", "Muncie Premchand", "Mahadevi Verma"};
        String[] publishers = new String[] {"Amazon", "flipkart", "Pristine", "Arihent"};

        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.setIsbn(random.nextInt(999) + "-" + random.nextInt(1999999999));
            book.setName("Book " + i);
            book.setAuthor(authors[random.nextInt(4)]);
            book.setPublisher(publishers[random.nextInt(3)]);
            bookList.add(book);
        }

        Bookstore bookstore = new Bookstore();
        bookstore.setName("Bharat Book Store");
        bookstore.setLocation("Bharat");
        bookstore.setBookList(bookList);

        objectToXML(bookstore);
    }

    private static void objectToXML(Bookstore bookstore) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Bookstore.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //write to stream
        marshaller.marshal(bookstore, System.out);

        //write to file
        marshaller.marshal(bookstore, new File(BOOKSTORE_XML));
    }
}
