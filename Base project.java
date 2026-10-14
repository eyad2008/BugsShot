package com.mycompany.main;
import java.util.*;
public class Main {
    public static boolean login_user(String [] Phone_numbers,String [] User_passwords,boolean chek_user_phone, boolean chek_user_password){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your Phone Number: ");
        String login_user_phone =input.next();
        System.out.print("Enter your password: ");
        String login_user_password =input.next();
        int login_user_indx =-1;
        for(int i=0;i<Phone_numbers.length;i++){
            if(login_user_phone.equals(Phone_numbers[i])) {
                login_user_indx = i;
                chek_user_phone =true;
            }
        }
        if(chek_user_phone == false) {
            if (login_user_phone.length() != 11)
                System.out.println("Invalid phone number length and its length is: " + login_user_phone.length());
            else System.out.println("this phone number not found !");
        }if(login_user_indx!=-1){
            if(login_user_password.equals(User_passwords[login_user_indx]))
                chek_user_password = true;
            else System.out.println("the password is incorrect");
        }
        if (chek_user_password==false||chek_user_phone==false)
            return false;
        else return true;

    }
    public static boolean login_admin(String Admin_email , String Admin_password,boolean chek_admin_email, boolean chek_admin_password){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your Email : ");
        String login_Admin_Email =input.next();
        System.out.print("Enter your password: ");
        String login_Admin_password =input.next();
        if(login_Admin_Email.equals(Admin_email))
                chek_admin_email =true;
        else{
            System.out.println("this Email not found !");
        }if(login_Admin_password.equals(Admin_password))
            chek_admin_password = true;
        else System.out.println("the password is incorrect");
        if(chek_admin_password==false||chek_admin_email==false)
            return false;
        else return true;
    }



