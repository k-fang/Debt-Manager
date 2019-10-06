package testMain;

import info.Debt;
import info.DebtsList;
import info.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LogResultTest {
    Person person;
    DebtsList debtsList;
    Person checkPerson;

    @BeforeEach
    public void setUp() {
        person = new Person();
        checkPerson = new Person();
        debtsList = new DebtsList();
        debtsList.logResult(person, 5, "Owe", "Kevin");



    }


   //test for one person in list
    @Test
    public void logResultTest() {
        ArrayList<Debt> list = debtsList.getListOfDebt();
        assertEquals(1, list.size());
        assertTrue(list.contains(person));
    }

    //test for two people in list
    @Test
    public void logResultTestMultiple() {
        debtsList.logResult(checkPerson, 5, "Owed", "John");
        ArrayList<Debt> list = debtsList.getListOfDebt();
        assertEquals(2, list.size());
        assertTrue(list.contains(checkPerson));
        assertTrue(list.contains(person));
    }







}
