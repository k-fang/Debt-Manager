package info;

import java.io.*;
import java.util.ArrayList;

public class DebtsList implements Serializable, Loadable, Saveable {

    private ArrayList<Debt> listOfDebt;
    private NormalDebt normalDebt;

    public DebtsList() {
        listOfDebt = new ArrayList<>();
    }

    // REQUIRES: Person, int amount, string either "Owe" or "Owed", String for name
    // MODIFIES: this
    // EFFECTS: sets passed parameters to Person person
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


    // REQUIRES: Person
    // MODIFIES: this
    // EFFECTS: adds a new person to listOfPeople
    public void addList(Debt person) {
        listOfDebt.add(person);
    }

    //EFFECTS: returns listOfPeople
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
    // taken from https://stackoverflow.com/questions/16111496/
    // java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the


    public String getNormalDebt() {
        return normalDebt.getWho();
    }
}
