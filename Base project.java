package com.mycompany.main;
import java.util.*;
public class Main {
    static Scanner input = new Scanner(System.in);
    static int login_user_indx;

    public static boolean login_user(String[] Phone_numbers, String[] User_passwords) {
        boolean chek_user_phone = false;
        boolean chek_user_password = false;
        System.out.print("Enter your Phone Number: ");
        String login_user_phone = input.next();
        System.out.print("Enter your password: ");
        String login_user_password = input.next();
        login_user_indx = -1;
        for (int i = 0; i < Phone_numbers.length; i++) {
            if (login_user_phone.equals(Phone_numbers[i])) {
                login_user_indx = i;
                chek_user_phone = true;
            }
        }
        if (chek_user_phone == false) {
            if (login_user_phone.length() != 11)
                System.out.println("Invalid phone number length and its length is: " + login_user_phone.length());
            else System.out.println("this phone number not found !");
        }
        if (login_user_indx != -1) {
            if (login_user_password.equals(User_passwords[login_user_indx]))
                chek_user_password = true;
            else System.out.println("the password is incorrect");
        }
        if (chek_user_password == false || chek_user_phone == false)
            return false;
        else return true;

    }

    public static boolean login_admin(String Admin_email, String Admin_password) {
        boolean chek_admin_email = false;
        boolean chek_admin_password = false;
        System.out.print("Enter your Email : ");
        String login_Admin_Email = input.next();
        System.out.print("Enter your password: ");
        String login_Admin_password = input.next();
        if (login_Admin_Email.equals(Admin_email))
            chek_admin_email = true;
        else {
            System.out.println("this Email not found !");
        }
        if (login_Admin_password.equals(Admin_password))
            chek_admin_password = true;
        else System.out.println("the password is incorrect");
        if (chek_admin_password == false || chek_admin_email == false)
            return false;
        else return true;
    }

    public static void showStatistics(String[] User_names, String[] Account_statuses, String[] Transaction_statuses, long[] Transaction_amounts) {
        int Active_Users = 0;
        int blocked_accounts = 0;
        for (int i = 0; i < Account_statuses.length; i++) {
            if (Account_statuses[i] != null) {
                if (Account_statuses[i].equalsIgnoreCase("active"))
                    Active_Users++;
                else if (Account_statuses[i].equalsIgnoreCase("block"))
                    blocked_accounts++;
            }
        }
        int successful_transactions = 0;
        int failed_transactions = 0;
        long amountofsuccessfultransfers = 0;
        for (int j = 0; j < Transaction_statuses.length; j++) {
            if (Transaction_statuses[j] != null) {
                if ("SUCCESS".equalsIgnoreCase(Transaction_statuses[j])) {
                    successful_transactions++;
                    amountofsuccessfultransfers += Transaction_amounts[j];
                } else if ("FAILED".equalsIgnoreCase(Transaction_statuses[j]))
                    failed_transactions++;
            }
        }
        int Users_count=0;
        for(int i=0;i<User_names.length;i++){
            if(User_names[i]!=null){
                Users_count++;
            }
        }

        System.out.println("Total number of registered users: " + Users_count + "\nNumber of active users: " + Active_Users + "\nNumber of blocked accounts: " + blocked_accounts + "\nTotal number of successful transactions: " + successful_transactions + "\nTotal number of failed transactions: " + failed_transactions + "\nTotal amount of successful transfers: " + amountofsuccessfultransfers);
    }

    public static int searchUser(String Search_User, int[] User_IDs, String[] User_names, String[] User_email_addresses, String[] Phone_numbers) {
        int search_index = -1;
        for (int i = 0; i < User_IDs.length; i++) {
            if (User_names[i] != null)
                if (Search_User.equals(String.valueOf(User_IDs[i])) || Search_User.equalsIgnoreCase(User_names[i]) || Search_User.equals(User_email_addresses[i]) || Search_User.equals(Phone_numbers[i])) {
                    search_index = i;
                    break;
                }
        }
        return search_index;
    }

