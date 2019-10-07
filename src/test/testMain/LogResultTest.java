package testMain;

import info.Debt;
import info.DebtsList;
import info.Person;
import info.UrgentPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LogResultTest {
    Person person;
    UrgentPerson personTwo;
    DebtsList debtsList;
    Person checkPerson;

    @BeforeEach
    public void setUp() {
        person = new Person();
        checkPerson = new Person();
        debtsList = new DebtsList();
        debtsList.logResult(person, 5, "Owe", "Kevin", "No due date");
        personTwo = new UrgentPerson();
        debtsList.logResult(personTwo, 7, "Owe", "Joe", "October");



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
        debtsList.logResult(checkPerson, 5, "Owed", "John", "No due date");
        ArrayList<Debt> list = debtsList.getListOfDebt();
        assertEquals(2, list.size());
        assertTrue(list.contains(checkPerson));
        assertTrue(list.contains(person));
    }

    //test for reminder method in debt
    @Test
    public void testReminder() {
        assertEquals(person.reminder(), "You owe Kevin 5 dollars.");
        assertEquals((personTwo.reminder()), "You owe Joe 7 dollars by October");
    }
    





}
