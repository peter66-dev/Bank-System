/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import validation.Check;

/**
 *
 * @author Dell
 */
public class User implements Comparable<User> {

    public String id;
    public String customer;
    public String account;
    public String password;
    public double money;
    public ArrayList<String> activity;

    public User() {
        id = "";
        customer = "";
        account = "";
        password = "";
        money = 0;
        activity = new ArrayList<>();
    }

    public User(String id, String customer, String account,
            String password, double money) {
        this.id = id;
        this.customer = customer;
        this.account = account;
        this.password = password;
        this.money = money;
    }

    public User(String id, String customer, String account,
            String password, double money, ArrayList<String> activity) {
        this.id = id;
        this.customer = customer;
        this.account = account;
        this.password = password;
        this.money = money;
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public ArrayList<String> getActivity() {
        return activity;
    }

    public void setActivity(ArrayList<String> activity) {
        this.activity = activity;
    }

    public boolean setMoney(double money) {
        if (money >= 0) {
            this.money = money;
            return true;
        }
        return false;
    }

    public void input() {
        id = Check.getAnID("Enter ID: ", "ID has only 4 number, please !!!");
        customer = Check.getAName("Enter your name: ", "Name has just alphabet, please !!!");
        account = Check.getAnAccount("Enter account: ", "Username has 4-15 characters (number and alphabet), please !!!");
        password = Check.getAPassword("Enter password: ",
                "Password has 6-15 characters (number, alphabet and 1 special), please!!!");
    }

    public void output() {
        System.out.printf("|%-10s|%-20s|%-15s|%20.2f$|\n", id, customer, account, money);
    }

    @Override
    public String toString() {
        return id + ", " + customer + ", " + account + ", " + money + "$";
    }

    @Override
    public int compareTo(User o) {
        return this.id.compareToIgnoreCase(o.id);
    }

}
