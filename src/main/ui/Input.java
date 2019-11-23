package ui;

import info.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class Input implements ActionListener {
    private NormalUrgentDebtsList normalUrgentDebtsList;
    private RecurringDebtsList recurringDebtsList;
    private Debt debt;
    private int amount;
    private String who;
    private String oweOrOwed;
    private String dueDate;
    private JFrame frame;
    private JPanel jpanel;
    private JLabel label;
    private JTextArea labelTwo;
    private JTextField field;
    private String fieldInput;
    private Boolean enterClicked;
    private String stringList;
    private Boolean value;
    private Boolean loop;


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        new Input();


    }

    public Input() throws IOException, ClassNotFoundException, InterruptedException {
        frame = new JFrame("Debt Recorder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 200));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        jpanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(boxlayout);

        label = new JLabel("");
        labelTwo = new JTextArea("\n \n");
        field = new JTextField(10);
        JButton btn = new JButton("Enter");

        btn.setActionCommand("myButton");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTwo.setAlignmentX(Component.LEFT_ALIGNMENT); //taken from:https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
        labelTwo.setEditable(false);
        jpanel.add(label, "North");
        jpanel.add(field, "Center");
        jpanel.add(btn, "East");
        jpanel.add(labelTwo, "South");
        frame.add(jpanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);  //taken from D11 example
        normalUrgentDebtsList = new NormalUrgentDebtsList();
        recurringDebtsList = new RecurringDebtsList();
        dueDate = "";
        fieldInput = "";
        stringList = "";
        enterClicked = false;
        value = false;
        loop = false;
        run();
    }

    //EFFECTS: calls down the structure of the program, once everything is done saves
    public void run() throws IOException, ClassNotFoundException, InterruptedException {
        askLoadOrNew();
        afterRun();
        normalUrgentDebtsList.save();
        recurringDebtsList.save();
        label.setText("Please close the program to save.");
    }

    //MODIFIES: this
    //EFFECTS: runs a loop that gets user input for data
    private void afterRun() throws InterruptedException {
        while (!loop) {
            askInputPrint();
            value = false;
            while (!value) {
                label.setText("Would you like to continue? (Type 'Yes' or 'No')");
                enterClicked = false;
                delayProgram();
                String done = fieldInput;
                if (done.equalsIgnoreCase("No")) {
                    loop = true;
                    value = true;
                } else if (!done.equalsIgnoreCase("Yes")) {
                    wrongInput();
                } else {
                    value = true;
                }
            }
        }
    }

    //EFFECTS: calls method to ask the what the  user wants to do, prints current list of debts
    private void askInputPrint() throws InterruptedException {
        askInput();
        printRegularList();
    }

    //EFFECTS: delays program by 10 miliseconds so the while loops work
    private void delayProgram() throws InterruptedException {
        while (!enterClicked) {
            Thread.sleep(10);
        }
    }


    //EFFECTS: logs the recurring debt to a list of recurring debts, catching exception if parameters error
    private void logRecurrentResult() throws InterruptedException {
        try {
            recurringDebtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
            recurringDebtsList.addListRe(normalUrgentDebtsList, debt);
        } catch (IntException e) {
            JOptionPane.showMessageDialog(frame,
                    "You entered a negative or zero amount!\nPlease enter your entry again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            askDebtType();
        } catch (OweException o) {
            JOptionPane.showMessageDialog(frame,
                    "You did not state whether this person owes or is owed by you!\n"
                            + "Please enter you entry again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            askDebtType();
        }
    }

    //EFFECTS: logs a normal or urgent debt to a list of normal/urgent debts catching exceptions if parameters error
    private void logRegularResult() throws InterruptedException {
        try {
            normalUrgentDebtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
            normalUrgentDebtsList.addList(debt);
        } catch (IntException e) {
            JOptionPane.showMessageDialog(frame,
                    "You entered a negative or zero amount!\nPlease enter your entry again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            askDebtType();
        } catch (OweException o) {
            JOptionPane.showMessageDialog(frame,
                    "You did not state whether this person owes or is owed by you!\n"
                            + "Please enter you entry again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            askDebtType();
        }
    }

    //MODIFIES: this
    //EFFECTS: asks user if they want to view their debts or create or delete a debt
    private void askInput() throws InterruptedException {
        label.setText("Would you like to create or delete an entry? (Type 'Create' or 'Delete')");
        Thread.sleep(10);
        enterClicked = false;
        delayProgram();
        enterClicked = false;
        askCreateOrDeleteDebt(fieldInput);

    }

    //EFFECTS: prints wrong input statement
    private void wrongInput() {
        JOptionPane.showMessageDialog(frame, "You didn't enter a recognized answer!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    //MODIFIES: this
    //EFFECTS: asks user if they want to create or delete a debt, if delete asks for the number next to the debt
    private void askCreateOrDeleteDebt(String answer) throws InterruptedException {
        enterClicked = false;
        if (answer.equalsIgnoreCase("create")) {
            askDebtType();
        } else if (answer.equalsIgnoreCase("delete")) {
            if (!normalUrgentDebtsList.getListOfDebt().isEmpty()) {
                label.setText("Type the number next to the debt you would like to delete, "
                        + "or type any other number to cancel.");
                delayProgram();
                int ans = Integer.parseInt(fieldInput);
                deleteDebt(ans);
            } else {
                JOptionPane.showMessageDialog(frame, "There are no debts to delete!");
            }
        } else {
            wrongInput();
        }

    }

    //EFFECTS: deletes the selected debt if user input is a registered debt
    private void deleteDebt(int ans) {
        if (ans > 0 && ans < normalUrgentDebtsList.getListOfDebt().size()) {
            normalUrgentDebtsList.removeList(recurringDebtsList, normalUrgentDebtsList.getSpecificDebt(ans));
            JOptionPane.showMessageDialog(frame,"That debt is paid off!");
        }
    }

    //MODIFIES: this
    //EFFECTS: asks for the debt type the user wants to create
    private void askDebtType() throws InterruptedException {
        askDebtTypeString();
        while (!enterClicked) {
            Thread.sleep(100);
        }
        enterClicked = false;
        if (fieldInput.equalsIgnoreCase("Regular")) {
            normalDebt();
            logRegularResult();
        } else if (fieldInput.equalsIgnoreCase("Urgent")) {
            urgentDebt();
            logRegularResult();
        } else if (fieldInput.equalsIgnoreCase("Recurring")) {
            recurringDebt();
            logRecurrentResult();
        } else {
            wrongInput();
        }

    }

    //EFFECTS: helper function to ask user question
    private void askDebtTypeString() {
        label.setText("Would you like to create an urgent, regular, or recurring debt? (Type 'Urgent', 'regular' or "
                + "'recurring')");
    }

    //MODIFIES: this
    //EFFECTS: creates a recurring debt
    private void recurringDebt() throws InterruptedException {
        debt = new RecurringDebt();
        label.setText("How often is this debt due? (every '...')");
        delayProgram();
        enterClicked = false;
        dueDate = fieldInput;
        debtInput();
    }

    //EFFECTS: creates a normal debt
    public void normalDebt() throws InterruptedException {
        debt = new NormalDebt();
        debtInput();
    }

    //MODIFIES: this
    //EFFECTS: creates an urgent debt
    public void urgentDebt() throws InterruptedException {
        debt = new UrgentDebt();
        label.setText("What is the date this debt is due?");
        delayProgram();
        enterClicked = false;
        dueDate = fieldInput;
        debtInput();

    }

    //MODIFIES: this
    //EFFECTS: asks user if they want to load a previous list or create a new list, load list if user says load
    public void askLoadOrNew() throws IOException, ClassNotFoundException, InterruptedException {
        boolean value = false;
        while (!value) {
            enterClicked = false;
            label.setText("Would you like to load a previous list or make a new list of debt? (Type 'Load' or 'New')");
            delayProgram();
            if (fieldInput.equalsIgnoreCase("Load")) {
                recurringDebtsList.load();
                normalUrgentDebtsList.load();
                printRegularList();
                value = true;
            } else if (!fieldInput.equalsIgnoreCase("New")) {
                wrongInput();
            } else {
                value = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: passes user input into logResult to be logged
    public void debtInput() throws InterruptedException {
        label.setText("Do you owe money or are you owed money? (Type Owe or Owed)");
        enterClicked = false;
        delayProgram();
        oweOrOwed = fieldInput;
        enterClicked = false;
        if (oweOrOwed.equalsIgnoreCase("Owed")) {
            askOwed();
        } else if (oweOrOwed.equalsIgnoreCase("Owe")) {
            askOwe();
        } else {
            wrongInput();
        }
    }


    private void askOwe() throws InterruptedException {
        label.setText("Please enter the amount you owe (No dollar signs please)");
        delayProgram();
        amount = Integer.parseInt(fieldInput);
        label.setText("Who do you owe this money to?");
        enterClicked = false;
        delayProgram();
        who = fieldInput;
    }

    private void askOwed() throws InterruptedException {
        label.setText("Please enter the amount owed to you (No dollar signs please)");
        delayProgram();
        amount = Integer.parseInt(fieldInput);
        label.setText("Who owes you this money?");
        enterClicked = false;
        delayProgram();
        who = fieldInput;
    }

    //EFFECTS: prints the list of all debts
    public void printRegularList() {
        stringList = "";
        int i = 1;
        for (Debt debt : normalUrgentDebtsList.getListOfDebt()) {
            stringList = stringList + i + ". " + debt.reminder() + "\n";
            i = i + 1;
        }
        labelTwo.setText(stringList);
    }

    //    /**
//     * Invoked when an action occurs.
//     *
//     * @param
//     */
    //EFFECTS: saves the user input in field to a string when the enter button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            fieldInput = field.getText();
            field.setText("");
            enterClicked = true;
        }
    }
}