    public static void viewAllUsers(int[] User_IDs, String[] User_names, String[] User_email_addresses, String[] Phone_numbers, long[] Balances, String[] Account_statuses) {
        for (int i = 0; i < User_IDs.length; i++) {
            if (User_names[i] != null)
                System.out.println("User: " + (1 + i) + "\nUser ID: " + User_IDs[i] + "\nName: " + User_names[i] + "\nPhone number: " + Phone_numbers[i] + "\nEmail address: " + User_email_addresses[i] + "\nUser Balance: " + Balances[i] + "\nAccount Statuses: " + Account_statuses[i]);
        }
    }

    public static int viewUserDetails(String specific_user, int[] User_IDs, String[] User_names, String[] User_email_addresses, String[] Phone_numbers) {
        int search_index = -1;
        for (int i = 0; i < User_IDs.length; i++) {
            if (User_names[i] != null)
                if (specific_user.equals(String.valueOf(User_IDs[i])) || specific_user.equalsIgnoreCase(User_names[i]) || specific_user.equals(User_email_addresses[i]) || specific_user.equals(Phone_numbers[i])) {
                    search_index = i;
                    break;
                }
        }
        return search_index;
    }

    // ==================== 4. SHOW FAVORITES (NESTED LOOP) ====================
    public static void showFavorites(String[][] Favorite_contacts, int[] User_IDs, String[] Phone_numbers, String[] User_email_addresses, String[] User_names) {
        System.out.println("\n--- Favorite Contacts ---");
        boolean hasFavorites = false;

        // Outer loop goes through the logged-in user's favorite slots
        for (int j = 0; j < Favorite_contacts[login_user_indx].length; j++) {
            String favPhone = Favorite_contacts[login_user_indx][j];
            if (favPhone != null) {
                // Inner loop searches the user arrays to display full contact details
                for (int k = 0; k < User_IDs.length; k++) {
                    if (Phone_numbers[k] != null && Phone_numbers[k].equals(favPhone)) {
                        hasFavorites = true;
                        System.out.println((j + 1) + ". Name  : " + User_names[k]);
                        System.out.println("   Phone : " + Phone_numbers[k]);
                        System.out.println("   Email : " + User_email_addresses[k]);
                        System.out.println("----------------------------------------");
                        break;
                    }
                }
            }
        }

        if (hasFavorites == false) {
            System.out.println("Your favorites list is empty.");
        }
    }

    // ==================== 5. ADD FAVORITE (FIXED) ====================
    public static void addFavorite(String[] Phone_numbers, String[] User_email_addresses, String[][] Favorite_contacts, String[] User_names) {
        System.out.println("\n--- Add Favorite ---");
        System.out.print("Enter Email or Phone number of the user to add: ");
        String target = input.next();
        int targetIndex = -1;

        // FIX 1: Check both Phone_numbers AND User_email_addresses (making sure slot isn't null)
        for (int i = 0; i < Phone_numbers.length; i++) {
            if ((Phone_numbers[i] != null && target.equals(Phone_numbers[i])) ||
                    (User_email_addresses[i] != null && target.equalsIgnoreCase(User_email_addresses[i]))) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            System.out.println("User not found in the system!");
            return;
        }

        if (targetIndex == login_user_indx) {
            System.out.println("You cannot add yourself to favorites!");
            return;
        }

        // FIX 2: Added "Favorite_contacts[login_user_indx][j] != null &&" to prevent NullPointerException
        for (int j = 0; j < Favorite_contacts[login_user_indx].length; j++) {
            if (Favorite_contacts[login_user_indx][j] != null && Favorite_contacts[login_user_indx][j].equals(Phone_numbers[targetIndex])) {
                System.out.println(User_names[targetIndex] + " is already in your favorites!");
                return;
            }
        }

        // Find the first empty slot in the 2D array
        boolean added = false;
        for (int j = 0; j < Favorite_contacts[login_user_indx].length; j++) {
            if (Favorite_contacts[login_user_indx][j] == null) {
                Favorite_contacts[login_user_indx][j] = Phone_numbers[targetIndex];
                added = true;
                break;
            }
        }

        if (added) {
            System.out.println(User_names[targetIndex] + " added to your favorites successfully!");
        } else {
            System.out.println("Your favorites list is full!");
        }
    }