    public static void showStatistics(String [] User_names,String [] Account_statuses,String[][] Transaction_statuses,long[][] Transaction_amounts){
        int Active_Users = 0;
        int  blocked_accounts = 0;
        for(int i=0;i<Account_statuses.length;i++){
            if(Account_statuses[i].equalsIgnoreCase("active"))
                Active_Users++;
            else if (Account_statuses[i].equalsIgnoreCase("block"))
                blocked_accounts++;
        }
        int successful_transactions = 0;
        int failed_transactions = 0;
        long  amountofsuccessfultransfers = 0;
        if(Transaction_statuses!=null&&Transaction_amounts!=null) {
            for (int j = 0; j < Transaction_statuses.length; j++)
                for (int i = 0; i < Transaction_statuses[j].length; i++) {
                    if (Transaction_statuses[j][i].equalsIgnoreCase("successful")) {
                        successful_transactions++;
                        amountofsuccessfultransfers += Transaction_amounts[j][i];
                    } else if (Transaction_statuses[j][i].equalsIgnoreCase("faild"))
                        failed_transactions++;
                }
        }
        System.out.println("Total number of registered users: "+User_names.length+"\nNumber of active users: "+Active_Users+"\nNumber of blocked accounts: "+blocked_accounts+"\nTotal number of successful transactions: "+successful_transactions+"\nTotal number of failed transactions: "+failed_transactions+"\nTotal amount of successful transfers: "+amountofsuccessfultransfers);
    }
    public static int searchUser(String Search_User,int [] User_IDs,String [] User_names,String [] User_email_addresses,String [] Phone_numbers){
        int search_index = -1 ;
        for(int i =0;i<User_IDs.length;i++){
            if(Search_User.equals(User_IDs[i])||Search_User.equalsIgnoreCase(User_names[i])||Search_User.equals(User_email_addresses[i])||Search_User.equals(Phone_numbers[i])){
                search_index=i;
            }
        }
        return search_index;
    }
    public static void viewAllUsers(int [] User_IDs,String [] User_names,String [] User_email_addresses,String [] Phone_numbers,long [] Balances,String [] Account_statuses){
        for(int i=0;i< User_IDs.length;i++){
            System.out.println("User: "+(1+i)+"\nUser ID: " + User_IDs[i] + "\nName: " + User_names[i] + "\nPhone number: " + Phone_numbers[i] + "\nEmail address: " + User_email_addresses[i] + "\nUser Balance: " + Balances[i] + "\nAccount Statuses: " + Account_statuses[i]);
        }
    }
    public static int viewUserDetails (String specific_user,int [] User_IDs,String [] User_names,String [] User_email_addresses,String [] Phone_numbers){
        int search_index = -1 ;
        for(int i =0;i<User_IDs.length;i++){
            if(specific_user.equals(User_IDs[i])||specific_user.equalsIgnoreCase(User_names[i])||specific_user.equals(User_email_addresses[i])||specific_user.equals(Phone_numbers[i])){
                search_index=i;
            }
        }
        return search_index ;

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String Admin_email = "Admin404@gmail.com" ;
        String Admin_password = "Admin15_19" ;
        int [] User_IDs = {5010,5020,5030,5040,5050,5060,5070,5080,5090,6010};
        String [] User_names = {"Ahmed","Mohammed","Ali","Malak","Marawan","Yasmeen","Eyad","Marym","Hana","youssef"};
        String [] User_email_addresses = {"ahmed@gmail.com","mahammed@gmail.com","ali@gmail.com","malak@gmail.com","marawan@gmail.com","yasmeen@gmail.com","eyad@gmail.com","marym@gmail.com","hana@gmail.com","youssef@gmail.com"};
        String [] Phone_numbers = {"01134567891","01198765431","01198654732","01153784952","01137895478","01274638190","01289736489","01276483286","01239748290","01244472983"};
        String [] User_passwords = {"Ahmed10","Mohammed20","Ali30","Malak40","Marawan50","Yasmeen60","Eyad70","Marym80","Hana90","Youssef10"};
        long [] Balances ={5000,1500,2000,400,6500,2300,4050,3500,10000,1800};
        String [] Account_statuses = {"Active","Block","Active","Active","Active","Active","Active","Active","Block","Active"};
        String [][] Favorite_contacts = new String[User_IDs.length][User_IDs.length-1];
        Favorite_contacts[0][0]="01198765431";
        //ArrayList<datatype>[] ArrayName = new ArrayList[outerSize];
        //Array List to be unlimited size, cuse we don't know many transaction will the user do.
        String [][] Transaction_IDs = new String[User_IDs.length][10000];
        String [][] Transaction_senders = new String[User_IDs.length][10000];
        String [][] Transaction_receivers = new String[User_IDs.length][10000];
        long [][] Transaction_amounts = new long[User_IDs.length][10000];
        String [][] Transaction_statuses = new String[User_IDs.length][10000];
        System.out.println("========== QUICK PAY ==========");
        System.out.print("1. Login as Admin\n2. Login as User\n3. Exit\nChoose an option: ");
        int main_menu = input.nextInt();
        switch (main_menu) {
            case 1:
                boolean chek_admin_email = false;
                boolean chek_admin_password = false;
                boolean login_Amin =login_admin( Admin_email ,  Admin_password, chek_admin_email,chek_admin_password);
                if(login_Amin==false){
                    login_admin( Admin_email ,  Admin_password, chek_admin_email,chek_admin_password);
                }
                System.out.print("========== ADMIN PANEL ==========\n1. Show Statistics\n2. Search User\n3. View All Users\n4. Update User\n5. View All Transactions\n6. Block Account\n7. Unblock Account\n8. View User Details\n9. Logout\nChoose an option: ");
                int Admin_operation = input.nextInt();
                switch (Admin_operation){
                    case 1:
                        showStatistics(User_names,Account_statuses,Transaction_statuses,Transaction_amounts);
                        break;
                    case 2:
                        System.out.print("Search for a user using one of the following(User ID or Name or Phone number or Email address) : ");
                        String Search_User = input.next();
                        int search_index = searchUser(Search_User,User_IDs,User_names,User_email_addresses,Phone_numbers);
                        if (search_index != -1) {
                            System.out.print("User ID: " + User_IDs[search_index] + "\nName: " + User_names[search_index] + "\nPhone number: " + Phone_numbers[search_index] + "\nEmail address: " + User_email_addresses[search_index] + "\nAccount Statuses: " + Account_statuses[search_index] );

                        }else System.out.println("This User Not found !");
                        break;
                    case 3:
                        viewAllUsers(User_IDs,User_names,User_email_addresses,Phone_numbers,Balances,Account_statuses);
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
                        System.out.print("View details of a user using one of the following (User ID or Name or Phone number or Email address) : ");
                        String specific_user = input.next();
                        int search_details_index = viewUserDetails (specific_user,User_IDs,User_names,User_email_addresses,Phone_numbers);
                        if (search_details_index != -1) {
                            long total_transactin_amount = 0;
                            for (int i = 0; i < Transaction_amounts.length; i++) {
                                    total_transactin_amount += Transaction_amounts[search_details_index][i];
                            }
                            System.out.print("User ID: " + User_IDs[search_details_index] + "\nName: " + User_names[search_details_index] + "\nPhone number: " + Phone_numbers[search_details_index] + "\nEmail address: " + User_email_addresses[search_details_index] + "\nUser Balance: " + Balances[search_details_index] + "\nAccount Statuses: " + Account_statuses[search_details_index] + "\nTotal transaction amount: " + total_transactin_amount);
                            if (Transaction_amounts.length != 0) {
                                System.out.println("Transaction amounts: ");
                                for (int i = 0; i < Transaction_amounts.length; i++) {
                                    System.out.println(Transaction_amounts[search_details_index][i]+ " , ");
                                }
                            }
                        }else System.out.println("This User Not found !");
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
                boolean chek_user_phone = false;
                boolean chek_user_password = false;
                boolean login = login_user(Phone_numbers,User_passwords,chek_user_phone,chek_user_password);
                if(login==false){
                    login_user(Phone_numbers,User_passwords,chek_user_phone,chek_user_password);
                }
                System.out.print("========== USER PANEL ==========\n1. Show Balance\n2. Transfer Money\n3. Transaction History\n4. Show Favorites\n5. Add Favorite\n6. Remove Favorite\n7. Search Recipient\n8. View Profile\n9. Update Profile\n10. Logout\nChoose an option: ");
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
