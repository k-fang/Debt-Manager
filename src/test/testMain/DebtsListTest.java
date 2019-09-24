package testMain;

import info.ListOfPeople;
import info.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DebtsListTest {
    private ListOfPeople listOfPeople;
    private ArrayList<Person> personList;
    private Person person;

    @BeforeEach
    public void setTestList() {
        listOfPeople = new ListOfPeople();
        person = new Person();
        person.setWho("Kevin");
        person.setAmount(5);
        person.setOweOrOwed("Owe");
        listOfPeople.addList(person);
    }
    //test adding 1 person
    @Test
    public void testOnePersonOweMoney(){
        personList = listOfPeople.returnList();
        assertEquals(1, personList.size());
        assertTrue ("Kevin".equals(person.getWho()));
        assertTrue ("Owe".equals(person.getOweOrOwed()));
        assertEquals (5, person.getAmount());
    }

    //test adding multiple people
    @Test
    public void testManyPeopleOweMoney(){
        person = new Person();
        person.setWho("John");
        person.setAmount(10);
        person.setOweOrOwed("Owe");
        listOfPeople.addList(person);
        personList = listOfPeople.returnList();
        assertEquals(2, personList.size());
    }

}

