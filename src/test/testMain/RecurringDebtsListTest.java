package testMain;
import info.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecurringDebtsListTest {
    RecurringDebt recurringDebt;
    RecurringDebt checkRecurringDebt;
    RecurringDebtsList recurringDebtsList;
    ArrayList<Debt> listOfDebt;
    NormalUrgentDebtsList regularDebtsList;


    @BeforeEach
    public void setUp() throws IOException, IntException, OweException {
        regularDebtsList = new NormalUrgentDebtsList();
        recurringDebt = new RecurringDebt();
        checkRecurringDebt = new RecurringDebt();
        recurringDebtsList = new RecurringDebtsList();
        recurringDebtsList.logResult(recurringDebt, 5, "Owe", "Bob", "No due date");
        recurringDebtsList.logResult(checkRecurringDebt, 10, "Owe", "John", "No due date");
        recurringDebtsList.addListRe(regularDebtsList, recurringDebt);
        recurringDebtsList.save();
    }

    //test for one person in list
    @Test
    public void logResultTest() {
        ArrayList<Debt> list = recurringDebtsList.getListOfDebt();
        assertEquals(1, list.size());
        assertTrue(list.contains(recurringDebt));
    }

    //test for two people in list
    @Test
    public void logResultTestMultiple() {
        recurringDebtsList.addListRe(regularDebtsList, checkRecurringDebt);
        ArrayList<Debt> list = recurringDebtsList.getListOfDebt();
        assertEquals(2, list.size());
        ArrayList<Debt> list2 = regularDebtsList.getListOfDebt();
        assertEquals(2, list2.size());
        assertTrue(list.contains(recurringDebt));
        assertTrue(list.contains(checkRecurringDebt));
        assertTrue(list2.contains(recurringDebt));
        assertTrue(list2.contains(checkRecurringDebt));
        recurringDebtsList.removeList(regularDebtsList, recurringDebt);
        assertEquals(1, list.size());
        assertEquals(1, list2.size());
        assertTrue(list.contains(checkRecurringDebt));
        assertTrue(list2.contains(checkRecurringDebt));
    }


    @Test
    public void testExceptionThrowsIntegerException() {
        try {
            regularDebtsList.logResult(checkRecurringDebt, -5, "Owe", "Bob", "Nov");
            fail();
        } catch (IntException e) {

        } catch (OweException e) {
            fail();
        }
    }

    @Test
    public void testExceptionThrowsOweOrOwedException() {
        try {
            regularDebtsList.logResult(checkRecurringDebt, 10, "Dab", "Bobby", "Nov");
            fail();
        } catch (IntException e) {
            fail();
        } catch (OweException e) {
        }
    }

    @Test
    public void testExceptionDoesNotThrow() {
        try {
            regularDebtsList.logResult(checkRecurringDebt, 10, "owe", "Bobby", "Nov");
        } catch (IntException e) {
            fail();
        } catch (OweException e) {
            fail();
        }
    }


    @Test
    // test list saves a person and brings the list back
    public void testBringListBack() throws ClassNotFoundException, IOException {
        recurringDebtsList.load();
        listOfDebt = recurringDebtsList.getListOfDebt();
        Debt firstPerson = listOfDebt.get(0);
        String firstPersonName = firstPerson.getWho();
        assertEquals(1, listOfDebt.size());
        assertTrue(firstPersonName.equals("Bob"));
    }


    @Test
    // test list saves a person, brings the list back, and adds another person
    public void testAddOnePersonInList() throws ClassNotFoundException, IOException, IntException, OweException {
        recurringDebtsList.load();

        recurringDebtsList.addListRe(regularDebtsList, checkRecurringDebt);
        listOfDebt = recurringDebtsList.getListOfDebt();
        Debt firstPerson = listOfDebt.get(0);
        String firstPersonName = firstPerson.getWho();
        assertEquals(2, listOfDebt.size());
        assertTrue(listOfDebt.contains(checkRecurringDebt));
        assertTrue(firstPersonName.equals("Bob"));
        recurringDebtsList.clearList();
        assertEquals(0, listOfDebt.size());

    }
}
