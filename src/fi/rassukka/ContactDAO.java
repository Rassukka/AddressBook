package fi.rassukka;

import java.io.IOException;
import java.util.ArrayList;

public interface ContactDAO {

    void addEntry() throws IOException;

    void removeEntry() throws IOException;

    void edit(Contact contact);

    void sort(int way) throws IOException;

    void search();

    ArrayList<Contact> getAllContacts();

    Contact getContact(int line);

    void syncDocument() throws IOException;

    void syncArray() throws IOException;

}
