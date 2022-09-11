package com.signature.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class ContactData {
    private static final ContactData instance = new ContactData();;

    private static final String CONTACTS_FILE = "contacts.xml";

    private static final String CONTACT = "contact";
    private static final String PROFILE = "profile";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String COMPANY = "company";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String PHONE_TYPE = "phone_type";
    private static final String EMAIL = "email";
    private static final String EMAIL_TYPE = "email_type";
    private static final String ADDRESS = "address";
    private static final String WEBSITE = "website";
    private static final String DATE = "date";
    private static final String DATE_TYPE = "date_type";
    private static final String RELATIONSHIP = "relationship";
    private static final String RELATION_TYPE = "relation_type";
    private static final String SIP = "sip";
    private static final String NOTES = "notes";
    private static final String ISFAVOURITE = "isFavourite";

    private ObservableList<Contact> contacts;

    private ContactData() {
        contacts = FXCollections.observableArrayList();
    }

    public static ContactData getInstance() {
        return instance;
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact item) {
        contacts.add(item);
    }

    public void deleteContact(Contact item) {
        contacts.remove(item);
    }

    public void loadContacts() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(CONTACTS_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Contact contact = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a contact item, we create a new contact
                    if (startElement.getName().getLocalPart().equals(CONTACT)) {
                        contact = new Contact();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(PROFILE)) {
                            event = eventReader.nextEvent();
                            contact.setProfile(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(FIRST_NAME)) {
                            event = eventReader.nextEvent();
                            contact.setFirstName(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(LAST_NAME)) {
                        event = eventReader.nextEvent();
                        contact.setLastName(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(COMPANY)) {
                        event = eventReader.nextEvent();
                        contact.setCompany(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PHONE_NUMBER)) {
                        event = eventReader.nextEvent();
                        contact.setPhoneNumber(event.asCharacters().getData());
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(PHONE_TYPE)) {
                            event = eventReader.nextEvent();
                            contact.setPhoneType(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(EMAIL)) {
                            event = eventReader.nextEvent();
                            contact.setEmail(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(EMAIL_TYPE)) {
                        event = eventReader.nextEvent();
                        contact.setEmailType(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(ADDRESS)) {
                        event = eventReader.nextEvent();
                        contact.setAddress(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(WEBSITE)) {
                        event = eventReader.nextEvent();
                        contact.setWebsite(event.asCharacters().getData());
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(DATE)) {
                            event = eventReader.nextEvent();
                            contact.setDate(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(DATE_TYPE)) {
                            event = eventReader.nextEvent();
                            contact.setDateType(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(RELATIONSHIP)) {
                        event = eventReader.nextEvent();
                        contact.setRelation(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(RELATION_TYPE)) {
                        event = eventReader.nextEvent();
                        contact.setRelationType(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SIP)) {
                        event = eventReader.nextEvent();
                        contact.setSip(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(NOTES)) {
                        event = eventReader.nextEvent();
                        contact.setNotes(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(ISFAVOURITE)) {
                        event = eventReader.nextEvent();
                        contact.setFavourite(event.asCharacters().getData().equals("true"));
                        continue;
                    }
                }

                // If we reach the end of a contact element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(CONTACT)) {
                        contacts.add(contact);
                    }
                }
            }
        }
        catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveContacts() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(CONTACTS_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement contactsStartElement = eventFactory.createStartElement("",
                    "", "contacts");
            eventWriter.add(contactsStartElement);
            eventWriter.add(end);

            for (Contact contact: contacts) {
                saveContact(eventWriter, eventFactory, contact);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "contacts"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Contacts file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveContact(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Contact contact)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create contact open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", CONTACT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, PROFILE, contact.getProfile());
        createNode(eventWriter, FIRST_NAME, contact.getFirstName());
        createNode(eventWriter, LAST_NAME, contact.getLastName());
        createNode(eventWriter, COMPANY, contact.getCompany());
        createNode(eventWriter, PHONE_NUMBER, contact.getPhoneNumber());
        createNode(eventWriter, PHONE_TYPE, contact.getPhoneType());
        createNode(eventWriter, EMAIL, contact.getEmail());
        createNode(eventWriter, EMAIL_TYPE, contact.getEmailType());
        createNode(eventWriter, ADDRESS, contact.getAddress());
        createNode(eventWriter, WEBSITE, contact.getWebsite());
        createNode(eventWriter, DATE, contact.getDate());
        createNode(eventWriter, DATE_TYPE, contact.getDateType());
        createNode(eventWriter, RELATIONSHIP, contact.getRelation());
        createNode(eventWriter, RELATION_TYPE, contact.getRelationType());
        createNode(eventWriter, SIP, contact.getSip());
        createNode(eventWriter, NOTES, contact.getNotes());
        if (contact.isFavourite()) {
            createNode(eventWriter, ISFAVOURITE, "true");
        } else {
            createNode(eventWriter, ISFAVOURITE, "false");
        }


        eventWriter.add(eventFactory.createEndElement("", "", CONTACT));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}