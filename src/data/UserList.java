/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import dao.File_DAO;
import hidePassword.PasswordField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;
import validation.Check;

/**
 *
 * @author Dell
 */
public class UserList {

    public TreeSet<User> list;
//    Base64.Encoder enc = Base64.getEncoder();
//    Base64.Decoder dec = Base64.getDecoder();

    public UserList() {
        list = new TreeSet<>();
    }

    public UserList(TreeSet<User> list) {
        this.list = list;
    }

    public TreeSet<User> getList() {
        return list;
    }

    public void setList(TreeSet<User> list) {
        this.list = list;
    }

    public boolean checkIDExist(String id, TreeSet<User> list) {
        if (list.isEmpty()) {
            return false;
        } else {
            for (User u : list) {
                if (u.id.equalsIgnoreCase(id)) {
                    return true;
                }
            }
            return false;
        }
    }

    public User checkIDExist(String id) {
        if (list.isEmpty()) {
            return null;
        } else {
            for (User u : list) {
                if (u.id.equalsIgnoreCase(id)) {
                    return u;
                }
            }
            return null;
        }
    }

    public User createAccount() {
        Scanner scan = new Scanner(System.in);
        User u = new User();
        boolean check = true;
        do {
            u.id = Check.getAnID("Enter ID: ", "Account ID has only 4 number, please !!!");
            if (this.checkIDExist(u.id, list)) {
                System.out.println("This ID has existed !");
            } else {
                System.out.println("This ID is valid!");
                check = false;
            }
        } while (check);
        u.customer = Check.getAName("Enter your name: ", "Name has just alphabet, please !!!");
        u.account = Check.getAnAccount("Enter account: ", "Account has 4-15 characters (number and alphabet), please !!!");
        u.password = Check.getAPassword("Enter password: ", "Password has 6-15 characters (number, alphabet and 1 special), please!!!");
        scan = new Scanner(System.in);
        String answer = new String();
        while (true) {
            String confirmPassword = "";
            confirmPassword = Check.getAPassword("Confirm your password: ", "Password has 6-15 characters (number, alphabet and 1 special), please!!!");

            if (confirmPassword.equalsIgnoreCase(u.password)) {
                u.getActivity().add(u.getId() + ", " + this.getDate() + ", " + "Create account!");
                return u;
            } else {
                System.out.println("The two passwords must be the same !");
                answer = Check.getAnswer("Do you wanna continue to confirm password? <Yes/No>", "Password has 8-15 characters (number, alphabet and 1 special), please!!!");
                if (answer.equalsIgnoreCase("no")) {
                    return null;
                }
            }
        }
    }

    public User login() {
        Scanner scan = new Scanner(System.in);
        String accountLogin = Check.getAnAccount("Login account: ", "Account has just number and alphabet, please !!!");
        scan = new Scanner(System.in);
        String pswLogin = Check.getAPassword("Login password: ", "Password has just number, alphabet and 1 special character, please!!!");
        scan = new Scanner(System.in);
        for (User u : list) {
            if (u.account.equalsIgnoreCase(accountLogin) && u.password.equalsIgnoreCase(pswLogin)) {
                u.getActivity().add(u.getId() + ", " + this.getDate() + ", " + "Login account!");
                System.out.println();
                for (String s : u.getActivity()) {
                    System.out.println(s);
                }
                return u;
            }
        }
        return null;
    }

    public void withdrawMoney(User u) {
        System.out.println("======= Withdraw Money =======");
        double wdMoney = Check.getADouble("Enter amount of money you wanna withdraw: ", "Sorry, Your account balance is insufficient for this transaction! \nPlease, enter again amount of money you wanna withdraw: ", 0, u.money);
        if (u.setMoney(u.money - wdMoney)) {
            u.getActivity().add(u.getId() + ", " + this.getDate() + ", " + "Withdraw money!!");
            System.out.println("Withdraw successfully!");
            System.out.println(u.toString());
        }
    }

    public void depositMoney(User u) {
        System.out.println("======= Deposit Money =======");
        double dMoney = Check.getADouble("Enter amount of money you deposit: ", "Money is a positive double number, please !!!");
        if (u.setMoney(u.money + dMoney)) {
            u.getActivity().add(u.getId() + ", " + this.getDate() + ", " + "Deposit money!!");
            System.out.println("Deposit successfully!");
            System.out.println(u.toString());
        }
    }

    public void transferMoney(User u) {
        String recipientID = Check.getAnID("Enter ID you wanna send: ", "Account has just number and alphabet, please !!!");
        User transferUser = this.checkIDExist(recipientID);
        if (transferUser == null) {
            System.out.println("This user does not exist in list!");
        } else {
            if (transferUser == u) {
                System.out.println("Cannot deal with itself!");
            } else {
                double moneyTransfer = Check.getADouble("Enter amount of money you wanna transfer: ", "Sorry, Your account balance is insufficient for this transaction! ", 0, u.money);
                if (u.setMoney(u.money - moneyTransfer) && transferUser.setMoney(transferUser.money + moneyTransfer)) {
                    System.out.println(u.toString());
                    System.out.println(transferUser.toString());
                    u.getActivity().add(u.getId() + ", " + this.getDate() + ", " + "Transfer money!");
                    System.out.println("Transfering successfully!");
                }
            }
        }
    }

    public void printAll() {
        if (!list.isEmpty()) {
            System.out.println(" ID         User                 Account                         Money");
            for (User u : list) {
                u.output();
            }
        } else {
            System.out.println("Don't have any Account in list !");
        }
    }

    public void saveBank(String filename) {
        File_DAO.storeBank(filename, list);
        System.out.println("Saving Bank successfully!");
    }

    public void saveUser(String filename) {
        File_DAO.storeUser(filename, list);
        System.out.println("Saving User successfully!");
    }

    public void printAllActivity(ArrayList<String> acManagement) {
        if (acManagement.isEmpty()) {
            System.out.println("Don't have any activity in Bank");
        } else {
            for (String s : acManagement) {
                System.out.println(s);
            }
        }
    }

    public void changePassword(User u) {
        String secondPsw = PasswordField.readPassword("Enter password you wanna change: ");
        u.getActivity().add(u.getId() + ", " + this.getDate() + ", " + "Change password!");
        u.setPassword(secondPsw);
    }

    public String getDate() {
        Date d = new Date();
        return d.toString();
    }

    public void writeLog(String filename, ArrayList<String> acManagement) {
        if (acManagement.isEmpty()) {
            System.out.println("Don't have any activity to write");
        } else {
            File_DAO.storeActivities(filename, acManagement);
            for (String s : acManagement) {
                System.out.println(s);
            }
            System.out.println("Write log successfully !");
        }
    }

}
