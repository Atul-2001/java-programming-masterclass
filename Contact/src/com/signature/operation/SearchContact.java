package com.signature.operation;

import com.signature.DataModel.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchContact {

    public static ObservableList<Contact> search(List<Contact> contacts, String str) {
        ObservableList<Contact> searchResult = FXCollections.observableArrayList();
        LinkedList<Contact> sortedContactList = new LinkedList<>(contacts);
        ListIterator<Contact> iterator = sortedContactList.listIterator();
        format(str);


        int len, strLen;

        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            String firstName = contact.getFirstName();
            len = firstName.length();
            strLen = str.length();
            if (strLen > len) {
                len = strLen - (strLen - len);
            } else {
                len = strLen;
            }
            if (firstName.substring(0, len).compareToIgnoreCase(str) == 0) {
                searchResult.add(contact);
                iterator.remove();
            }
        }

        iterator = sortedContactList.listIterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            String lastName = contact.getLastName();
            len = lastName.length();
            strLen = str.length();
            if (strLen > len) {
                len = strLen - (strLen - len);
            } else {
                len = strLen;
            }
            if (lastName.substring(0, len).compareToIgnoreCase(str) == 0) {
                searchResult.add(contact);
                iterator.remove();
            }
        }

        iterator = sortedContactList.listIterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            String phoneNumber = contact.getPhoneNumber();
            len = phoneNumber.length();
            strLen = str.length();
            if (strLen > len) {
                len = strLen - (strLen - len);
            } else {
                len = strLen;
            }
            if (phoneNumber.substring(0, len).compareToIgnoreCase(str) == 0) {
                searchResult.add(contact);
                iterator.remove();
            }
        }

        iterator = sortedContactList.listIterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            String email = contact.getEmail();
            len = email.length();
            strLen = str.length();
            if (strLen > len) {
                len = strLen - (strLen - len);
            } else {
                len = strLen;
            }
            if (email.substring(0, len).compareToIgnoreCase(str) == 0) {
                searchResult.add(contact);
                iterator.remove();
            }
        }

        iterator = sortedContactList.listIterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            String name = contact.getFirstName() + " " + contact.getLastName();
            len = name.length();
            strLen = str.length();
            if (strLen > len) {
                len = strLen - (strLen - len);
            } else {
                len = strLen;
            }
            if (name.substring(0, len).compareToIgnoreCase(str) == 0) {
                searchResult.add(contact);
                iterator.remove();
            }
        }

        return searchResult;
    }

    private static String format(String str) {
        LinkedList<String> keys = new LinkedList<>(Arrays.asList(str.split("")));
        AtomicInteger spaceCount = new AtomicInteger(0);
        AtomicBoolean notWhiteSpace = new AtomicBoolean(false);

        keys.removeIf(key -> {
            if (Character.isLetterOrDigit(key.charAt(0))) {
                notWhiteSpace.set(true);
                return false;
            } else if (Character.isWhitespace(key.charAt(0))) {
                if (notWhiteSpace.get()) {
                    spaceCount.set(1);
                    notWhiteSpace.set(false);
                } else {
                    spaceCount.incrementAndGet();
                    notWhiteSpace.set(false);
                }
                return spaceCount.get() > 1;
            } else if (key.compareToIgnoreCase("@") == 0) {
                notWhiteSpace.set(true);
                return false;
            } else if (key.compareToIgnoreCase("_") == 0) {
                notWhiteSpace.set(true);
                return false;
            } else {
                return true;
            }
        });

        ListIterator<String> iterator = keys.listIterator();
        String formattedString  = "";
        while (iterator.hasNext()) {
            formattedString = formattedString.concat(iterator.next());
        }
        System.out.println(formattedString);
        return formattedString;
    }
}
