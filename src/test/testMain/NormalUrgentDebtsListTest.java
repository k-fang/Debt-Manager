package testMain;

import info.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class NormalUrgentDebtsListTest {
    NormalDebt normalDebt;
    UrgentDebt urgentDebt;
    NormalDebt normalDebtTwo;
    UrgentDebt urgentDebtTwo;
    UrgentDebt urgentDebtExceptionTest;
    NormalUrgentDebtsList regularDebtsList;
    NormalUrgentDebtsList singleDebtsList;
    RecurringDebtsList recurringDebtsList;
    Map<String, Debt> mapOfDebts;
    Map<String, Debt> mapOfRecurringDebts;


    @BeforeEach
    public void setUp() throws IntException, OweException, IOException {
        normalDebt = new NormalDebt();
        regularDebtsList = new NormalUrgentDebtsList();
        //singleDebtsList = new NormalUrgentDebtsList();
        recurringDebtsList = new RecurringDebtsList();
        regularDebtsList.logResult(normalDebt, 5, "Owe", "Kevin", "No due date");
        urgentDebt = new UrgentDebt();
        /*regularDebtsList.logResult(urgentDebt, 7, "Owe", "Joe", "October");
        regularDebtsList.addList(urgentDebt);
        normalDebtTwo = new NormalDebt();
        regularDebtsList.logResult(normalDebtTwo, 5, "Owed", "John", "No due date");
        regularDebtsList.addList(normalDebtTwo);
        urgentDebtTwo = new UrgentDebt();
        regularDebtsList.logResult(urgentDebtTwo, 10, "Owed", "Bob", "November");
        urgentDebtExceptionTest = new UrgentDebt();
        regularDebtsList.addList(urgentDebtTwo);*/
        urgentDebtExceptionTest = new UrgentDebt();
        regularDebtsList.save();





    }


   //test for one person in list
    @Test
    public void logResultTest() {
        assertEquals(regularDebtsList.getSpecificDebt("Kevin"), normalDebt);
    }

    //test for two people in list
    @Test
    public void logResultTestMultiple() throws IntException, OweException {
        regularDebtsList.logResult(urgentDebt, 7, "Owe", "Joe", "October");
        assertEquals(regularDebtsList.getSpecificDebt("Kevin"), normalDebt);
        assertEquals(regularDebtsList.getSpecificDebt("Joe"), urgentDebt);
    }


    @Test
    public void testExceptionThrowsIntegerException() {
        try {
            regularDebtsList.logResult(urgentDebtExceptionTest, -5, "Owe", "Bob", "Nov");
            fail();
        } catch (IntException e) {

        } catch (OweException e) {
            fail();
        }
    }

    @Test
    public void testExceptionThrowsOweOrOwedException() {
        try {
            regularDebtsList.logResult(urgentDebtExceptionTest, 10, "Dab", "Bobby", "Nov");
            fail();
        } catch (IntException e) {
            fail();
        } catch (OweException e) {
        }
    }

    @Test
    public void testExceptionDoesNotThrow() {
        try {
            regularDebtsList.logResult(urgentDebtExceptionTest, 10, "owe", "Bobby", "Nov");
        } catch (IntException e) {
            fail();
        } catch (OweException e) {
            fail();
        }
    }

    @Test
    // test list saves a person and brings the list back
    public void testBringListBack() throws ClassNotFoundException, IOException {
        regularDebtsList.load();
        assertEquals(regularDebtsList.getSpecificDebt("Kevin").getWho(), "Kevin");
    }


    @Test
    // test list saves a person, brings the list back, and adds another person
    public void testAddOnePersonInList() throws ClassNotFoundException, IOException, IntException, OweException {
        regularDebtsList.load();
        regularDebtsList.logResult(urgentDebt, 7, "Owe", "Joe", "October");
        assertEquals(regularDebtsList.getSpecificDebt("Kevin").getWho(), "Kevin");
        assertEquals(regularDebtsList.getSpecificDebt("Joe").getWho(), "Joe");
    }
}
