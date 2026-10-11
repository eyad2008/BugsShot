package com.mycompany.main;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String [] User_IDs = {"5010","5020","5030","5040","5050","5060","5070","5080","5090","6010"};
        String [] User_names = {"Ahmed","Mohammed","Ali","Malak","Marawan","Yasmeen","Eyad","Marym","Hana","youssef"};
        String [] User_email_addresses = {"ahmed@gmail.com","mahammed@gmail.com","ali@gmail.com","malak@gmail.com","marawan@gmail.com","yasmeen@gmail.com","eyad@gmail.com","marym@gmail.com","hana@gmail.com","youssef@gmail.com"};
        String [] Phone_numbers = {"01134567891","01198765431","01198654732","01153784952","01137895478","01274638190","01289736489","01276483286","01239748290","01244472983"};
        String [] User_passwords = {"Ahmed10","Mohammed20","Ali30","Malak40","Marawan50","Yasmeen60","Eyad70","Marym80","Hana90","Youssef10"};
        long [] Balances ={5000,1500,2000,400,6500,2300,4050,3500,10000,1800};
        String [] Account_statuses = {"Active","Block","Active","Active","Active","Active","Active","Active","Block","Active"};
        String [][] Favorite_contacts = new String[10][10];
        Favorite_contacts[0][0]="01198765431";
        ArrayList<String> Transaction_IDs = new ArrayList<String>();
        ArrayList<String> Transaction_senders = new ArrayList<String>();
        ArrayList<String> Transaction_receivers = new ArrayList<String>();
        ArrayList<Long> Transaction_amounts = new ArrayList<Long>();
        ArrayList<String> Transaction_statuses = new ArrayList<String>();
        System.out.println("========== QUICK PAY ==========");
        System.out.println("1. Login as Admin\n2. Login as User\n3. Exit\nChoose an option: ");
        int main_menu = input.nextInt();
        switch (main_menu) {
            case 1:
                System.out.println("========== ADMIN PANEL ==========\n1. Show Statistics\n2. Search User\n3. View All Users\n4. Update User\n5. View All Transactions\n6. Block Account\n7. Unblock Account\n8. View User Details\n9. Logout\nChoose an option: ");
                int Admin_operation = input.nextInt();
                switch (Admin_operation){
                    case 1:
                        //Show Statistics;
                        break;
                    case 2:
                        //Search User;
                        break;
                    case 3:
                        //View All Users;
                        break;
                    case 4:
                        //Update User:
                        break;
                    case 5:
                        //View All Transactions;
                        break;
                    case 6:
                        //Block Account;
                        break;
                    case 7:
                        //Unblock Account;
                        break;
                    case 8:
                        //View User Details;
                        break;
                    case 9:
                        //Logout;
                        break;
                    default:
                        System.out.println("invalid operation number !");
                        break;
                }
                break;
            case 2 :
                System.out.println("========== USER PANEL ==========\n1. Show Balance\n2. Transfer Money\n3. Transaction History\n4. Show Favorites\n5. Add Favorite\n6. Remove Favorite\n7. Search Recipient\n8. View Profile\n9. Update Profile\n10. Logout\nChoose an option: ");
                int User_operation = input.nextInt();
                switch (User_operation){
                    case 1:
                        //Show Balance;
                        break;
                    case 2:
                        //Transfer Money;
                        break;
                    case 3:
                        //Transaction History;
                        break;
                    case 4:
                        //Show Favorites:
                        break;
                    case 5:
                        //Add Favorite;
                        break;
                    case 6:
                        //Remove Favorite;
                        break;
                    case 7:
                        //Search Recipient;
                        break;
                    case 8:
                        //View Profile;
                        break;
                    case 9:
                        //Update Profile;
                        break;
                    case 10:
                        //Logout;
                        break;
                    default:
                        System.out.println("invalid operation number !");
                        break;
                }
                break;
            case 3:
                return;
            default:
                System.out.println("invalid operation number !");
        }





    }
}