/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import hidePassword.PasswordField;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Check {

    static Scanner scan = new Scanner(System.in);

    public static int getAnInteger(String msg, String errorMsg, int min, int max) {
        int number;
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }

        while (true) {
            try {
                System.out.print(msg);
                number = Integer.parseInt(scan.nextLine());
                if (!(number >= min && number <= max)) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getAName(String msg, String errorMsg) {
        String name = new String();
        String pattern = "[a-zA-Z].+";
        while (true) {
            try {
                System.out.print(msg);
                name = scan.nextLine();
                if (!name.matches(pattern)) {
                    throw new Exception();
                }
                return name;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String msg, String errorMsg) {
        double number;

        while (true) {
            try {
                System.out.print(msg);
                number = Double.parseDouble(scan.nextLine());
                if (number <= 0) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String msg, String errorMsg, double min, double max) {
        double number;
        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        while (true) {
            try {
                System.out.println(msg);
                number = Double.parseDouble(scan.nextLine());
                if (!(number >= min && number <= max)) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getAnID(String msg, String errorMsg) {
        String s = new String();
        String pattern = "[0-9]{4}";
        while (true) {
            try {
                System.out.print(msg);
                s = scan.nextLine();
                if (!s.matches(pattern)) {
                    throw new Exception();
                }
                return s;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getAnAccount(String msg, String errorMsg) {
        String s = new String();
        String pattern = "[0-9a-zA-Z]{4,15}";
        while (true) {
            try {
                System.out.print(msg);
                s = scan.nextLine();
                if (!s.matches(pattern)) {
                    throw new Exception();
                }
                return s;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getAPassword(String msg, String errorMsg) {
        String psw = new String();
        while (true) {
            try {
                System.out.print(msg);
                psw = PasswordField.readPassword(msg);
                if (!(psw.length() >= 6 && psw.length() <= 15)) {
                    throw new Exception();
                }
                return psw;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getAnswer(String msg, String errorMsg) {
        String answer = new String();
        while (true) {
            try {
                System.out.print(msg);
                answer = scan.nextLine();
                answer = answer.trim();
                if (!(answer.equalsIgnoreCase("yes")
                        || answer.equalsIgnoreCase("no"))) {
                    throw new Exception();
                }
                return answer;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
}
