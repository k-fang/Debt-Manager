package info;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DebtsList implements Serializable, Loadable, Saveable {

    private ArrayList<Person> listOfPeople;
    private Person person;

    public DebtsList() {
        listOfPeople = new ArrayList<>();
    }

    // REQUIRES: Person, int amount, string either "Owe" or "Owed", String for name
    // MODIFIES: this
    // EFFECTS: sets passed parameters to Person person
    public void logResult(Person person, int amount, String oweOrOwed, String who) {
        person.setAmount(amount);
        person.setOweOrOwed(oweOrOwed);
        person.setWho(who);
        addList(person);

    }

    // REQUIRES: Person
    // MODIFIES: this
    // EFFECTS: adds a new person to listOfPeople
    public void addList(Person person) {
        listOfPeople.add(person);
    }

    //EFFECTS: returns listOfPeople
    public ArrayList<Person> getListOfPeople() {
        return listOfPeople;
    }




    /*public void save() throws IOException {
        PrintWriter pw = new PrintWriter (new FileOutputStream("input"));
        for (Person person : listOfPeople) {
            pw.println(person.getWho());
            pw.println(person.getAmount());
            pw.println(person.getOweOrOwed());
        }
        pw.close();
    }*/

    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listOfPeople);
        oos.close();
    }

    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        listOfPeople = (ArrayList<Person>) ois.readObject();
        ois.close();
    }
    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the


    public String getPerson() {
        return person.getWho();
    }
}