    // ==================== 6. REMOVE FAVORITE (FIXED TOO!) ====================
    public static void removeFavorite(String[] Phone_numbers, String[] User_email_addresses, String[][] Favorite_contacts, String[] User_names) {
        System.out.println("\n--- Remove Favorite ---");
        System.out.print("Enter Email or Phone number of the favorite to remove: ");
        String target = input.next();
        int targetIndex = -1;

        // Check both Phone and Email
        for (int i = 0; i < Phone_numbers.length; i++) {
            if ((Phone_numbers[i] != null && target.equals(Phone_numbers[i])) ||
                    (User_email_addresses[i] != null && target.equalsIgnoreCase(User_email_addresses[i]))) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            System.out.println("User not found!");
            return;
        }

        int removeIndex = -1;
        // Added "!= null" check here too so removeFavorite doesn't crash!
        for (int j = 0; j < Favorite_contacts[login_user_indx].length; j++) {
            if (Favorite_contacts[login_user_indx][j] != null && Favorite_contacts[login_user_indx][j].equals(Phone_numbers[targetIndex])) {
                removeIndex = j;
                break;
            }
        }

        if (removeIndex == -1) {
            System.out.println("This user is not in your favorites list.");
            return;
        }

        Favorite_contacts[login_user_indx][removeIndex] = null;
        // Shift remaining favorites left so there are no empty gaps in the middle
        for (int j = removeIndex; j < Favorite_contacts[login_user_indx].length - 1; j++) {
            Favorite_contacts[login_user_indx][j] = Favorite_contacts[login_user_indx][j + 1];
        }
        Favorite_contacts[login_user_indx][Favorite_contacts[login_user_indx].length - 1] = null;

        System.out.println(User_names[targetIndex] + " has been removed from your favorites.");
    }

