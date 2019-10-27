package info;

import java.io.Serializable;

public class RecurringDebt extends Debt implements Serializable {
    @Override
    public String reminder() {
        if (oweOrOwed.equals("Owe")) {
            return "You owe " + who + " " + amount + " dollars every " + dueDate;
        } else {
            return who + " owes you " + amount + " dollars every " + dueDate;
        }
    }
}
