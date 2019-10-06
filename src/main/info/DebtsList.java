package info;

import java.io.*;
import java.util.ArrayList;

public class DebtsList implements Serializable, Loadable, Saveable {

    private ArrayList<Debt> listOfDebt;
    private Person person;

    public DebtsList() {
        listOfDebt = new ArrayList<>();
    }

    // REQUIRES: Person, int amount, string either "Owe" or "Owed", String for name
    // MODIFIES: this
    // EFFECTS: sets passed parameters to Person person
    public void logResult(Debt person, int amount, String oweOrOwed, String who) {
        person.setAmount(amount);
        person.setOweOrOwed(oweOrOwed);
        person.setWho(who);
        addList(person);

    }

    // REQUIRES: Person
    // MODIFIES: this
    // EFFECTS: adds a new person to listOfPeople
    public void addList(Debt person) {
        listOfDebt.add(person);
    }

    //EFFECTS: returns listOfPeople
    public ArrayList<Debt> getListOfDebt() {
        return listOfDebt;
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
        oos.writeObject(listOfDebt);
        oos.close();
    }

    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        listOfDebt = (ArrayList<Debt>) ois.readObject();
        ois.close();
    }
    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the


    public String getPerson() {
        return person.getWho();
    }
}
