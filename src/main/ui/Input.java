package ui;

import info.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class Input extends JFrame implements ActionListener {
    private NormalUrgentDebtsList normalUrgentDebtsList;
    private RecurringDebtsList recurringDebtsList;
    private Debt debt;
    private int amount;
    private String who;
    private String oweOrOwed;
    private String dueDate;
    private JLabel label;
    private JTextField field;
    private String fieldInput;
    private Boolean enterClicked;


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        new Input();


    }

    public Input() throws IOException, ClassNotFoundException, InterruptedException {
        super("Debt Recorder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        label = new JLabel("");
        field = new JTextField(10);
        JButton btn = new JButton("Enter");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); //sets "this" class as an action listener for btn.
        add(label);
        add(field);
        add(btn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);  //taken from D11 example
        normalUrgentDebtsList = new NormalUrgentDebtsList();
        recurringDebtsList = new RecurringDebtsList();
        dueDate = "";
        fieldInput = "";
        enterClicked = false;
        run();
    }

    //EFFECTS: runs a loop that gets user input for data
    public void run() throws IOException, ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        askLoadOrNew();
        while (true) {
            askViewOrInput();
            System.out.println("Would you like to continue? (Type Yes or No)");
            String done = input.next();
            if (done.equalsIgnoreCase("No")) {
                break;
            }
        }
        normalUrgentDebtsList.save();
        recurringDebtsList.save();
    }
    /////

    //EFFECTS: logs the recurring debt to a list of recurring debts, catching exception if parameters error
    private void logRecurrentResult() {
        try {
            recurringDebtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
            recurringDebtsList.addListRe(normalUrgentDebtsList, debt);
        } catch (IntException e) {
            System.out.println("You entered a negative or zero amount!\nPlease enter your entry again.");
            askDebtType();
        } catch (OweException o) {
            System.out.println("You did not state whether this person owes or is owed by you!\n"
                    + "Please enter you entry again.");
            askDebtType();
        }
    }

    //EFFECTS: logs a normal or urgent debt to a list of normal/urgent debts catching exceptions if parameters error
    private void logRegularResult() {
        try {
            normalUrgentDebtsList.logResult(debt, amount, oweOrOwed, who, dueDate);
            normalUrgentDebtsList.addList(debt);
        } catch (IntException e) {
            System.out.println("You entered a negative or zero amount!\nPlease enter your entry again.");
            /*userInput();*/
            askDebtType();
        } catch (OweException o) {
            System.out.println("You did not state whether this person owes or is owed by you!\n"
                    + "Please enter you entry again.");
            askDebtType();
        }
    }

    //EFFECTS: asks user if they want to view their debts or create or delete a debt
    private void askViewOrInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose to view your Debts or create/delete an entry. (Type 'View', 'Create' or 'Delete')");
        String answer = input.next();
        if (answer.equalsIgnoreCase("view")) {
            System.out.println("Would you like to view your recurring or all your debts? (Type 'Recurring' or 'All')");
            String answerTwo = input.next();
            if (answerTwo.equalsIgnoreCase("all") && !normalUrgentDebtsList.getListOfDebt().isEmpty()) {
                printRegularList();
            } else if (answerTwo.equalsIgnoreCase("recurring") && !recurringDebtsList.getListOfDebt().isEmpty()) {
                printRecurringList();
            } else if (!answerTwo.equalsIgnoreCase("recurring") && !answerTwo.equalsIgnoreCase("all")) {
                wrongInput();
            } else {
                System.out.println("You have no debts in that category!");
            }
        } else {
            askCreateOrDeleteDebt(answer);
        }
    }

    //EFFECTS: prints wrong input statement
    private void wrongInput() {
        System.out.println("You didn't enter a recognized answer!");
    }

    //EFFECTS: asks user if they want to create or delete a debt, if delete asks for the number next to the debt
    private void askCreateOrDeleteDebt(String answer) {
        if (answer.equalsIgnoreCase("create")) {
            askDebtType();
        } else if (answer.equalsIgnoreCase("delete")) {
            if (!normalUrgentDebtsList.getListOfDebt().isEmpty()) {
                Scanner input = new Scanner(System.in);
                System.out.println("Type the number next to the debt you would like to delete, "
                        + "or type any other number to cancel.");
                printRegularList();
                int ans = input.nextInt();
                deleteDebt(ans);
            } else {
                System.out.println("There are no debts to delete.");
            }
        } else {
            wrongInput();
        }

    }

    //EFFECTS: deletes the selected debt if user input is a registered debt
    private void deleteDebt(int ans) {
        if (ans > 0 && ans < normalUrgentDebtsList.getListOfDebt().size()) {
            normalUrgentDebtsList.removeList(recurringDebtsList, normalUrgentDebtsList.getSpecificDebt(ans));
            System.out.println("That debt is paid off!");
        }
    }

    //EFFECTS: asks for the debt type the user wants to create
    private void askDebtType() {
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like to do:\n"
                + "Create an urgent debt (Type 'Urgent')\n"
                + "Create a regular debt (Type 'Regular')\n"
                + "Create a recurring debt (Type 'Recurring')\n");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Regular")) {
            normalDebt();
            logRegularResult();
        } else if (answer.equalsIgnoreCase("Urgent")) {
            urgentDebt();
            logRegularResult();
        } else if (answer.equalsIgnoreCase("Recurring")) {
            recurringDebt();
            logRecurrentResult();

        } else {
            wrongInput();
        }
    }

    //EFFECTS: creates a recurring debt
    private void recurringDebt() {
        Scanner input = new Scanner(System.in);
        debt = new RecurringDebt();
        System.out.println("How often is this debt due? (every '...')");
        dueDate = input.next();
        debtInput(debt);
    }

    //EFFECTS: creates a normal debt
    public void normalDebt() {
        debt = new NormalDebt();
        debtInput(debt);


    }

    //EFFECTS: creates an urgent debt
    public void urgentDebt() {
        Scanner input = new Scanner(System.in);
        debt = new UrgentDebt();
        System.out.println("What is the date this debt is due?");
        dueDate = input.next();
        debtInput(debt);

    }

    //EFFECTS: asks user if they want to load a previous list or create a new list, load list if user says load
    public void askLoadOrNew() throws IOException, ClassNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        label.setText("Would you like to load a previous list or create a new list of debts? "
                + "(Type 'Load' or 'New')");
