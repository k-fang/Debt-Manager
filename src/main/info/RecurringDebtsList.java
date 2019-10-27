package info;

public class RecurringDebtsList extends DebtsList {
    private NormalUrgentDebtsList normalUrgentDebtsList;



    @Override
    public void removeList(Debt debt) {
        if (listOfRecurringDebt.contains(debt)) {
            listOfRecurringDebt.remove(debt);
            /*normalUrgentDebtsList.removeList(debt);*/
        }
    }
}
