package info;

public class NormalUrgentDebtsList extends DebtsList {

    // MODIFIES: Debt, this
    // EFFECTS: sets passed parameters to Debt, and adds debt to listOfDebt
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
