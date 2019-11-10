package info;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<ConfirmationObserver> confirmationObservers;

    public Subject() {
        confirmationObservers = new ArrayList<>();
    }

    public void addObserver(ConfirmationObserver observer) {
        if (!confirmationObservers.contains(observer)) {
            confirmationObservers.add(observer);
        }
    }

    public void removeObserver(ConfirmationObserver observer) {
        if (confirmationObservers.contains(observer)) {
            confirmationObservers.remove(observer);
        }
    }

    public void notifyObservers() {
        for (ConfirmationObserver observer : confirmationObservers) {
            observer.update();
        }
    }
}
