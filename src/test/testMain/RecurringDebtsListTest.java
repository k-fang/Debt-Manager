package testMain;
import info.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RecurringDebtsListTest {
    RecurringDebt recurringDebt;
    RecurringDebt checkRecurringDebt;
    RecurringDebtsList recurringDebtsList;
    ArrayList<Debt> listOfDebt;
    NormalUrgentDebtsList regularDebtsList;
    Map<String,Debt> mapOfDebts;
    Map<String,Debt> mapOfRecurringDebts;



    @BeforeEach
    public void setUp() throws IOException, IntException, OweException {

        regularDebtsList = new NormalUrgentDebtsList();
        recurringDebt = new RecurringDebt();
        checkRecurringDebt = new RecurringDebt();
        recurringDebtsList = new RecurringDebtsList();
        recurringDebtsList.logResult(recurringDebt, 5, "Owe", "Bob", "No due date");

        //recurringDebtsList.addListRe(regularDebtsList, recurringDebt);
        recurringDebtsList.save();
    }

    //test for one person in list
    @Test
    public void logResultTest() {
        assertEquals(recurringDebtsList.getSpecificDebt("Bob"), recurringDebt);
    }

    //test for two people in list
    @Test
    public void logResultTestMultiple() throws IntException, OweException {
        recurringDebtsList.logResult(checkRecurringDebt, 10, "Owe", "John", "No due date");
        assertEquals(recurringDebtsList.getSpecificDebt("Bob"), recurringDebt);
        assertEquals(recurringDebtsList.getSpecificDebt("John"), checkRecurringDebt);
    }


    @Test
    public void testExceptionThrowsIntegerException() {
        try {
            recurringDebtsList.logResult(checkRecurringDebt, -5, "Owe", "Bob", "Nov");
            fail();
        } catch (IntException e) {

        } catch (OweException e) {
            fail();
        }
    }

    @Test
    public void testExceptionThrowsOweOrOwedException() {
        try {
            recurringDebtsList.logResult(checkRecurringDebt, 10, "Dab", "Bobby", "Nov");
            fail();
        } catch (IntException e) {
            fail();
        } catch (OweException e) {
        }
    }

    @Test
    public void testExceptionDoesNotThrow() {
        try {
            recurringDebtsList.logResult(checkRecurringDebt, 10, "owe", "Bobby", "Nov");
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
        assertEquals(recurringDebtsList.getSpecificDebt("Bob").getWho(), "Bob");
    }


    @Test
    // test list saves a person, brings the list back, and adds another person
    public void testAddOnePersonInList() throws ClassNotFoundException, IOException, IntException, OweException {
        recurringDebtsList.load();

        recurringDebtsList.logResult(checkRecurringDebt, 10, "Owe", "John", "No due date");
        assertEquals(recurringDebtsList.getSpecificDebt("Bob").getWho(), "Bob");
        assertEquals(recurringDebtsList.getSpecificDebt("John").getWho(), "John");


    }
}
