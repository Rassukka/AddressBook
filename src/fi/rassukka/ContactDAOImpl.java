package fi.rassukka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactDAOImpl implements ContactDAO {

    private ArrayList<Contact> allContacts = new ArrayList<Contact>();
    private Scanner scanner = new Scanner(System.in);
    private File file;
    private BufferedReader br;
    private BufferedWriter writerAppend;
    private BufferedWriter writerWrite;

    // FIXME: ParseInt or other removes the first 0, maybe need to be saved as
    // an array

    public ContactDAOImpl() throws IOException {

        file = new File("Addressbook.txt");

        if (!file.exists()) {
            System.out.println("Could not find the old file Addressbook.txt, generating a new one...");
            file.createNewFile();
        }

        Contact contact = new Contact("fdjska", "fdjksl", "432569", "fhdjsa");
        Contact contact2 = new Contact("Rasmus", "Haavisto", "321732", "jfkdls");
        allContacts.add(contact);
        allContacts.add(contact2);

        br = new BufferedReader(new FileReader(file));
        writerAppend = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
        writerWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));


        syncArray();
        syncDocument();

        for (Contact c : allContacts) {
            System.out.println(c);
        }
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
        System.out.println("Succesfully added new contact " + capitalize(firstName) + " " + capitalize(lastName) + ".");
    }

    @Override
    public void removeEntry() throws IOException {

    }

    @Override
    public void edit(Contact contact) {

    }

    @Override
    public void sort(int way) {

    }

    @Override
    public void search() {
        // TODO: Debug this
        // TODO: Maybe 0 == exit?

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
            System.out.println("Contact found: " + found.get(0).toString());
        } else if (found.size() > 1) {
            System.out.println("Multiple contacts found for search " + lookFor);
            for (int i = 1; i <= found.size() + 1; i++) {
                System.out.println(i + ") " + found.get(i).toString());
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

        // FIXME: tyhjä rivi lopusta pois

        // chekkaa tämän funktion toiminta Konstruktorissa syncArray pois

        try {
            writerWrite.write("");
            writerWrite.flush();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        for (Contact c : allContacts) {
            writerAppend.write(capitalize(c.getFirstName().toString()) + " " + capitalize(c.getLastName().toString()) + " " + c.getPhone().toString() + " " + c.getEmail().toString() + System.getProperty("line.separator"));
            writerAppend.flush();
        }


    }

    @Override
    public void syncArray() throws IOException {
        try {

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] temp = line.split(" ");
                Contact contact = new Contact(temp[0].toString().toLowerCase(), temp[1].toString().toLowerCase(),
                        temp[2].toString(), temp[3].toString().toLowerCase());
                allContacts.add(contact);
            }

        } catch (Exception e) {
            System.out.println("Syntax error in file, please check syntax...");
        }

    }

    public String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
