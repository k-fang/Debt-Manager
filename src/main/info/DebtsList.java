package info;

import java.io.*;
import java.util.ArrayList;

public abstract class DebtsList implements Serializable, Loadable, Saveable {

    protected ArrayList<Debt> listOfDebt;
    protected ArrayList<Debt> listOfRecurringDebt;


    public DebtsList() {
        listOfDebt = new ArrayList<>();
        listOfRecurringDebt = new ArrayList<>();
    }


    // MODIFIES: Debt, this
    // EFFECTS: sets passed parameters to Debt, and adds debt to listOfDebt
    public void logResult(Debt debt, int amt, String oweOr, String who, String dueD) throws IntException, OweException {
        if (amt <= 0) {
            throw new IntException();
        }
        debt.setAmount(amt);
        if (!oweOr.equalsIgnoreCase("Owe") && !oweOr.equalsIgnoreCase("Owed")) {
            throw new OweException();
        }
        debt.setOweOrOwed(oweOr);
        debt.setWho(who);
        debt.setDueDate(dueD);
        addList(debt);

    }


    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfDebt
    public void addList(Debt debt) {
        listOfDebt.add(debt);
    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfRecurringDebt
    public void addListRe(Debt debt) {
        listOfRecurringDebt.add(debt);
    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: removes the given debt from listOfDebt
    public abstract void removeList(Debt debt);
      /*  if (listOfDebt.contains(debt)) {
            listOfDebt.remove(debt);
            removeListRe(debt);
        }*/

/*
    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: removes the given debt from listOfRecurringDebt
    public void removeListRe(Debt debt) {
        if (listOfRecurringDebt.contains(debt)) {
            listOfRecurringDebt.remove(debt);
            removeList(debt);
        }
    }*/



    //EFFECTS: returns listOfDebt
    public ArrayList<Debt> getListOfDebt() {
        return listOfDebt;
    }

    //EFFECTS: returns listOfDebt
    public ArrayList<Debt> getListOfDebtRe() {
        return listOfRecurringDebt;
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
    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the




}
