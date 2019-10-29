package info;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DebtsList implements Serializable, Loadable, Saveable {

    /*protected ArrayList<Debt> listOfDebt;
    protected ArrayList<Debt> listOfRecurringDebt;*/
    protected NormalUrgentDebtsList regularDebtsList;
    protected RecurringDebtsList recurringDebtsList;
    protected Map<String, Debt> mapOfDebts;
    protected Map<String, Debt> mapOfRecurringDebts;

    public DebtsList() {
        /*listOfDebt = new ArrayList<>();
        listOfRecurringDebt = new ArrayList<>();*/
        mapOfDebts = new HashMap<>();
        mapOfRecurringDebts = new HashMap<>();
    }


    // MODIFIES: Debt, this
    // EFFECTS: sets passed parameters to Debt, and adds debt to listOfDebt
    public abstract void logResult(Debt debt, int amt, String o, String w, String d) throws IntException, OweException;
    /*{
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
        addList(debt);

    }
*/
    public abstract Map<String, Debt> getMapOfDebts();

    public abstract Debt getSpecificDebt(String s);
    //{
      //  return mapOfDebts.get(s);
    //}



    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: removes the given debt from listOfDebt
    /*public abstract void removeList(Debt debt);*/
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
   /* public abstract ArrayList<Debt> getListOfDebt();*/ /*{
        return listOfDebt;
    }

    //EFFECTS: returns listOfDebt
    public ArrayList<Debt> getListOfDebtRe() {
        return listOfRecurringDebt;
    }
*/




    //EFFECTS: saves listofDebt to a file
    @Override
    public abstract void save() throws IOException;/* {
        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(listOfDebt);
        oos.close();
    }*/

    //EFFECTS: loads listofDebt from a file
    @Override
    public abstract void load() throws IOException, ClassNotFoundException;/* {
        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        // person = (Person) ois.readObject();
        listOfDebt = (ArrayList<Debt>) ois.readObject();
        ois.close();
    }*/

    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the




}
