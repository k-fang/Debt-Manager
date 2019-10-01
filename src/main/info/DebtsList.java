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

    //EFFECTS: runs a loop that gets user input for data
    public void run() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        load();
        while (true) {
            Person person = new Person();
            userInput(person);
            System.out.println("Are you finished imputing numbers? (Type Yes or No)");
            String done = input.next();
            if (done.equals("Yes")) {
                break; // referenced from B04 simple calculator lecture lab
            }
        }
        save();
        for (Person person : listOfPeople) {
            System.out.println(person.getWho());
            System.out.println(person.getOweOrOwed());
            System.out.println(person.getAmount());
        }
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

    // REQUIRES: Person
    // EFFECTS: passes user input into logResult to be logged
    public void userInput(Person person) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you owe money or are you owed money? (Type Owe or Owed)");
        String oweOrOwed = input.next();
        if (oweOrOwed.equals("Owed")) {
            System.out.println("Please enter the amount owed to you");
            int amount = input.nextInt();
            System.out.println("Who owes you this money?");
            String who = input.next();
            System.out.println("You are owed " + amount + " dollars by " + who);
            logResult(person, amount, oweOrOwed, who);

        } else {
            System.out.println("Please enter the amount you owe");
            int amount = input.nextInt();
            System.out.println("Who do you owe this money to?");
            String who = input.next();
            System.out.println("You owe " + amount + " dollars to " + who);
            logResult(person, amount, oweOrOwed, who);
        }

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
