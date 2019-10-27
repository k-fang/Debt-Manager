package info;

public class NormalUrgentDebtsList extends DebtsList {
    @Override
    public void removeList(Debt debt) {

        if (listOfDebt.contains(debt)) {
            listOfDebt.remove(debt);
            /*removeList(debt);*/
        }

    }
}
