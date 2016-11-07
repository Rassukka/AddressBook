package fi.rassukka;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactDAOImpl implements ContactDAO {

    // TODO: make sure this works how it is supposed to
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    // TODO: Implement this
//  public static int NUMBER_OF_CONTACTS;
    private ArrayList<Contact> allContacts = new ArrayList<Contact>();
    private Scanner scanner = new Scanner(System.in);
    private File file;
    private BufferedReader br;
    private BufferedWriter writer;


    public ContactDAOImpl() throws IOException {

        file = new File("Addressbook.txt");

        if (!file.exists()) {
            System.out.println("Could not find the file Addressbook.txt, generating a new one...");
            file.createNewFile();
        }

        br = new BufferedReader(new FileReader(file));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));

        syncArray();
        sort("lastname");
        syncDocument();
    }

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void addEntry() throws IOException {

        //Should be functional and complete.

        System.out.println("");
        System.out.println("Give the information for the contact");

        System.out.println("First name: ");
        String firstName = scanner.nextLine().toLowerCase();
        if (firstName.equals("0")) {
            return;
        }

        System.out.println("Last name: ");
        String lastName = scanner.nextLine().toLowerCase();
        if (lastName.equals("0")) {
            return;
        }

        boolean running = true;

        String phone;
        do {
            System.out.println("Phone number: ");

            phone = scanner.nextLine();

            if (phone.length() == 10 || phone.equals("0")) {
                try {
                    Integer.parseInt(phone);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid phone number...");
                    continue;
                }
                running = false;
            } else {
                System.out.println("Invalid phone number...");
                continue;
            }
        } while (running);

        if (phone.equals("0")) {
            return;
        }

        running = true;
        String email;

        do {
            System.out.println("Email: ");
            email = scanner.nextLine();

            if (validate(email) == true || email.equals("0")) {
                running = false;
            } else {
                System.out.println("Invalid email...");
            }


        } while (running);

        if (email.equals("0")) {
            return;
        }

        System.out.println("");

        Contact newContact = new Contact(firstName, lastName, phone, email);
        allContacts.add(newContact);
        syncDocument();
        System.out.println("Successfully added new contact " + capitalize(firstName) + " " + capitalize(lastName) + ".");

    }

    @Override
    public void removeEntry() throws IOException {

        //FIXME: Volvo plz fix
        ArrayList<Contact> found = getContacts("remove");

        if (found.size() == 1) {
            System.out.println("Contact found: " + capitalize(found.get(0).getFirstName()) + " " + capitalize(found.get(0).getLastName()) + " " + found.get(0).getPhone() + " " + found.get(0).getEmail());
            System.out.println("Are you sure you want to remove this contact? (y/n) ");
            String decision = scanner.nextLine().toLowerCase();
            if (decision.equals("y")) {
//                for (Iterator<Contact> c = allContacts.iterator(); c.hasNext(); ) {
//                    Contact contact = c.next();
//                    if (contact.getPhone().equals(found.get(0).getPhone())) {
                allContacts.remove(found.get(0));
                syncDocument();
            }
//                }
//            }
        } else if (found.size() > 1) {
            System.out.println("Multiple contacts found: ");
            for (int i = 0; i <= found.size() - 1; i++) {
                System.out.println(i + 1 + ") " + capitalize(found.get(i).getFirstName()) + " " + capitalize(found.get(i).getLastName()) + " " + found.get(i).getPhone() + " " + found.get(i).getEmail());
            }
            boolean running = true;
            do {
                try {
                    System.out.println("Please choose who you want to remove: ");
                    int choice = scanner.nextInt();
                    if (choice > found.size() || choice < 0) {
                        System.out.println("Invalid number, please try again...");
                    } else if (choice == 0) {
                        running = false;
                        break;
                    } else {
                        Contact remove = found.get(choice - 1);
                        System.out.println(capitalize(remove.getFirstName()) + " " + capitalize(remove.getLastName()) + " " + remove.getPhone() + " " + remove.getEmail());
                        System.out.println("Are you sure you want to remove this contact? (y/n)");
                        String decision = scanner.nextLine();
                        if (decision.equals("y")) {
                            for (Iterator<Contact> c = allContacts.iterator(); c.hasNext(); ) {
                                Contact contact = c.next();
                                if (contact.getPhone().equals(remove.getPhone())) {
                                    allContacts.remove(contact);
                                    syncDocument();
                                    System.out.println("Successfully removed contact");
                                }
                            }
                        } else {
                            running = false;
                        }

                    }
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    break;
                }
            } while (running);
        } else {
            System.out.println("No contacts found");
        }


    }

    @Override
    public void edit() throws IOException {

    }

    @Override
    public void sort(String s) throws IOException {
        if (s.equals("lastname")) {
            Collections.sort(allContacts, Contact.lastNameComparator);
        } else {
            System.out.println("In which way do you want the database to be sorted? (firstname, lastname, phone, email)");
            boolean running = true;
            while (running) {
                String userDecision = scanner.nextLine().toLowerCase();
                switch (userDecision) {
                    case "firstname":
                        Collections.sort(allContacts, Contact.firstNameComparator);
                        running = false;
                        break;
                    case "lastname":
                        Collections.sort(allContacts, Contact.lastNameComparator);
                        running = false;
                        break;
                    case "phone":
                        Collections.sort(allContacts, Contact.phoneComparator);
                        running = false;
                        break;
                    case "email":
                        Collections.sort(allContacts, Contact.emailComparator);
                        running = false;
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Syntax error, try again: ");
                        break;
                }
            }

            System.out.println("Successfully sorted the database");
            syncDocument();
        }
    }

    @Override
    public void search() {

        ArrayList<Contact> found = getContacts("search");

        if (found.size() == 1) {
            System.out.println("Contact found: " + capitalize(found.get(0).getFirstName()) + " " + capitalize(found.get(0).getLastName()) + " " + found.get(0).getPhone() + " " + found.get(0).getEmail());
        } else if (found.size() > 1) {
            System.out.println("Multiple contacts found: ");
            for (int i = 0; i <= found.size() - 1; i++) {
                System.out.println(i + 1 + ") " + capitalize(found.get(i).getFirstName()) + " " + capitalize(found.get(i).getLastName()) + " " + found.get(i).getPhone() + " " + found.get(i).getEmail());
                // i - 1 when you want index instead of number showed
            }
        } else {
            System.out.println("No contacts found");
        }
    }

    @Override
    public void syncDocument() throws IOException {
        if (allContacts.size() != 0) {
            BufferedWriter writerWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writerWrite.write("");
            writerWrite.flush();
            writerWrite.close();

            for (Contact c : allContacts) {
                writer.write(capitalize(c.getFirstName()) + " " + capitalize(c.getLastName()) + " " + c.getPhone() + " " + c.getEmail() + System.getProperty("line.separator"));
                writer.flush();
            }
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

    private ArrayList<Contact> getContacts(String mode) {

        if (mode.equals("search")) {
            System.out.println("Who do you want to find?");
        } else if (mode.equals("remove")) {
            System.out.println("Who do you want to remove?");
        } else if (mode.equals("edit")) {
            System.out.println("Who do you want to edit?");
        }
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
        return found;
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
