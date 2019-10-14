package testMain;

import info.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LogResultTest {
    NormalDebt normalDebt;
    UrgentDebt urgentDebt;
    NormalDebt normalDebtTwo;
    UrgentDebt urgentDebtTwo;
    DebtsList debtsList;
    DebtsList singleDebtsList;


    @BeforeEach
    public void setUp() throws IntegerException, OweOrOwedException {
        normalDebt = new NormalDebt();
        debtsList = new DebtsList();
        singleDebtsList = new DebtsList();
        singleDebtsList.logResult(normalDebt, 5, "Owe", "Kevin", "No due date");
        urgentDebt = new UrgentDebt();
        debtsList.logResult(urgentDebt, 7, "Owe", "Joe", "October");
        normalDebtTwo = new NormalDebt();
        debtsList.logResult(normalDebtTwo, 5, "Owed", "John", "No due date");
        urgentDebtTwo = new UrgentDebt();
        debtsList.logResult(urgentDebtTwo, 10, "Owed", "Bob", "November");



    }


   //test for one person in list
    @Test
    public void logResultTest() {
        ArrayList<Debt> list = singleDebtsList.getListOfDebt();
        assertEquals(1, list.size());
        assertTrue(list.contains(normalDebt));
    }

    //test for two people in list
    @Test
    public void logResultTestMultiple() {

        ArrayList<Debt> list = debtsList.getListOfDebt();
        assertEquals(3, list.size());
        assertTrue(list.contains(normalDebtTwo));
        assertTrue(list.contains(urgentDebt));
        assertTrue(list.contains(urgentDebtTwo));
    }

    //test for reminder method in debt
    @Test
    public void testReminder() {
        assertEquals(normalDebt.reminder(), "You owe Kevin 5 dollars.");
        assertEquals((urgentDebt.reminder()), "You owe Joe 7 dollars by October");
        assertEquals((normalDebtTwo.reminder()),"John owes you 5 dollars.");
        assertEquals((urgentDebtTwo.reminder()), "Bob owes you 10 dollars by November");
    }






}
