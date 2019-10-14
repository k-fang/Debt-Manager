package ui;

import info.*;

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
        dueDate = "";
        run();
    }

    //EFFECTS: runs a loop that gets user input for data
    public void run() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        askLoadOrNew();
        while (true) {
            userInput();
            System.out.println("Are you finished imputing numbers? (Type Yes or No)");
            String done = input.next();
            if (done.equalsIgnoreCase("Yes")) {
                break; // referenced from B04 simple calculator lecture lab
            }
        }
        debtsList.save();
        printList();
    }

    public void userInput() {
        askPersonOrUrgentPerson();
        try {
            debtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
        } catch (integerException e) {
            System.out.println("You entered a negative or zero amount!\nPlease enter your entry again.");
            userInput();
        } finally {
            System.out.println("Thank You.");
        }

    }


    private void askPersonOrUrgentPerson() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to create and urgent item or a regular item? (Type 'Urgent' or 'Regular')");
        String urgentOrRegular = input.next();
        if (urgentOrRegular.equalsIgnoreCase("Regular")) {
            normalDebt();
        } else {
            urgentDebt();
        }
    }

    public void normalDebt() {
        debt = new NormalDebt();
        debtInput(debt);
        /*try {
            debtsList.logResult(debt, amount, oweOrOwed, who, "No due date");
        } catch (integerException e) {
            System.out.println("You entered a negative or zero amount!");
            */

    }

    public void urgentDebt() {
        Scanner input = new Scanner(System.in);
        debt = new UrgentDebt();
        System.out.println("What is the date this debt is due?");
        dueDate = input.next();
        debtInput(debt);
       /* try {
            debtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
        } catch (integerException e) {
            System.out.println("You entered a negative or zero amount!");
        }*/
    }

    public void askLoadOrNew() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to load a previous list or create a new list? (Type 'Load' or 'New')");
        String loadOrNew = input.next();
        if (loadOrNew.equalsIgnoreCase("Load")) {
            debtsList.load();
        }
    }

    // REQUIRES: Person
    // EFFECTS: passes user input into logResult to be logged
    public void debtInput(Debt person) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you owe money or are you owed money? (Type Owe or Owed)");
        oweOrOwed = input.next();
        if (oweOrOwed.equalsIgnoreCase("Owed")) {
            System.out.println("Please enter the amount owed to you");
            amount = input.nextInt();
            System.out.println("Who owes you this money?");
            who = input.next();


        } else {
            System.out.println("Please enter the amount you owe");
            amount = input.nextInt();
            System.out.println("Who do you owe this money to?");
            who = input.next();


        }

    }

    public void printList() {
        for (Debt debt : debtsList.getListOfDebt()) {
            System.out.println(debt.reminder());
        }
    }
}