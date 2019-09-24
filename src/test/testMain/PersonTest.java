package testMain;

import info.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PersonTest {
    Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setAmount (5);
        person.setWho ("Kevin");
        person.setOweOrOwed ("Owe");
    }

    @Test
    public void testPerson() {
        assertEquals(5, person.getAmount());
        assertTrue("Kevin".equals(person.getWho()));
        assertTrue("Owe".equals(person.getOweOrOwed()));

    }








}
