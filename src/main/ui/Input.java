package ui;

import info.Debt;
import info.DebtsList;
import info.Person;
import info.UrgentPerson;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    private DebtsList debtsList;
    private Debt debt;
    private int amount;
    private String who;
    private String oweOrOwed;
    private String dueDate;


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
            askPersonOrUrgentPerson();
           /* Debt person = new Person();
            userInput(person);*/
            System.out.println("Are you finished imputing numbers? (Type Yes or No)");
            String done = input.next();
            if (done.equals("Yes")) {
                break; // referenced from B04 simple calculator lecture lab
            }
        }
        debtsList.save();
        for (Debt person : debtsList.getListOfDebt()) {
            person.reminder();
        }
    }

    private void askPersonOrUrgentPerson() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to create and urgent item or a regular item? (Type 'Urgent' or 'Regular')");
        String urgentOrRegular = input.next();
        if (urgentOrRegular.equals("Regular")) {
            debt = new Person();
            userInput(debt);
            debtsList.logResult(debt, amount, oweOrOwed, who, "No due date");
        } else {
            debt = new UrgentPerson();
            System.out.println("What is the date this debt is due?");
            dueDate = input.next();
            userInput(debt);
            debtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
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
    public void userInput(Debt person) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you owe money or are you owed money? (Type Owe or Owed)");
        oweOrOwed = input.next();
        if (oweOrOwed.equals("Owed")) {
            System.out.println("Please enter the amount owed to you");
            amount = input.nextInt();
            System.out.println("Who owes you this money?");
            who = input.next();
            System.out.println("You are owed " + amount + " dollars by " + who);
            //debtsList.logResult(person, amount, oweOrOwed, who);

        } else {
            System.out.println("Please enter the amount you owe");
            amount = input.nextInt();
            System.out.println("Who do you owe this money to?");
            who = input.next();
            System.out.println("You owe " + amount + " dollars to " + who);
            //debtsList.logResult(person, amount, oweOrOwed, who);
        }

    }
}