    // ==================== 9. UPDATE PROFILE ====================
    public static void updateProfile(int[]User_IDs,String[] User_names, String[] User_email_addresses, String[] Phone_numbers, String[] User_passwords) {
        System.out.println("\n--- Update Profile ---");
        System.out.println("1. Update Name");
        System.out.println("2. Update Email address");
        System.out.println("3. Update Phone number");
        System.out.println("4. Update Password");
        System.out.print("Choose an option: ");
        String choice = input.next();
        switch (choice) {
            case "1":
                System.out.print("Enter new Name: ");
                String newName = input.next();
                int newName_found=searchUser(newName,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                if(newName_found==-1) {
                if (newName.length() >= 3) {
                    User_names[login_user_indx] = newName;
                    System.out.println("Name updated successfully!");
                } else
                    System.out.println("Invalid Name! Must be at least 3 characters.");
                }else System.out.println("This name is already token!");
                break;
            case "2":
                System.out.print("Enter new Email address: ");
                String newEmail = input.next();
                int newEmail_found=searchUser(newEmail,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                if(newEmail_found==-1) {
                    if (newEmail.contains("@") && newEmail.contains(".")) {
                        User_email_addresses[login_user_indx] = newEmail;
                        System.out.println("Email address updated successfully!");
                    } else
                        System.out.println("Invalid Email format!");
                }else System.out.println("This email already token ! ");
                break;

            case "3":
                System.out.print("Enter new Phone number: ");
                String newPhone = input.next();
                int newPhone_found=searchUser(newPhone,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                if(newPhone_found==-1) {
                    if (newPhone.length() == 11) {
                        Phone_numbers[login_user_indx] = newPhone;
                        System.out.println("Phone number updated successfully!");
                    } else
                        System.out.println("Invalid Phone number! Must be 11 digits.");
                }else System.out.println("this number already token !");
                break;

            case "4":
                System.out.print("Enter new Password: ");
                String newPassword = input.next();
                int newPassword_found=searchUser(newPassword,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                if(newPassword_found==-1) {
                    if (newPassword.length() >= 4) {
                        User_passwords[login_user_indx] = newPassword;
                        System.out.println("Password updated successfully!");
                    } else
                        System.out.println("Invalid Password! Must be at least 4 characters.");
                }else System.out.println("This Password is week! ");
                break;

            default:
                System.out.println("invalid operation number !");
                break;
        }
    }

    // ==================== 8. VIEW PROFILE ====================
    public static void viewProfile(int[] User_IDs, String[] User_names, String[] User_email_addresses, String[] Phone_numbers, long[] Balances, String[] Account_statuses) {

        System.out.println("\n--- My Profile ---");
        System.out.println("User ID        : " + User_IDs[login_user_indx]);
        System.out.println("Name           : " + User_names[login_user_indx]);
        System.out.println("Email address  : " + User_email_addresses[login_user_indx]);
        System.out.println("Phone number   : " + Phone_numbers[login_user_indx]);
        System.out.println("Balance        : " + Balances[login_user_indx] + " EGP");
        System.out.println("Account Status : " + Account_statuses[login_user_indx]);
    }

    public static void viewAllTransactions(String[] Transaction_IDs, String[] Transaction_senders, String[] Transaction_receivers, long[] Transaction_amounts, String[] Transaction_statuses) {
        boolean found = false;
        for (int i = 0; i < Transaction_IDs.length; i++) {
            if (Transaction_IDs[i] != null) {
                found = true;
                System.out.println("\n========== Transaction ==========");
                System.out.println("Transaction ID: " + Transaction_IDs[i]);
                System.out.println("Sender: " + Transaction_senders[i]);
                System.out.println("Receiver: " + Transaction_receivers[i]);
                System.out.println("Amount: " + Transaction_amounts[i]);
                System.out.println("Status: " + Transaction_statuses[i]);
            }
        }
        if (!found) {
            System.out.println("No transactions found!");
        }
    }

    public static void showBalance(long[] Balances) {

        System.out.println("======== YOUR BALANCE ========");
        System.out.println("Current Balance: " + Balances[login_user_indx] + " EGP");
    }

    public static void updateUser(int[] User_IDs, String[] User_names, String[] User_email_addresses, String[] Phone_numbers, String[] User_passwords) {
        System.out.println("Enter user ID to update: ");
        int update_User_Id = input.nextInt();
        int update_index = -1;
        for (int i = 0; i < User_IDs.length; i++) {
            if (User_IDs[i] != 0 && update_User_Id == User_IDs[i]) {
                update_index = i;
                break;
            }
        }
        if (update_index != -1) {
            System.out.println("\n--- Update User---");
            System.out.println("1. Update Name only");
            System.out.println("2. Update Email address only");
            System.out.println("3. Update Phone number only");
            System.out.println("4. Update ID only");
            System.out.print("Choose what you want to update: ");

            String choice = input.next();
            switch (choice) {
                case "1":
                    System.out.print("Enter new Name: ");
                    String newName = input.next();
                    int newName_found=searchUser(newName,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                    if(newName_found==-1) {
                        if (newName.length() >= 3) {
                            User_names[update_index] = newName;
                            System.out.println("Name updated successfully!");
                        } else
                            System.out.println("Invalid Name! Must be at least 3 characters.");
                    }else System.out.println("This name is already token!");
                    break;

                case "2":
                    System.out.print("Enter new Email address: ");
                    String newEmail = input.next();
                    int newEmail_found=searchUser(newEmail,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                    if(newEmail_found==-1) {
                        if (newEmail.contains("@") && newEmail.contains(".")) {
                            User_email_addresses[update_index] = newEmail;
                            System.out.println("Email address updated successfully!");
                        } else
                            System.out.println("Invalid Email format!");
                    }else System.out.println("This email already token ! ");
                    break;

                case "3":
                    System.out.print("Enter new Phone number: ");
                    String newPhone = input.next();
                    int newPhone_found=searchUser(newPhone,  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                    if(newPhone_found==-1) {
                        if (newPhone.length() == 11) {
                            Phone_numbers[update_index] = newPhone;
                            System.out.println("Phone number updated successfully!");
                        } else
                            System.out.println("Invalid Phone number! Must be 11 digits.");
                    }else System.out.println("this number already token !");
                    break;

                case "4":
                    System.out.print("Enter new ID: ");
                    int newid= input.nextInt();
                    int newid_found=searchUser(String.valueOf(newid),  User_IDs,  User_names, User_email_addresses,  Phone_numbers);
                    if(newid_found==-1) {
                        User_IDs[update_index] = newid;
                        System.out.println("ID updated successfully!");
                    }else System.out.println("This ID is already token! ");
                    break;

                default:
                    System.out.println("Invalid operation number!");
                    break;
            }
        } else System.out.println("User not found! ");
    }

    public static void blockAccount(int[] User_IDs, String[] User_names, String[] Phone_numbers, String[] User_email_addresses, String[] Account_statuses) {
        System.out.print("Enter User ID, Name, Phone, or Email to block: ");
        String target = input.next();
        int index = searchUser(target, User_IDs, User_names, User_email_addresses, Phone_numbers);

        if (index != -1 && Account_statuses[index] != null) {
            if (Account_statuses[index].equalsIgnoreCase("block")) {
                System.out.println("Account status: Block. This account is already blocked!");
            } else {
                Account_statuses[index] = "Block";
                System.out.println("Account status changed to: Block. User " + User_names[index] + " has been blocked successfully.");
            }
        } else {
            System.out.println("User not found in the system!");
        }
    }

    public static void unblockAccount(int[] User_IDs, String[] User_names, String[] Phone_numbers, String[] User_email_addresses, String[] Account_statuses) {
        System.out.print("Enter User ID, Name, Phone, or Email to unblock: ");
        String target = input.next();
        int index = searchUser(target, User_IDs, User_names, User_email_addresses, Phone_numbers);

        if (index != -1 && Account_statuses[index] != null) {
            if (Account_statuses[index].equalsIgnoreCase("active")) {
                System.out.println("Account status: Active. This account is already active!");
            } else {
                Account_statuses[index] = "Active";
                System.out.println("Account status changed to: Active. User " + User_names[index] + " has been unblocked successfully.");
            }
        } else {
            System.out.println("User not found in the system!");
        }
    }

    public static void searchRecipient(int[] User_IDs, String[] User_names, String[] User_email_addresses, String[] Phone_numbers, String[] Account_statuses) {
        System.out.print("Enter email or phone to search: ");
        String search = input.next();
        int found = searchUser(search, User_IDs, User_names, User_email_addresses, Phone_numbers);
        if (found == -1) {
            System.out.println("User not found.");
        } else {
            System.out.println("\n[Recipient Found]");
            System.out.println("Name: " + User_names[found]);
            System.out.println("Email: " + User_email_addresses[found]);
            System.out.println("Phone: " + Phone_numbers[found]);
            if (Account_statuses[found] .equalsIgnoreCase("Active") ) {
                System.out.println("Account: Active");
            } else {
                System.out.println("Account: Not active");
            }
            if (found == login_user_indx) {
                System.out.println("(Note: this is your own account)");
            }
        }
    }


    public static void showHistory(String[] User_email_addresses, String[] Transaction_IDs, String[] Transaction_senders, String[] Transaction_receivers, long[] Transaction_amounts, String[] Transaction_statuses) {
        System.out.println("\n--- Your Transaction History ---");
        int shown = 0;
        if (Transaction_IDs != null) {
            for (int i = 0; i < Transaction_IDs.length; i++) {
                if (Transaction_IDs[i] != null) {
                    boolean iSent = Transaction_senders[i].equals(User_email_addresses[login_user_indx]);
                    boolean iReceived = Transaction_receivers[i].equals(User_email_addresses[login_user_indx]) && Transaction_statuses[i].equals("SUCCESS");

                    if (iSent) {
                        shown++;
                        System.out.println(shown + ". SENT     | To: " + Transaction_receivers[i] + " | Amount: " + Transaction_amounts[i] + " EGP | Status: " + Transaction_statuses[i]);
                    } else if (iReceived) {
                        shown++;
                        System.out.println(shown + ". RECEIVED | From: " + Transaction_senders[i] + " | Amount: " + Transaction_amounts[i] + " EGP | Status: " + Transaction_statuses[i]);
                    }
                }
            }
        }
        if (shown == 0) {
            System.out.println("No transactions found.");
        }
    }


    //Save a transaction in the arrays
    static int Transaction_ID = 0;

    public static void saveTransaction(int to, long amount, String status, String[] User_email_addresses, String[] Transaction_IDs, String[] Transaction_senders, String[] Transaction_receivers, long[] Transaction_amounts, String[] Transaction_statuses) {

        if (Transaction_ID < Transaction_IDs.length) {
            Transaction_senders[Transaction_ID] = User_email_addresses[login_user_indx];
            Transaction_receivers[Transaction_ID] = User_email_addresses[to];
            Transaction_amounts[Transaction_ID] = amount;
            Transaction_statuses[Transaction_ID] = status;
            Transaction_IDs[Transaction_ID] = String.valueOf(Transaction_ID);
            Transaction_ID++;
        }
    }

    //  Check the transfer, returns "" if everything is OK
    public static String checkTransfer(int to, long amount, String[] Account_statuses, long[] Balances) {
        if (to == login_user_indx) {
            return "You cannot transfer money to yourself.";
        } else if (Account_statuses[login_user_indx].equalsIgnoreCase("block")) {
            return "Your account is not active.";
        } else if (Account_statuses[to].equalsIgnoreCase("block")) {
            return "Recipient's account is not active.";
        } else if (amount <= 0) {
            return "Amount must be greater than zero.";
        } else if (amount > Balances[login_user_indx]) {
            return "Insufficient balance! Your balance is: " + Balances[login_user_indx] + " EGP";
        }
        return "";
    }

    // 2. Transfer
    public static void transferMoney(int[] User_IDs, String[] Transaction_IDs, String[] Account_statuses, String[] Transaction_senders, String[] Transaction_receivers, long[] Transaction_amounts, String[] Transaction_statuses, String[] User_names, String[] User_email_addresses, String[] Phone_numbers, long[] Balances) {
        System.out.println("\n--- Transfer Money ---");
        System.out.print("Enter recipient's Email or Phone: ");
        String search = input.next();
        int to = searchUser(search, User_IDs, User_names, User_email_addresses, Phone_numbers);
        if (to == -1) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Recipient: " + User_names[to]);
        System.out.print("Enter amount to transfer (EGP): ");
        long amount = input.nextLong();
        String error = checkTransfer(to, amount, Account_statuses, Balances);
        if (!error.equals("")) {
            saveTransaction(to, amount, "FAILED", User_email_addresses, Transaction_IDs, Transaction_senders, Transaction_receivers, Transaction_amounts, Transaction_statuses);
            System.out.println("[FAILED Error] " + error);
        } else {
            Balances[login_user_indx] -= amount;
            Balances[to] += amount;
            saveTransaction(to, amount, "SUCCESS", User_email_addresses, Transaction_IDs, Transaction_senders, Transaction_receivers, Transaction_amounts, Transaction_statuses);
            System.out.println("[Success] Transfer completed successfully!");
            System.out.println("Your new balance: " + Balances[login_user_indx] + " EGP");

        }
    }





    public static void main(String[] args) {
        String Admin_email = "Admin404@gmail.com";
        String Admin_password = "Admin15_19";
        int[] User_IDs = new int[100];
        User_IDs[0] = 5010;
        User_IDs[1] = 5020;
        User_IDs[2] = 5030;
        User_IDs[3] = 5040;
        User_IDs[4] = 5050;

        String[] User_names = new String[100];
        User_names[0] = "Ahmed";
        User_names[1] = "Mohammed";
        User_names[2] = "Ali";
        User_names[3] = "Marawan";
        User_names[4] = "youssef";

        String[] User_email_addresses = new String[100];
        User_email_addresses[0] = "ahmed@gmail.com";
        User_email_addresses[1] = "mahammed@gmail.com";
        User_email_addresses[2] = "ali@gmail.com";
        User_email_addresses[3] = "marawan@gmail.com";
        User_email_addresses[4] = "youssef@gmail.com";

        String[] Phone_numbers = new String[100];
        Phone_numbers[0] = "01134567891";
        Phone_numbers[1] = "01198765431";
        Phone_numbers[2] = "01274638190";
        Phone_numbers[3] = "01239748290";
        Phone_numbers[4] = "01153784952";

        String[] User_passwords = new String[100];
        User_passwords[0] = "Ahmed10";
        User_passwords[1] = "Mohammed20";
        User_passwords[2] = "Ali30";
        User_passwords[3] = "Marawan50";
        User_passwords[4] = "Youssef10";

        long[] Balances = new long[100];
        Balances[0] = 5000;
        Balances[1] = 2300;
        Balances[2] = 2000;
        Balances[3] = 3500;
        Balances[4] = 1800;

        String[] Account_statuses = new String[100];
        Account_statuses[0] = "Active";
        Account_statuses[1] = "Block";
        Account_statuses[2] = "Active";
        Account_statuses[3] = "Active";
        Account_statuses[4] = "Active";

        String[][] Favorite_contacts = new String[User_IDs.length][User_IDs.length - 1];
        Favorite_contacts[0][0] = "01198765431";
        String[]Transaction_IDs = new String[10000];
        String[] Transaction_senders = new String[10000];
        String[]Transaction_receivers = new String[10000];
        long[] Transaction_amounts = new long[10000];
        String[] Transaction_statuses = new String[10000];
        boolean exit = false;
        while (!exit) {
            System.out.println("========== QUICK PAY ==========");
            System.out.print("1. Login as Admin\n2. Login as User\n3. Exit\nChoose an option: ");
            String main_menu = input.next();
            switch (main_menu) {
                case "1":
                    int retries = 0;
                    boolean login_Admin = login_admin(Admin_email, Admin_password);
                    while (!login_Admin && retries < 3) {
                        System.out.println("Invalid credentials. Try again.");
                        login_Admin = login_admin(Admin_email, Admin_password);
                        retries++;
                    }
                    if (!login_Admin) {
                        System.out.println("Too many failed attempts. Returning to main menu.");
                        break;
                    }
                    String Admin_operation = "9";
                    do {
                        System.out.print("========== ADMIN PANEL ==========\n1. Show Statistics\n2. Search User\n3. View All Users\n4. Update User\n5. View All Transactions\n6. Block Account\n7. Unblock Account\n8. View User Details\n9. Logout\nChoose an option: ");

                        Admin_operation = input.next();

                        switch (Admin_operation) {
                            case "1":
                                showStatistics(User_names, Account_statuses, Transaction_statuses, Transaction_amounts);
                                break;
                            case "2":
                                System.out.print("Search for a user using one of the following(User ID or Name or Phone number or Email address) : ");
                                String Search_User = input.next();
                                int search_index = searchUser(Search_User, User_IDs, User_names, User_email_addresses, Phone_numbers);
                                if (search_index != -1) {
                                    System.out.print("User ID: " + User_IDs[search_index] + "\nName: " + User_names[search_index] + "\nPhone number: " + Phone_numbers[search_index] + "\nEmail address: " + User_email_addresses[search_index] + "\nAccount Statuses: " + Account_statuses[search_index]);

                                } else System.out.println("This User Not found !");
                                break;
                            case "3":
                                viewAllUsers(User_IDs, User_names, User_email_addresses, Phone_numbers, Balances, Account_statuses);
                                break;
                            case "4":
                                updateUser(User_IDs, User_names, User_email_addresses, Phone_numbers, User_passwords);
                                break;
                            case "5":
                                viewAllTransactions(Transaction_IDs, Transaction_senders, Transaction_receivers, Transaction_amounts, Transaction_statuses);
                                break;
                            case "6":
                                blockAccount(User_IDs, User_names, Phone_numbers, User_email_addresses, Account_statuses);
                                break;
                            case "7":
                                unblockAccount(User_IDs, User_names, Phone_numbers, User_email_addresses, Account_statuses);
                                break;
                            case "8":
                                System.out.print("View details of a user using one of the following (User ID or Name or Phone number or Email address) : ");
                                String specific_user = input.next();
                                int search_details_index = viewUserDetails(specific_user, User_IDs, User_names, User_email_addresses, Phone_numbers);
                                if (search_details_index != -1) {
                                    System.out.println("User ID: " + User_IDs[search_details_index] + "\nName: " + User_names[search_details_index] + "\nPhone number: " + Phone_numbers[search_details_index] + "\nEmail address: " + User_email_addresses[search_details_index] + "\nUser Balance: " + Balances[search_details_index] + "\nAccount Statuses: " + Account_statuses[search_details_index]);
                                    boolean hasTx = false;
                                    for (int i = 0; i < Transaction_IDs.length; i++) {
                                        if (Transaction_IDs[i] != null) {
                                            boolean i_Sent = Transaction_senders[i].equals(User_email_addresses[search_details_index]);
                                            boolean i_Received = Transaction_receivers[i].equals(User_email_addresses[search_details_index]) && Transaction_statuses[i].equals("SUCCESS");

                                            if (i_Sent) {
                                                hasTx= true;
                                                System.out.println("SENT     | To: " + Transaction_receivers[i] + " | Amount: " + Transaction_amounts[i] + " EGP | Status: " + Transaction_statuses[i]);
                                            } else if (i_Received) {
                                                hasTx= true;
                                                System.out.println("RECEIVED | From: " + Transaction_senders[i] + " | Amount: " + Transaction_amounts[i] + " EGP | Status: " + Transaction_statuses[i]);
                                            }
                                        }
                                    }
                                    if (!hasTx) {
                                        System.out.println("No transactions found.");
                                    }

                                } else System.out.println("This User Not found !");
                                break;
                            case "9":
                                //log out;
                                break;
                            default:
                                System.out.println("invalid operation number !");
                                break;
                        }
                    } while (!(Admin_operation.equals("9")));
                    break;
                case "2":
                    int userRetries = 0;
                    boolean login = login_user(Phone_numbers, User_passwords);
                    while (!login && userRetries < 3) {
                        System.out.println("Invalid login credentials. Please try again.");
                        login = login_user(Phone_numbers, User_passwords);
                        userRetries++;
                    }
                    if (!login) {
                        System.out.println("Login failed. Returning to main menu.");
                        break;
                    }
                    String User_operation = "10";
                    do {
                        System.out.print("========== USER PANEL ==========\n1. Show Balance\n2. Transfer Money\n3. Transaction History\n4. Show Favorites\n5. Add Favorite\n6. Remove Favorite\n7. Search Recipient\n8. View Profile\n9. Update Profile\n10. Logout\nChoose an option: ");
                        User_operation = input.next();

                        switch (User_operation) {
                            case "1":
                                showBalance(Balances);
                                break;
                            case "2":
                                transferMoney(User_IDs, Transaction_IDs, Account_statuses, Transaction_senders, Transaction_receivers, Transaction_amounts, Transaction_statuses, User_names, User_email_addresses, Phone_numbers, Balances);
                                break;
                            case "3":
                                showHistory(User_email_addresses, Transaction_IDs, Transaction_senders, Transaction_receivers, Transaction_amounts, Transaction_statuses);
                                break;
                            case "4":
                                showFavorites(Favorite_contacts, User_IDs, Phone_numbers, User_email_addresses, User_names);
                                break;
                            case "5":
                                addFavorite(Phone_numbers,User_email_addresses, Favorite_contacts, User_names);
                                break;
                            case "6":
                                removeFavorite(Phone_numbers,User_email_addresses, Favorite_contacts, User_names);
                                break;
                            case "7":
                                searchRecipient(User_IDs, User_names, User_email_addresses, Phone_numbers, Account_statuses);
                                break;
                            case "8":
                                viewProfile(User_IDs, User_names, User_email_addresses, Phone_numbers, Balances, Account_statuses);
                                break;
                            case "9":
                                updateProfile(User_IDs,User_names, User_email_addresses, Phone_numbers, User_passwords);
                                break;
                            case "10":
                                //log out;
                                break;
                            default:
                                System.out.println("invalid operation number !");
                                break;
                        }
                    } while (!(User_operation.equals("10")));
                    break;
                case "3":
                    exit = true;
                    return;
                default:
                    System.out.println("invalid operation number !");
            }
        }
    }
}
