package fi.rassukka;

import java.io.IOException;
import java.util.ArrayList;

public interface ContactDAO {

    void addEntry() throws IOException;

    void removeEntry() throws IOException;

    void edit() throws IOException;

    void sort(String s) throws IOException;

    void search();

    void syncDocument() throws IOException;

    void syncArray() throws IOException;

}
