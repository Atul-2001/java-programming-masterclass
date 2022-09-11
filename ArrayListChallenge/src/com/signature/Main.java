package com.signature;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static MobilePhone mobile = new MobilePhone();

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();

        while (!quit){
            System.out.print("\nEnter action: (6 to show available actions) : ");
            int action = input.nextInt();
            input.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;

                case 1:
                    mobile.printAllContact();
                    break;

                case 2:
                    addContact();
                    break;

                case 3:
                    update();
                    break;

                case 4:
                    delete();
                    break;

                case 5:
                    search();
                    break;

                case 6:
                    printActions();
                    break;

                default:
                    System.out.println("Choose correct option...");
                    break;
            }
        }
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

    private static void printActions() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - Shutdown\n" +
                           "1  - All Contacts\n" +
                           "2  - Add + New Contact\n" +
                           "3  - Update Contact\n" +
                           "4  - Remove Contact\n" +
                           "5  - Search Contact\n" +
                           "6  - list Options.");
        System.out.println("Choose your action: ");
    }

    public static void addContact() {

        System.out.print("\nEnter Contact Name   : ");
        String name = input.nextLine();
        System.out.print("Enter contact number : ");
        String phone = input.nextLine();

        if (mobile.addContact(Contacts.createContact(name,phone))) {
            System.out.println("Contact Added Successfully!");
        } else {
            System.out.println("Failed to create contact!");
        }
    }

    public static void update() {

        System.out.print("\nEnter old contact name : ");
        String name = input.nextLine();

        Contacts oldContact = mobile.searchContact(name);

        if (oldContact == null) {
            System.out.println("Failed to Update. Contact " + name + " not found!");
            return;
        } else {

            System.out.print("\nEnter new contact name   : ");
            name = input.nextLine();
            System.out.print("Enter new contact number : ");
            String phone = input.nextLine();

            Contacts newContact = Contacts.createContact(name, phone);

            if (mobile.updateContact(oldContact, newContact)) {
                System.out.println("Contact " + oldContact.getContactName() + " updated successfully!");
            } else {
                System.out.println("Error updating contact!");
            }
        }
    }

    public static void delete() {
        System.out.print("\nEnter contact name : ");
        String name = input.nextLine();

        if (mobile.deleteContact(name)) {
            System.out.println("Contact " + name + " deleted successfully!");
        } else {
            System.out.println("Error deleting contact!");
        }
    }

    public static void search() {

        System.out.print("\nEnter contact name : ");
        String name = input.nextLine();

        Contacts contact = mobile.searchContact(name);

        if (contact != null) {
            System.out.println("\nContact found....");
            System.out.println("Name  : " + contact.getContactName());
            System.out.println("Phone : " + contact.getContactNumber());
        } else {
            System.out.println("Contact " + name + " not found !");
        }
    }
}
