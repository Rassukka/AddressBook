package fi.rassukka;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // TODO: main loop
        // TODO: Multiple languages
        // TODO: onExit, close threads writers etc.
        // TODO: security, encrypt and decrypt the data
        // TODO: get and remove duplicates

        ContactDAO contacts = new ContactDAOImpl();

        boolean running = true;

        print();

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
                    break;
                case "2":
                    break;
                case "3":
                    contacts.addEntry();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    contacts.sort("");
                    break;
                case "7":
                    contacts.search();
                    break;
                default:
                    System.out.println("no such choice");
            }

            System.out.println();

        }
    }

    private static void print() {
        System.out.print("1) Load from file\n" //
                + "2) Save to file\n" //
                + "3) Add an entry\n" //
                + "4) Remove an entry\n" //
                + "5) Edit an existing entry\n" //
                + "6) Sort the address book\n" //
                + "7) Search for a specific entry\n" //
                + "0) Quit\n" //
                + "\n"); //
    }

}
