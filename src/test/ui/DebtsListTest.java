package ui;

import info.DebtsList;
import info.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DebtsListTest {

    private DebtsList testList;
    private ArrayList<Person> newList;

    @BeforeEach
    public void setTestList() {
        testList = new DebtsList();
        Person person = new Person();
        testList.logResult(person, 10, "Owe", "Kevin");
        //testList.addToList(person);
    }



    //test adding 1 person
    @Test
    public void testOnePersonOweMoney(){
        newList = testList.getListOfPeople();
        assertEquals(1, newList.size());
    }

    //test adding multiple people

}

