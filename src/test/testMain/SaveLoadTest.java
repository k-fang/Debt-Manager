package testMain;
import info.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SaveLoadTest {
    NormalDebt normalDebt;
    NormalDebt checkNormalDebt;
    DebtsList debtsList;
    ArrayList<Debt> listOfDebt;

    @BeforeEach
    public void setUp() throws IOException, IntException, OweException {
        normalDebt = new NormalDebt();
        checkNormalDebt = new NormalDebt();
        debtsList = new DebtsList();
        debtsList.logResult(normalDebt, 5, "Owe", "Bob", "No due date");
        debtsList.save();

    }

    @Test
    // test list saves a person and brings the list back
    public void testBringListBack() throws ClassNotFoundException, IOException {
        debtsList.load();
        listOfDebt = debtsList.getListOfDebt();
        Debt firstPerson = listOfDebt.get(0);
        String firstPersonName = firstPerson.getWho();
        assertEquals(1, listOfDebt.size());
        assertTrue(firstPersonName.equals("Bob"));
    }


    @Test
    // test list saves a person, brings the list back, and adds another person
    public void testAddOnePersonInList() throws ClassNotFoundException, IOException, IntException, OweException {
        debtsList.load();
        debtsList.logResult(checkNormalDebt, 10, "Owe", "John", "No due date");
        listOfDebt = debtsList.getListOfDebt();
        Debt firstPerson = listOfDebt.get(0);
        String firstPersonName = firstPerson.getWho();
        assertEquals(2, listOfDebt.size());
        assertTrue(listOfDebt.contains(checkNormalDebt));
        assertTrue(firstPersonName.equals("Bob"));


    }
}
