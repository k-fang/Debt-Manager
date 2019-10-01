package ui;

import info.DebtsList;
import info.Person;

import java.io.IOException;
import java.util.ArrayList;

public class Input {
    private ArrayList<Person> listofpeople;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DebtsList d = new DebtsList();
        d.run();


    }


}