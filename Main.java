package com.mycompany.Main;

import java.util.Scanner;

public class Main {

    // ===== Users data (each index = one user) =====
    static String[] names = {"Yousef", "Hana", "Eyad", "Marym"};
    static String[] emails = {"yousef@mail.com", "hana@mail.com", "eyad@mail.com", "marym@mail.com"};
    static String[] phones = {"01211223345", "01211223346", "01211223347", "01211223348"};
    static double[] balances = {1000, 500, 750, 300};
    static boolean[] active = {true, true, true, true};

    // ===== Transactions data (each index = one transaction) =====
    static String[] trSender = new String[1000];
    static String[] trReceiver = new String[1000];
    static double[] trAmount = new double[1000];
    static String[] trStatus = new String[1000];
    static int trCount = 0;

    // The logged in user is the first one (Yousef)
    static int me = 0;

    static Scanner input = new Scanner(System.in);

    // ---------- Show the menu ----------
    public static void showMenu() {
        System.out.println("\n========= USER PANEL =========");
        System.out.println("1. Search Recipient");
        System.out.println("2. Transfer Money");
        System.out.println("3. Show Transaction History");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    // Read the user's choice 
    public static int readChoice() {
        String text = input.nextLine().trim();
        if (text.equals("1")) {
            return 1;
        } else if (text.equals("2")) {
            return 2;
        } else if (text.equals("3")) {
            return 3;
        } else if (text.equals("4")) {
            return 4;
        } else {
            return 0;
        }
    }

    // Find a user by email or phone  
    public static int findUser(String search) {
        int found = -1;
        for (int i = 0; i < emails.length; i++) {
            if (emails[i].equalsIgnoreCase(search) || phones[i].equals(search)) {
                found = i;
            }
        }
        return found;
    }

    // 1. Search 
    public static void searchRecipient() {
        System.out.print("Enter email or phone to search: ");
        String search = input.nextLine().trim();

        int found = findUser(search);

        if (found == -1) {
            System.out.println("[Error] User not found.");
        } else {
            System.out.println("\n[Recipient Found]");
            System.out.println("Name: " + names[found]);
            System.out.println("Email: " + emails[found]);
            System.out.println("Phone: " + phones[found]);
            if (active[found]) {
                System.out.println("Account: Active");
            } else {
                System.out.println("Account: Not active");
            }
            if (found == me) {
                System.out.println("(Note: this is your own account)");
            }
        }
    }

    //Save a transaction in the arrays 
    public static void saveTransaction(int to, double amount, String status) {
        if (trCount < 100) {
            trSender[trCount] = emails[me];
            trReceiver[trCount] = emails[to];
            trAmount[trCount] = amount;
            trStatus[trCount] = status;
            trCount++;
        }
    }

    //  Check the transfer, returns "" if everything is OK
    public static String checkTransfer(int to, double amount) {
        if (to == me) {
            return "You cannot transfer money to yourself.";
        } else if (!active[me]) {
            return "Your account is not active.";
        } else if (!active[to]) {
            return "Recipient's account is not active.";
        } else if (amount <= 0) {
            return "Amount must be greater than zero.";
        } else if (amount > balances[me]) {
            return "Insufficient balance! Your balance is: " + balances[me] + " EGP";
        }
        return "";
    }

    // 2. Transfer
    public static void transferMoney() {
        System.out.println("\n--- Transfer Money ---");
        System.out.print("Enter recipient's Email or Phone: ");
        String search = input.nextLine().trim();

        int to = findUser(search);

        if (to == -1) {
            System.out.println("[Error] User not found.");
            return;
        }

        System.out.println("Recipient: " + names[to]);
        System.out.print("Enter amount to transfer (EGP): ");

        double amount = 0;
        try {
            amount = Double.parseDouble(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[Error] Please enter a valid amount.");
            return;
        }

        String error = checkTransfer(to, amount);

        if (!error.equals("")) {
            saveTransaction(to, amount, "FAILED - " + error);
            System.out.println("[Error] " + error);
        } else {
            balances[me] = balances[me] - amount;
            balances[to] = balances[to] + amount;

            saveTransaction(to, amount, "SUCCESS");
            System.out.println("[Success] Transfer completed successfully!");
            System.out.println("Your new balance: " + balances[me] + " EGP");
        }
    }

    // 3. History 
    public static void showHistory() {
        System.out.println("\n--- Your Transaction History ---");
        int shown = 0;

        for (int i = 0; i < trCount; i++) {
            boolean iSent = trSender[i].equalsIgnoreCase(emails[me]);
            boolean iReceived = trReceiver[i].equalsIgnoreCase(emails[me])
                    && trStatus[i].equals("SUCCESS");

            if (iSent) {
                shown++;
                System.out.println(shown + ". SENT     | To: " + trReceiver[i]
                        + " | Amount: " + trAmount[i] + " EGP | Status: " + trStatus[i]);
            } else if (iReceived) {
                shown++;
                System.out.println(shown + ". RECEIVED | From: " + trSender[i]
                        + " | Amount: " + trAmount[i] + " EGP | Status: " + trStatus[i]);
            }
        }

        if (shown == 0) {
            System.out.println("No transactions found.");
        }
    }

    //  Main 
    public static void main(String[] args) {
        System.out.println("Logged in as: " + names[me] + " | Balance: " + balances[me] + " EGP");

        int choice = 0;

        while (choice != 4) {
            showMenu();
            choice = readChoice();

            if (choice == 1) {
                searchRecipient();
            } else if (choice == 2) {
                transferMoney();
            } else if (choice == 3) {
                showHistory();
            } else if (choice == 4) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice!");
            }
        }

        input.close();
    }
}
