package info;

import java.io.Serializable;

public abstract class Debt implements Serializable {

    protected int amount;
    protected String oweOrOwed;
    protected String who;
    protected String dueDate;


    //EFFECTS: logs parameters to debt fields
    public void logResult(int amt, String o, String w, String d) {
        setAmount(amt);
        setOweOrOwed(o);
        setWho(w);
        setDueDate(d);
    }

    //EFFECTS: returns int amount of money owe or owed
    public int getAmount() {
        return amount;
    }

    //EFFECTS: returns string "owe" or "owed"
    public String getOweOrOwed() {
        return oweOrOwed;
    }

    //EFFECTS: returns name of person in list
    public String getWho() {
        return who;
    }


    //REQUIRES: int amount
    //MODIFIES: this
    //EFFECTS: sets amount to the passed parameter from user
    public void setAmount(int amount) {
        this.amount = amount;
    }

    //REQUIRES: String of either "Owed" or "Owe"
    //MODIFIES: this
    //EFFECTS: sets oweOrOwed to either "Owed" or "Owe" depending on user input
    public void setOweOrOwed(String oweOrOwed) {
        this.oweOrOwed = oweOrOwed;
    }

    //REQUIRES: String
    //MODIFIES: this
    //EFFECTS: sets who to name of person who owes or is owed money
    public void setWho(String who) {
        this.who = who;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public abstract String reminder();



}


