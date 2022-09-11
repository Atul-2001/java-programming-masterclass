package com.signature;

import java.util.ArrayList;

public class MobilePhone {

    ArrayList<Contacts> contacts;

    public MobilePhone() {
        this.contacts = new ArrayList<Contacts>();
    }

    public boolean addContact(Contacts contact) {
        if (findContact(contact) > -1) {
            System.out.println("Contact " + contact.getContactName() + " already exist!");
            return false;
        } else {
            contacts.add(contact);
            return true;
        }
    }

    public boolean updateContact(Contacts oldContact, Contacts newContact) {
        int index = findContact(oldContact);

        if (index == -1) {
            System.out.println(oldContact.getContactName() + " was not found!");
            return false;
        } else if (findContact(newContact.getContactName()) > -1) {
            System.out.println("Contact with name " + newContact.getContactName() + " already exists.  Update was not successful.");
            return false;
        } else {
            contacts.set(index,newContact);
            return true;
        }
    }

    public boolean deleteContact(String name) {

        int index = findContact(name);
        if (index > -1) {
            contacts.remove(index);
            return true;
        } else {
            System.out.println("Contact " + name + " not found!");
            return false;
        }
    }

    public Contacts searchContact(String name) {

        int index = findContact(name);
        if (index > -1) {
            return this.contacts.get(index);
        } else {
            return null;
        }
    }

    private int findContact(Contacts contact) {
        return this.contacts.indexOf(contact);
    }

    private int findContact(String name) {

        for (int i = 0; i < this.contacts.size(); i++) {
            Contacts test = contacts.get(i);

            if (test.getContactName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void printAllContact() {
        System.out.println("\nContact List");
        for(int i=0; i<this.contacts.size(); i++) {
            System.out.println((i+1) + ". " +
                    this.contacts.get(i).getContactName() + " -> " +
                    this.contacts.get(i).getContactNumber());
        }
    }
}
