package fi.rassukka;

import java.util.ArrayList;

public class getContactHelp {

    private String userInput;
    private ArrayList foundContacts;

    public getContactHelp(String userInput, ArrayList foundContacts) {
        this.userInput = userInput;
        this.foundContacts = foundContacts;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public ArrayList getFoundContacts() {
        return foundContacts;
    }

    public void setFoundContacts(ArrayList foundContacts) {
        this.foundContacts = foundContacts;
    }
}
