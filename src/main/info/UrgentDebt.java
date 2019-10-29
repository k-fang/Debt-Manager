package info;

import java.io.Serializable;

public class UrgentDebt extends Debt implements Serializable {


    @Override
    public String reminder() {
        if (oweOrOwed.equalsIgnoreCase("Owe")) {
            return "You owe " + who + " " + amount + " dollars by " + dueDate;
        } else {
            return who + " owes you " + amount + " dollars by " + dueDate;
        }
    }

// create something where i gotta pull out the name if someone requests it


}

