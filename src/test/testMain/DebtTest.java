package testMain;

import info.*;
import org.junit.jupiter.api.BeforeEach;
import info.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class DebtTest {

    NormalDebt normalDebt;
    UrgentDebt urgentDebt;
    RecurringDebt recurringDebt;
    RecurringDebtsList recurringDebtsList;
    NormalUrgentDebtsList regularDebtsList;


    @BeforeEach
    public void setUp() throws IntException, OweException {
        normalDebt = new NormalDebt();
        normalDebt.setAmount(5);
        normalDebt.setDueDate("No due date");
        normalDebt.setOweOrOwed("Owe");
        normalDebt.setWho("Kevin");
        urgentDebt = new UrgentDebt();
        urgentDebt.setAmount(7);
        urgentDebt.setDueDate("October");
        urgentDebt.setOweOrOwed("Owe");
        urgentDebt.setWho("Joe");
        recurringDebt = new RecurringDebt();
        recurringDebt.setAmount(6);
        recurringDebt.setDueDate("week");
        recurringDebt.setOweOrOwed("Owed");
        recurringDebt.setWho("John");
    }

      //test for reminder method in debt
    @Test
    public void testReminder() {
        assertEquals(normalDebt.reminder(), "You owe Kevin 5 dollars.");
        assertEquals(urgentDebt.reminder(), "You owe Joe 7 dollars by October");
        assertEquals(recurringDebt.reminder(), "John owes you 6 dollars every week");
    }

}




