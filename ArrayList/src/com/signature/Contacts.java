package com.signature;

import java.util.ArrayList;

public class Contacts {

    private ArrayList<String> contactName = new ArrayList<String>();
    private ArrayList<String> contactNumber = new ArrayList<String>();

    public void addContact(String cName, String cNumber) {
        if (searchContact(cName) > -1) {
            System.out.println("Contact " + cName + " already exist.");
        } else {
            contactName.add(cName);
            contactNumber.add(cNumber);
        }
    }

    public void updateContact(String cName, String newNumber) {
        int index = searchContact(cName);

        if (index > -1) {
            contactNumber.add(index, newNumber);
            System.out.println("\tContact Updated Successfully!\n");
        } else {
            System.out.println("\tFailed to update. Contact " + cName + " does not exist!\n");
        }
    }

    public void deleteContact(String cName) {

        int index = searchContact(cName);
        if (index > -1) {
            contactName.remove(index);
            contactNumber.remove(index);
            System.out.println("\tContact Deleted Successfully!\n");
        } else {
            System.out.println("\tContact " + cName + " does not exist!\n");
        }
    }

    public void displayContact(String cName) {

        int index = searchContact(cName);
        if (index > -1) {
            System.out.println("\tName  : "+contactName.get(index));
            System.out.println("\tPhone : "+contactNumber.get(index) + "\n");
        } else {
            System.out.println("\tNo contact found!\n");
        }
    }

    private int searchContact(String cName) {
        return contactName.indexOf(cName);
    }

    public void contact() {
        System.out.println();
        if (contactName.size() <= 0) {
            System.out.println("No contact available!\n");
        }
        for (int i = 0; i < contactName.size(); i++) {
            System.out.println("Name  : " + contactName.get(i));
            System.out.println("Phone : " + contactNumber.get(i) + "\n");
        }
    }
}
