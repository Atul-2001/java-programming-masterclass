package com.signature;

import javax.swing.*;
import java.util.Scanner;

public class MobilePhone {

    private static Scanner scan = new Scanner(System.in);

    Contacts contact = new Contacts();

    public void menu() {

        System.out.println("\tContacts");
        System.out.println("1. All Contacts");
        System.out.println("2. Add + new Contact");
        System.out.println("3. Update Contact");
        System.out.println("4. Remove Contact");
        System.out.println("5. Search Contact");
        System.out.println("6. Exit\n");
    }

    public void allContacts() {
        contact.contact();
    }

    public void newContact() {
        System.out.println("\nEnter Contact Name   : ");
        String name = scan.nextLine();
        System.out.println("Enter Contact Number : ");
        String phone = scan.nextLine();
        contact.addContact(name,phone);
    }

    public void update() {
        System.out.println("\nEnter Contact Name   : ");
        String name = scan.nextLine();
        System.out.println("Enter New Contact Number : ");
        String phone = scan.nextLine();
        contact.updateContact(name,phone);
    }

    public void delete() {
        System.out.println("\nEnter Contact Name   : ");
        String name = scan.nextLine();
        contact.deleteContact(name);
    }

    public void search() {
        System.out.println("\nEnter Contact Name   : ");
        String name = scan.nextLine();
        contact.displayContact(name);
    }

}
