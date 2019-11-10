package info;

public class ConfirmationObserver {
    private int numberOfDebtsAdded;
    private int numberOfDebtsDeleted;

    public ConfirmationObserver() {
        numberOfDebtsDeleted = 0;
        numberOfDebtsAdded = 0;
    }

    public void update() {
        System.out.println("Debt has been added successfully.");
    }

    public void addDebt() {
        numberOfDebtsAdded = numberOfDebtsAdded + 1;
    }

    public void addDeletedDebt() {
        numberOfDebtsDeleted = numberOfDebtsDeleted + 1;
    }

    public void printStatistics() {
        System.out.println("Number of Debts you added: " + numberOfDebtsAdded
                + "\nNumber of Debts that have been paid off: " + numberOfDebtsDeleted);
    }
}
