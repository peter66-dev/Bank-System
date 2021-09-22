/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import data.User;
import data.UserList;
import java.util.ArrayList;
import validation.Check;
//C:\Users\Dell\Desktop\Chuyên Ngành 2\PRO192\Code\Secret Bank\build

/**
 *
 * @author Dell
 */
public class Program {

    public static void main(String[] args) {
        int choice;
        UserList obj = new UserList();
        ArrayList<String> acManagement = new ArrayList<>();

        do {
            System.out.println("********************* Menu **************************");
            Menu();
            choice = Check.getAnInteger("Your choice is: ", "Just 1-7 options, please !!!", 1, 7);
            switch (choice) {

                case 1: {
                    User u = obj.createAccount();
                    if (u != null) {
                        if (obj.list.add(u)) {
                            System.out.println("Creating successfully!");
                            acManagement.add(u.id + ", " + obj.getDate() + ", " + "Create account!");
                        } else {
                            System.out.println("Creating failed!");
                        }
                    } else {
                        System.out.println("Creating failed!");
                    }
                    break;
                }

                case 2: {
                    int choiceSubMenu;
                    if (!obj.getList().isEmpty()) {
                        User userLogin = obj.login();

                        if (userLogin != null) {
                            System.out.println("Login success !");
                            acManagement.add(userLogin.id + ", " + obj.getDate() + ", " + "Login account!");
                            subMenu2();
                            choiceSubMenu = Check.getAnInteger("Your choice is: ", "Just 1-5 options, please !!!", 1, 5);
                            switch (choiceSubMenu) {

                                case 1: {//rut
                                    obj.withdrawMoney(userLogin);
                                    acManagement.add(userLogin.id + ", " + obj.getDate() + ", " + "Withdraw money!");
                                    break;
                                }

                                case 2: {//gui
                                    obj.depositMoney(userLogin);
                                    acManagement.add(userLogin.id + ", " + obj.getDate() + ", " + "Deposit money!");
                                    break;
                                }

                                case 3: {//transfer
                                    acManagement.add(userLogin.id + ", " + obj.getDate() + ", " + "Transfer money!");
                                    obj.transferMoney(userLogin);
                                    break;
                                }

                                case 4: {//remove
                                    if (obj.getList().remove(userLogin)) {
                                        System.out.println("Removing successfully!");
                                        acManagement.add(userLogin.id + ", " + obj.getDate() + ", " + "Remove account!");
                                    } else {
                                        System.out.println("Removing failed!");
                                    }

                                    break;
                                }

                                case 5: {
                                    obj.changePassword(userLogin);
                                    acManagement.add(userLogin.id + ", " + obj.getDate() + ", " + "Change password!");
                                    break;
                                }

                            }
                        } else {
                            System.out.println("Login failed !");
                        }
                    } else {
                        System.out.println("Don't have any Account in list !");
                    }
                    break;
                }
                case 3: {//print all
                    System.out.println("======================== Print all ========================");
                    obj.printAll();
                    break;
                }

                case 4: {//store data
                    obj.saveBank("Bank.dat");
                    obj.saveUser("User.dat");
                    break;
                }

                case 5: {//ghi hết acti vô file, và đã có trong acManagement
                    //write file and output
                    obj.writeLog("log.dat", acManagement);
                    break;
                }

                case 6: {
                    obj.printAllActivity(acManagement);
                    break;
                }

                case 7: {//quit
                    System.out.println("Good bye !");
                    break;
                }
            }

        } while (choice != 7);
    }

    public static void Menu() {
        System.out.println("1. Create new account");
        System.out.println("2. Login");
        System.out.println("3. Print all Account in Bank");
        System.out.println("4. Store data");
        System.out.println("5. Write log");
        System.out.println("6. Print all activity in bank system");
        System.out.println("7. Quit");
    }

    public static void subMenu2() {
        System.out.println("1. Withdraw money");
        System.out.println("2. Deposit money");
        System.out.println("3. Transfer money");
        System.out.println("4. Remove account");
        System.out.println("5. Change password");
    }
}
