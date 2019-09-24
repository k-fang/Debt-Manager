package info;

import java.util.Scanner;

public class DebtsList {

    private ListOfPeople listOfPeople;


    public DebtsList() {
        Scanner input = new Scanner(System.in);
        listOfPeople = new ListOfPeople ();
        while (true) {
            Person person = new Person();
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

            System.out.println("Are you finished imputing numbers? (Type Yes or No)");

            String done = input.next();
            if (done.equals("Yes")) {
                break; // referenced from B04 simple calculator lecture lab

            }
        }
        System.out.println (listOfPeople.returnList());
    }

    // REQUIRES: person, int amount, string either "Owe" or "Owed", String for name
    // MODIFIES: this
    // EFFECTS: adds a new person to listOfPeople with passed parameters
    public void logResult(Person person, int amount, String oweOrOwed, String who) {
        person.setAmount(amount);
        person.setOweOrOwed(oweOrOwed);
        person.setWho(who);
        listOfPeople.addList(person);
    }

}
