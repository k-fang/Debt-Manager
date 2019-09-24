package info;

import java.util.ArrayList;


public class ListOfPeople {
    private ArrayList<Person> listOfPeople;
    public ListOfPeople (){listOfPeople = new ArrayList<>();}

    //REQUIRES: Person
    //MODIFIES: this
    //Effects: adds person to listOfPeople
    public void addList(Person person) {
        listOfPeople.add(person);
    }
    //EFFECTS: returns listOfPeople when called
    public ArrayList<Person> returnList() {
        return listOfPeople;
    }

}
