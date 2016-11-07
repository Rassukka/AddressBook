package fi.rassukka;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // TODO: Multiple languages
        // TODO: onExit, close threads writers etc.
        // TODO: security, encrypt and decrypt the data
        // TODO: get and remove duplicates
        // TODO: make it less buggy :)
        // TODO: print out every contact
        // TODO: add number of contacts
        // TODO: help, print out the instruction
        // TODO: GUI, with javaFX, android app?
        // TODO: Sync the app
        // TODO: Documentation
        
        // Poistaa puhelinnumeron perusteella koska sen ei koskaan pitäisi olla sama kahdella ihmisellä
        // Kaikista loopeista pääsee pois kirjoittamalla 0

        ContactDAO contacts = new ContactDAOImpl();

        boolean running = true;

        System.out.print("1) Add an entry\n" //
                + "2) Remove an entry\n" //
                + "3) Edit an existing entry\n" //
                + "4) Sort the address book\n" //
                + "5) Search for a specific entry\n" //
                + "0) Quit\n" //
                + "\n"); //


        while (running) {

            Scanner scanner = new Scanner(System.in);


            System.out.println("Please choose what you'd like to do with the database: ");
            String userChoice = scanner.nextLine().toLowerCase();

            switch (userChoice) {
                case "0":
                    System.out.println("Byee!");
                    running = false;
                    break;
                case "1":
                    contacts.addEntry();
                    break;
                case "2":
                    contacts.removeEntry();
                    break;
                case "3":
                    contacts.edit();
                    break;
                case "4":
                    contacts.sort("");
                    break;
                case "5":
                    contacts.search();
                    break;
                default:
                    System.out.println("no such choice");
            }

            System.out.println();

        }
    }
}


