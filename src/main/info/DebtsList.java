package info;

import java.io.*;
import java.util.ArrayList;

public abstract class DebtsList extends Subject implements Serializable, Loadable, Saveable {

    //protected ArrayList<Debt> listOfDebt;
  //  protected ArrayList<Debt> listOfRecurringDebt;



    public DebtsList() {

       // listOfDebt = new ArrayList<>();
        //listOfRecurringDebt = new ArrayList<>();
    }

    // MODIFIES: Debt
    // EFFECTS: sets passed parameters to Debt, and adds debt to listOfDebt
    public void logResult(Debt debt, int amt, String o, String w, String d) throws IntException, OweException {
        if (amt <= 0) {
            throw new IntException();
        }
        if (!o.equalsIgnoreCase("Owe") && !o.equalsIgnoreCase("Owed")) {
            throw new OweException();
        }
        debt.logResult(amt, o, w, d);
    }

   /* public Debt getSpecificDebt(int i) {
        return listOfDebt.get(i - 1);
    }*/


    //EFFECTS: returns listOfDebt
    public abstract ArrayList<Debt> getListOfDebt();




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
