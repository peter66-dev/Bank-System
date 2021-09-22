/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidePassword;


/**
 *
 * @author Dell
 */
public class EraserThread implements Runnable {

    private boolean stop;

    public EraserThread(String prompt) {
        System.out.print(prompt);
    }

    public void run() {
        stop = true;
        while (stop) {
            System.out.print("\010*");
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void stopMasking() {
        this.stop = false;
    }
}
