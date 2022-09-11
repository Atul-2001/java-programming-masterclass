package com.signature;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        MobilePhone samsung = new MobilePhone();

        samsung.menu();

        boolean status = true;

        while (status) {

            System.out.println("Select option :- ");
            int op = input.nextInt();
            input.nextLine();

            switch (op) {
                case 0: samsung.menu();
                        break;

                case 1: samsung.allContacts();
                        break;

                case 2: samsung.newContact();
                        break;

                case 3: samsung.update();
                        break;

                case 4: samsung.delete();
                        break;

                case 5: samsung.search();
                        break;

                case 6: status = false;
                        break;

                default:
                    System.out.println("Choose correct option!\n");
                    break;
            }
        }
    }
}
