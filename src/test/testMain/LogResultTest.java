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
    Person personThree;
    UrgentPerson personFour;
    DebtsList debtsList;
    DebtsList singleDebtsList;


    @BeforeEach
    public void setUp() {
        person = new Person();
        debtsList = new DebtsList();
        singleDebtsList = new DebtsList();
        singleDebtsList.logResult(person, 5, "Owe", "Kevin", "No due date");
        personTwo = new UrgentPerson();
        debtsList.logResult(personTwo, 7, "Owe", "Joe", "October");
        personThree = new Person();
        debtsList.logResult(personThree, 5, "Owed", "John", "No due date");
        personFour = new UrgentPerson();
        debtsList.logResult(personFour, 10, "Owed", "Bob", "November");



    }


   //test for one person in list
    @Test
    public void logResultTest() {
        ArrayList<Debt> list = singleDebtsList.getListOfDebt();
        assertEquals(1, list.size());
        assertTrue(list.contains(person));
    }

    //test for two people in list
    @Test
    public void logResultTestMultiple() {

        ArrayList<Debt> list = debtsList.getListOfDebt();
        assertEquals(3, list.size());
        assertTrue(list.contains(personThree));
        assertTrue(list.contains(personTwo));
        assertTrue(list.contains(personFour));
    }

    //test for reminder method in debt
    @Test
    public void testReminder() {
        assertEquals(person.reminder(), "You owe Kevin 5 dollars.");
        assertEquals((personTwo.reminder()), "You owe Joe 7 dollars by October");
        assertEquals((personThree.reminder()),"John owes you 5 dollars.");
        assertEquals((personFour.reminder()), "Bob owes you 10 dollars by November");
    }






}
