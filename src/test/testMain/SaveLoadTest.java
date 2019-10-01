package testMain;
import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB;
import info.DebtsList;
import info.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SaveLoadTest {
    Person person;
    Person checkPerson;
    DebtsList debtsList;
    ArrayList<Person> listOfPerson;

    @BeforeEach
    public void setUp() throws IOException {
        person = new Person();
        checkPerson = new Person();
        debtsList = new DebtsList();
        debtsList.logResult(person, 5, "Owe", "Bob");
        debtsList.save();

    }

    @Test
    // test list saves a person and brings the list back
    public void testBringListBack() throws ClassNotFoundException, IOException {
        debtsList.load();
        listOfPerson = debtsList.getListOfPeople();
        Person firstPerson = listOfPerson.get(0);
        String firstPersonName = firstPerson.getWho();
        assertEquals(1, listOfPerson.size());
        assertTrue(firstPersonName.equals("Bob"));
    }


    @Test
    // test list saves a person, brings the list back, and adds another person
    public void testAddOnePersonInList() throws ClassNotFoundException, IOException {
        debtsList.load();
        debtsList.logResult(checkPerson, 10, "Owe", "John");
        listOfPerson = debtsList.getListOfPeople();
        Person firstPerson = listOfPerson.get(0);
        String firstPersonName = firstPerson.getWho();
        assertEquals(2, listOfPerson.size());
        assertTrue(listOfPerson.contains(checkPerson));
        assertTrue(firstPersonName.equals("Bob"));


    }
}
