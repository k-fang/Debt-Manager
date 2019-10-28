package info;

import java.io.*;
import java.util.ArrayList;

public class NormalUrgentDebtsList extends DebtsList {

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
        debt.setWho(w);
        debt.setDueDate(d);
       /* addList(debt);*/

    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfDebt
    public void addList(Debt debt) {
        if (!listOfDebt.contains(debt)) {
            listOfDebt.add(debt);
        }
    }

    /*@Override*/
    public void removeList(RecurringDebtsList rdl, Debt debt) {
        if (listOfDebt.contains(debt)) {
            listOfDebt.remove(debt);
            rdl.removeList(this, debt);
        }

    }

    @Override
    public ArrayList<Debt> getListOfDebt() {
        return listOfDebt;
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
