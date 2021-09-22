/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import data.User;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Dell
 */
public class File_DAO {

    public static void storeBank(String filename, TreeSet<User> list) {
        PrintWriter pw = null;//account & money
        try {
            pw = new PrintWriter(filename);
            for (User u : list) {
                pw.println(u.account + ", " + u.money);
                pw.flush();
            }
        } catch (Exception e) {
            System.out.println("Bug 1");
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                System.out.println("Bug 2");
            }
        }
    }

    public static void storeUser(String filename, TreeSet<User> list) {
        PrintWriter pw = null;//account & password
        try {
            pw = new PrintWriter(filename);
            for (User u : list) {
                pw.println(u.account + ", " + u.password);
                pw.flush();
            }
        } catch (Exception e) {
            System.out.println("Bug 1");
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                System.out.println("Bug 2");
            }
        }
    }

    public static void storeActivities(String filename, ArrayList<String> acManagement) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
            for (String s : acManagement) {
                pw.print(s);
                pw.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
