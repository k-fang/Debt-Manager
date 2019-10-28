package info;

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
        addList(debt);

    }

    // REQUIRES: Debt
    // MODIFIES: this
    // EFFECTS: adds a new debt to listOfDebt
    public void addList(Debt debt) {
        listOfDebt.add(debt);
    }

    @Override
    public void removeList(Debt debt) {

        if (listOfDebt.contains(debt)) {
            listOfDebt.remove(debt);
            /*removeList(debt);*/
        }

    }
}
