package com.mycompany.quickpay;

import java.util.Scanner;

public class Quickpay {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ===== Users data (each index = one user) =====
        String[] names = {"Yousef", "Hana", "Eyad", "Marym"};
        String[] emails = {"yousef@gmail.com", "hana@gmail.com", "eyad@gmail.com", "marym@gmail.com"};
        String[] phones = {"01211223345", "01211223346", "01211223347", "01211223348"};
        double[] balances = {1000, 500, 750, 300};
        boolean[] active = {true, true, true, true};

        // ===== Transactions data (each index = one transaction) =====
        String[] trSender = new String[100];
        String[] trReceiver = new String[100];
        double[] trAmount = new double[100];
        String[] trStatus = new String[100];
        int trCount = 0;

        // The logged in user is the first one (Yousef)
        int me = 0;
        System.out.println("Logged in as: " + names[me] + " | Balance: " + balances[me] + " EGP");

        int choice = 0;

        while (choice != 4) {
            System.out.println("\n========= USER PANEL =========");
            System.out.println("1. Search Recipient");
            System.out.println("2. Transfer Money");
            System.out.println("3. Show Transaction History");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String text = input.nextLine().trim();
            if (text.equals("1")) {
                choice = 1;
            } else if (text.equals("2")) {
                choice = 2;
            } else if (text.equals("3")) {
                choice = 3;
            } else if (text.equals("4")) {
                choice = 4;
            } else {
                choice = 0;
            }

            // ---------- 1. Search ----------
            if (choice == 1) {
                System.out.print("Enter email or phone to search: ");
                String search = input.nextLine().trim();

                int found = -1;
                for (int i = 0; i < emails.length; i++) {
                    if (emails[i].equalsIgnoreCase(search) || phones[i].equals(search)) {
                        found = i;
                    }
                }

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

            // ---------- 2. Transfer ----------
            else if (choice == 2) {
                System.out.println("\n--- Transfer Money ---");
                System.out.print("Enter recipient's Email or Phone: ");
                String search = input.nextLine().trim();

                int to = -1;
                for (int i = 0; i < emails.length; i++) {
                    if (emails[i].equalsIgnoreCase(search) || phones[i].equals(search)) {
                        to = i;
                    }
                }

                if (to == -1) {
                    System.out.println("[Error] User not found.");
                } else {
                    System.out.println("Recipient: " + names[to]);
                    System.out.print("Enter amount to transfer (EGP): ");

                    double amount = 0;
                    boolean validNumber = true;
                    try {
                        amount = Double.parseDouble(input.nextLine().trim());
                    } catch (NumberFormatException e) {
                        validNumber = false;
                    }

                    if (!validNumber) {
                        System.out.println("[Error] Please enter a valid amount.");
                    } else {
                        // Check all the problems one by one
                        String error = "";

                        if (to == me) {
                            error = "You cannot transfer money to yourself.";
                        } else if (!active[me]) {
                            error = "Your account is not active.";
                        } else if (!active[to]) {
                            error = "Recipient's account is not active.";
                        } else if (amount <= 0) {
                            error = "Amount must be greater than zero.";
                        } else if (amount > balances[me]) {
                            error = "Insufficient balance! Your balance is: " + balances[me] + " EGP";
                        }

                        if (!error.equals("")) {
                            // Failed transaction
                            if (trCount < 100) {
                                trSender[trCount] = emails[me];
                                trReceiver[trCount] = emails[to];
                                trAmount[trCount] = amount;
                                trStatus[trCount] = "FAILED - " + error;
                                trCount++;
                            }
                            System.out.println("[Error] " + error);
                        } else {
                            // Successful transaction
                            balances[me] = balances[me] - amount;
                            balances[to] = balances[to] + amount;

                            if (trCount < 100) {
                                trSender[trCount] = emails[me];
                                trReceiver[trCount] = emails[to];
                                trAmount[trCount] = amount;
                                trStatus[trCount] = "SUCCESS";
                                trCount++;
                            }
                            System.out.println("[Success] Transfer completed successfully!");
                            System.out.println("Your new balance: " + balances[me] + " EGP");
                        }
                    }
                }
            }

            // ---------- 3. History ----------
            else if (choice == 3) {
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

            // ---------- 4. Exit ----------
            else if (choice == 4) {
                System.out.println("Exiting...");
            }

            // ---------- Wrong input ----------
            else {
                System.out.println("Invalid choice!");
            }
        }

        input.close();
    }
}