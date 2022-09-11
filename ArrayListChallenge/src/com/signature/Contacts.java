package com.signature;

public class Contacts {

    private String contactName;
    private String contactNumber;

    public Contacts(String contactName, String contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public static Contacts createContact(String name, String phone) {
        return new Contacts(name, phone);
    }
}
