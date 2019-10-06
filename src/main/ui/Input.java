package ui;

import info.Debt;
import info.DebtsList;
import info.Person;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    private DebtsList debtsList;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Input();


    }

    public Input() throws IOException, ClassNotFoundException {
        debtsList = new DebtsList();
        run();
    }

    //EFFECTS: runs a loop that gets user input for data
    public void run() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        askLoadOrNew();
        while (true) {
            Person person = new Person();
            userInput(person);
            System.out.println("Are you finished imputing numbers? (Type Yes or No)");
            String done = input.next();
            if (done.equals("Yes")) {
                break; // referenced from B04 simple calculator lecture lab
            }
        }
        debtsList.save();
        for (Debt person : debtsList.getListOfDebt()) {
            System.out.println(person.getWho());
            System.out.println(person.getOweOrOwed());
            System.out.println(person.getAmount());
        }
    }

    public void askLoadOrNew() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to load a previous list or create a new list? (Type 'Load' or 'New')");
        String loadOrNew = input.next();
        if (loadOrNew.equals("Load")) {
            debtsList.load();
        }
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
            debtsList.logResult(person, amount, oweOrOwed, who);

        } else {
            System.out.println("Please enter the amount you owe");
            int amount = input.nextInt();
            System.out.println("Who do you owe this money to?");
            String who = input.next();
            System.out.println("You owe " + amount + " dollars to " + who);
            debtsList.logResult(person, amount, oweOrOwed, who);
        }

    }
}