//        System.out.println("Would you like to load a previous list or create a new list of debts? "
//                + "(Type 'Load' or 'New')");
        //String loadOrNew = input.next();
        //if (loadOrNew.equalsIgnoreCase("Load")) {
        while (!enterClicked) {
            Thread.sleep(100);
            if (fieldInput.equalsIgnoreCase("Load")) {
                recurringDebtsList.load();
                normalUrgentDebtsList.load();
                System.out.println("this shit worked");
            }
        }
        
    }

    // REQUIRES: Person
    // EFFECTS: passes user input into logResult to be logged
    public void debtInput(Debt person) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you owe money or are you owed money? (Type Owe or Owed)");
        oweOrOwed = input.next();
        if (oweOrOwed.equalsIgnoreCase("Owed")) {
            System.out.println("Please enter the amount owed to you (No dollar signs please)");
            amount = input.nextInt();
            System.out.println("Who owes you this money?");
            who = input.next();
        } else if (oweOrOwed.equalsIgnoreCase("Owe")) {
            System.out.println("Please enter the amount you owe (No dollar signs please)");
            amount = input.nextInt();
            System.out.println("Who do you owe this money to?");
            who = input.next();
        } else {
            wrongInput();
        }
    }

    //EFFECTS: prints the list of all debts
    public void printRegularList() {
        int i = 1;
        for (Debt debt : normalUrgentDebtsList.getListOfDebt()) {
            System.out.println(i + ". " + debt.reminder());
            i = i + 1;
        }
    }

    //EFFECTS: prints the list of recurring debts
    public void printRecurringList() {
        int i = 1;
        for (Debt debt : recurringDebtsList.getListOfDebt()) {
            System.out.println(i + ". " + debt.reminder());
            i = i + 1;
        }
    }

//    /**
//     * Invoked when an action occurs.
//     *
//     * @param
//     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            fieldInput = field.getText();
            enterClicked = true;
        }
    }
}