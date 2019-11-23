package info;

import java.io.*;
import java.util.ArrayList;

public class NormalUrgentDebtsList extends DebtsList {
    private ArrayList<Debt> listOfDebt;

    public NormalUrgentDebtsList() {
        listOfDebt = new ArrayList<>();

    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfDebt
    public void addList(Debt debt) {
        if (!listOfDebt.contains(debt)) {
            listOfDebt.add(debt);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a given debt from the listofDebt
    public void removeList(RecurringDebtsList rdl, Debt debt) {
        if (listOfDebt.contains(debt)) {
            listOfDebt.remove(debt);
            rdl.removeList(this, debt);
        }
    }

    //EFFECTS: returns listofDebt
    @Override
    public ArrayList<Debt> getListOfDebt() {
        return listOfDebt;
    }

    //EFFECTS: gets the ith debt in a list
    public Debt getSpecificDebt(int i) {
        return listOfDebt.get(i - 1);
    }


    //EFFECTS: saves listofDebt to a file
    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listOfDebt);
        oos.close();
    }

    //EFFECTS: loads listofDebt from a file
    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        listOfDebt = (ArrayList<Debt>) ois.readObject();
        ois.close();
    }

}
