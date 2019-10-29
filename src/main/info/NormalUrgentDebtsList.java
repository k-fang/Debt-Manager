package info;

import java.io.*;
import java.util.Map;

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
        debt.setDueDate(d);
        debt.setWho(w);
        //addMap(debt);
       /* addList(debt);*/

    }

    @Override
    public Map<String, Debt> getMapOfDebts() {
        return mapOfDebts;
    }

    @Override
    public Debt getSpecificDebt(String s) {
        return mapOfDebts.get(s);
    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfDebt
    public void addMap(Debt debt) {
        if (!mapOfDebts.containsKey(debt.getWho())) {
            mapOfDebts.put(debt.getWho(), debt);
        }
    }

    /*@Override*/
    public void removeValue(RecurringDebtsList rdl, Debt debt) {
        if (mapOfDebts.containsKey(debt.getWho())) {
            mapOfDebts.remove(debt.getWho());
            rdl.removeValue(this, debt);
        }

    }

   /* @Override
    public ArrayList<Debt> getListOfDebt() {
        return listOfDebt;
    }*/

    //EFFECTS: saves listofDebt to a file
    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(mapOfDebts);
        oos.close();
    }

    //EFFECTS: loads listofDebt from a file
    @Override
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        mapOfDebts = (Map<String, Debt>) ois.readObject();
        ois.close();
    }

}
