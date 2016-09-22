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
        // TODO: 0 == exit
        // FIXME: all to uppercase and lovercase messing up, fix by handling all in lovercase and for printing out and file to uppercase (email is case sensitive?) maybe a specific method for this with enums?

        ContactDAO contacts = new ContactDAOImpl();

        System.out.print("1) Load from file\n" //
                + "2) Save to file\n" //
                + "3) Add an entry\n" //
                + "4) Remove an entry\n" //
                + "5) Edit an existing entry\n" //
                + "6) Sort the address book\n" //
                + "7) Search for a specific entry\n" //
                + "8) Quit\n" //
                + "\n");

        // TODO: Try and catch

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please choose what you'd like to do with the database: ");
        int userChoice = scanner.nextInt();

        // TODO: Chance to switch
        if (userChoice == 3) {
            contacts.addEntry();
        } else if (userChoice == 7) {
            contacts.search();
        } else if (userChoice == 6) {
            contacts.sort(4);
        }

        scanner.close();

    }

}
