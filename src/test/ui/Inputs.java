package ui;

import info.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class Inputs {
    private ArrayList<Person> listofpeople;

    public static void main(String[] args) {
        new Inputs();

    }

    private Inputs() {
        listofpeople = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            Person person = new Person();
            System.out.println("Do you owe money or are you owed money? (Type Owe or Owed)");
            String owe = input.next();
            if (owe.equals("Owed")) {
                System.out.println("Please enter the amount owed to you");
                int amount = input.nextInt();
                System.out.println("Who owes you this money?");
                String who = input.next();
                System.out.println("You are owed " + amount + " dollars by " + who);
                logResult(person, amount, owe, who);
            } else {
                System.out.println("Please enter the amount you owe");
                int amount = input.nextInt();
                System.out.println("Who do you owe this money to?");
                String who = input.next();
                System.out.println("You owe " + amount + " dollars to " + who);
                logResult(person, amount, owe, who);
            }

            System.out.println("Are you finished imputing numbers? (Type Yes or No)");

            String done = input.next();
            if (done.equals("Yes")) {
                break; // referenced from B04 simple calculator lecture lab

            }
        }
    }

    private void logResult(Person person, int amount, String owe, String who) {
        person.setAmount(amount);
        person.setOweorowed(owe);
        person.setWho(who);
        listofpeople.add(person); //referenced from B04 lec lab
    }
}