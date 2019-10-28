package info;

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
        debt.setWho(w);
        debt.setDueDate(d);
        addListRe(debt);

    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfRecurringDebt
    public void addListRe(Debt debt) {
        listOfRecurringDebt.add(debt);

    }


    @Override
    public void removeList(Debt debt) {
        if (listOfRecurringDebt.contains(debt)) {
            listOfRecurringDebt.remove(debt);
            /*normalUrgentDebtsList.removeList(debt);*/
        }
    }
}
