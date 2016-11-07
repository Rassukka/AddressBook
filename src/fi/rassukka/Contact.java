package fi.rassukka;

import java.util.Comparator;

public class Contact {

    public static Comparator<Contact> firstNameComparator = (s1, s2) -> {

        String firstName1 = s1.getFirstName().toUpperCase();
        String firstName2 = s2.getFirstName().toUpperCase();
        return firstName1.compareTo(firstName2);

    };
    public static Comparator<Contact> lastNameComparator = (s1, s2) -> {

        String lastName1 = s1.getLastName().toUpperCase();
        String lastName2 = s2.getLastName().toUpperCase();
        return lastName1.compareTo(lastName2);

    };
    public static Comparator<Contact> phoneComparator = (s1, s2) -> {

        String phone1 = s1.getPhone().toUpperCase();
        String phone2 = s2.getPhone().toUpperCase();
        return phone1.compareTo(phone2);

    };
    public static Comparator<Contact> emailComparator = (s1, s2) -> {

        String email1 = s1.getEmail().split("@")[1].toUpperCase();
        String email2 = s2.getEmail().split("@")[1].toUpperCase();
        return email1.compareTo(email2);

    };
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName.toString() + " " + lastName.toString() + " " + phone + " " + email.toString();
    }

}
