package info;

import java.io.Serializable;

public class NormalDebt extends Debt implements Serializable {

    @Override
    public String reminder() {
        if (oweOrOwed.equals("Owe")) {
            return "You owe " + who + " " + amount + " dollars.";
        } else {
            return who + " owes you " + amount + " dollars.";
        }
    }

}

