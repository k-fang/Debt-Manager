package info;

public class ConfirmationObserver {
    private int numberOfDebtsAdded;
    private int numberOfDebtsDeleted;

    public void update() {
        System.out.println("Debt has been added successfully.");
    }
    
}
