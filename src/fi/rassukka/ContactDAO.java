package fi.rassukka;

import java.io.IOException;
import java.util.ArrayList;

public interface ContactDAO {
	
	public void addEntry() throws IOException;
	public void removeEntry() throws IOException;
	public void edit(Contact contact);
	public void sort(int way);
	public void search();
	public ArrayList<Contact> getAllContacts();
	public Contact getContact(int line);
	public void syncDocument() throws IOException;
	public void syncArray() throws IOException;

}
