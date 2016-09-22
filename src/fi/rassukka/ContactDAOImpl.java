package fi.rassukka;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ContactDAOImpl implements ContactDAO {

    private ArrayList<Contact> allContacts = new ArrayList<Contact>();
    private Scanner scanner = new Scanner(System.in);
    private File file;
    private BufferedReader br;
    private BufferedWriter writer;

//  TODO: Automatic sort

    public ContactDAOImpl() throws IOException {

        file = new File("Addressbook.txt");

        if (!file.exists()) {
            System.out.println("Could not find the old file Addressbook.txt, generating a new one...");
            file.createNewFile();
        }

        br = new BufferedReader(new FileReader(file));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));

        syncArray();
        syncDocument();
    }

    @Override
    public void addEntry() throws IOException {

        // TODO: vain 10 numeroisia puhelinnumeroita?
        // TODO: emailin muoto pit�� olla oikein?
        // TODO: automaattisesti isot kirjaimet nimeen
        // TODO: loop that you can exit with 0.

        System.out.println("");
        System.out.println("Give the information for ");
        System.out.println("First name: ");
        String firstName = scanner.nextLine().toLowerCase();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine().toLowerCase();
        System.out.println("Phone number: ");

        String phone = scanner.nextLine();
        // TODO: Fix this to actually do something
        try {
            Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            System.out.println("Invalid phone number...");
        }
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("");

        Contact newContact = new Contact(firstName, lastName, phone, email);
        allContacts.add(newContact);
        syncDocument();
        System.out.println("Successfully added new contact " + capitalize(firstName) + " " + capitalize(lastName) + ".");
    }

    @Override
    public void removeEntry() throws IOException {

    }

    @Override
    public void edit(Contact contact) {

    }

    @Override
    public void sort(int way) throws IOException {
        // TODO: Looppi ja poistuminen
        System.out.println("In which way do you want the database to be sorted? (firstname, lastname, phone, email)");
        String userDecision = scanner.nextLine().toLowerCase();
        if (userDecision.equals("firstname")) {
            Collections.sort(allContacts, Contact.firstNameComparator);
        } else if (userDecision.equals("lastname")) {
            Collections.sort(allContacts, Contact.lastNameComparator);
        } else if (userDecision.equals("phone")) {
            Collections.sort(allContacts, Contact.phoneComparator);
        } else if (userDecision.equals("email")) {
            Collections.sort(allContacts, Contact.emailComparator);
        } else {
            System.out.println("Please enter a valid way");
        }
        System.out.println("Successfully sorted the database with " + userDecision);
        syncDocument();
    }

    @Override
    public void search() {

        System.out.println("Who do you want to find?");
        String lookFor = scanner.nextLine().toLowerCase();
        ArrayList<Contact> found = new ArrayList<Contact>();

        for (Contact c : allContacts) {
            if (lookFor.equals(c.getFirstName())) {
                found.add(c);
            } else if (lookFor.equals(c.getLastName())) {
                found.add(c);
            } else if (lookFor.equals(c.getPhone())) {
                found.add(c);
            } else if (lookFor.equals(c.getEmail())) {
                found.add(c);
            }
        }

        if (found.size() == 1) {
            System.out.println("Contact found: " + capitalize(found.get(0).getFirstName()) + " " + capitalize(found.get(0).getLastName()) + " " + found.get(0).getPhone() + " " + found.get(0).getEmail());
        } else if (found.size() > 1) {
            System.out.println("Multiple contacts found for search " + lookFor);
            for (int i = 0; i <= found.size() - 1; i++) {
                System.out.println(i + 1 + ") " + capitalize(found.get(i).getFirstName()) + " " + capitalize(found.get(i).getLastName()) + " " + found.get(i).getPhone() + " " + found.get(i).getEmail());
                // i - 1 kun haluaa indexin!
            }

            // TODO: what to do with multiple contacts? need for removal?

            // int choice = scanner.nextInt();
            // boolean running = false;
            // while (running) {
            // if (choice > found.size() || choice < 0) {
            // System.out.println("Invalid number, please try again...");
            // } else {
            //
            // }
        }

    }

    @Override
    public ArrayList<Contact> getAllContacts() {

        return null;
    }

    @Override
    public Contact getContact(int line) {

        return null;
    }

    @Override
    public void syncDocument() throws IOException {

        BufferedWriter writerWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writerWrite.write("");
        writerWrite.flush();
        writerWrite.close();

        for (Contact c : allContacts) {
            writer.write(capitalize(c.getFirstName()) + " " + capitalize(c.getLastName()) + " " + c.getPhone() + " " + c.getEmail() + System.getProperty("line.separator"));
            writer.flush();
        }


    }

    @Override
    public void syncArray() throws IOException {

        String str;
        while ((str = br.readLine()) != null) {
            String[] temp = str.split(" ");
            Contact contact = new Contact(temp[0].toLowerCase(), temp[1].toLowerCase(), temp[2], temp[3].toLowerCase());
            allContacts.add(contact);
        }


    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
