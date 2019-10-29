package info;

import java.io.*;
import java.util.Map;

public class RecurringDebtsList extends DebtsList {

    // MODIFIES: Debt, this
    // EFFECTS: sets passed parameters to Debt, and adds debt to listOfDebt
    public void logResult(Debt debt, int amt, String o, String w, String d) throws IntException, OweException {
        if (amt <= 0) {
            throw new IntException();
        }
        debt.setAmount(amt);
        if (!o.equalsIgnoreCase("Owe") && !o.equalsIgnoreCase("Owed")) {
            throw new OweException();
        }
        debt.setOweOrOwed(o);
        debt.setDueDate(d);
        debt.setWho(w);
        //addMap(regularDebtsList, debt);
        /*addListRe(debt);*/
    }

    @Override
    public Map<String, Debt> getMapOfDebts() {
        return mapOfRecurringDebts;
    }

    @Override
    public Debt getSpecificDebt(String s) {
        return mapOfRecurringDebts.get(s);
    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfRecurringDebt
    public void addMap(NormalUrgentDebtsList ndl, Debt debt) {
        mapOfRecurringDebts.put(debt.getWho(), debt);
        ndl.addMap(debt);

    }


    /*@Override*/
    public void removeValue(NormalUrgentDebtsList ndl, Debt debt) {
        if (mapOfRecurringDebts.containsKey(debt.getWho())) {
            mapOfRecurringDebts.remove(debt.getWho());
            ndl.removeValue(this, debt);
        }
    }

    /*@Override
    public ArrayList<Debt> getListOfDebt() {
        return listOfRecurringDebt;*/


    //EFFECTS: saves listofDebt to a file
    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp2");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(mapOfRecurringDebts);
        oos.close();
    }

    //EFFECTS: loads listofDebt from a file
    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp2");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        mapOfRecurringDebts = (Map<String, Debt>) ois.readObject();
        ois.close();
    }
    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
}
