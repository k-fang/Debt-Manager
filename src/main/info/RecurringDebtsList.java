package info;

import java.io.*;
import java.util.ArrayList;

public class RecurringDebtsList extends DebtsList {
    private ArrayList<Debt> listOfRecurringDebt;

    public RecurringDebtsList() {
        listOfRecurringDebt = new ArrayList<>();
    }


    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfRecurringDebt
    public void addListRe(NormalUrgentDebtsList ndl, Debt debt) {
        listOfRecurringDebt.add(debt);
        ndl.addList(debt);
    }

    //MODIFIES: this
    //EFFECTS: removes a given debt from the listOfRecurringDebt
    public void removeList(NormalUrgentDebtsList ndl, Debt debt) {
        if (listOfRecurringDebt.contains(debt)) {
            listOfRecurringDebt.remove(debt);
            ndl.removeList(this, debt);
        }
    }

    //EFFECTS: returns listOfRecurringDebt
    @Override
    public ArrayList<Debt> getListOfDebt() {
        return listOfRecurringDebt;
    }

    //EFFECTS: saves listOfDebt to a file
    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp2");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listOfRecurringDebt);
        oos.close();
    }

    //EFFECTS: loads listofDebt from a file
    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp2");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        listOfRecurringDebt = (ArrayList<Debt>) ois.readObject();
        ois.close();
    }
    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
}
