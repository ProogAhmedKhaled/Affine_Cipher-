/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.math.BigInteger;
import javax.swing.JOptionPane;

/**
 *
 * @author O.o
 */
public class AffineMethod {

    public String Alpha;

    public char[] Alphap;

    public AffineMethod(int choice) {

        if (choice == 1) {
            this.Alpha = "abcdefghijklmnopqrstuvwxyz";
            // Alphap = Alpha.toCharArray();
        } else if (choice == 2) {
            this.Alpha = JOptionPane.showInputDialog("Enter your Letter !! ");

            // Alphap = Alpha.toCharArray();
        }

        Alphap = Alpha.toCharArray();
    }

    /* Just For Test   
     public void display() {

     for (char B : Alphap) {

     System.out.println(B);
     }

     }
     */
    public int getElementIndex(char ch) {
        int index = 0;

        for (int i = 0; i < Alphap.length; i++) {

            if (Alphap[i] == ch) {
                index = i;
                break;
            }
        }

        return index;
    }

    public char getElement(int index) {

        char ch;

        ch = Alphap[index];

        return ch;
    }

    public int GCD(int n1, int n2) {

        int result;
        BigInteger b1, b2;

        b1 = new BigInteger("" + n1);
        b2 = new BigInteger("" + n2);

        result = Integer.parseInt("" + b1.gcd(b2));

        return result;
    }

    public int mod(int E, int Z) {

        String e = "" + E;
        String z = "" + Z;
        String str = "";
        // create 3 BigInteger objects
        BigInteger bi1, bi2, bi3;

        // Two numbers are relatively prime if they have no
        // common factors, other than 1.
        bi1 = new BigInteger(e);
        bi2 = new BigInteger(z);

        bi3 = bi1.mod(bi2);

        str = "" + bi3;

        return Integer.parseInt(str);
    }

    public int modularInverse(int E, int Z) {

        String e = "" + E;
        String z = "" + Z;
        String str = "";

        // create 3 BigInteger objects
        BigInteger bi1, bi2, bi3;

        // Two numbers are relatively prime if they have no
        // common factors, other than 1.
        bi1 = new BigInteger(e);
        bi2 = new BigInteger(z);

        // perform modInverse operation on bi1 using bi2
        if (GCD(E, Z) == 1) {
            bi3 = bi1.modInverse(bi2);

            str = "" + bi3;
        }
        return Integer.parseInt(str);
    }

    public String affineEncryption(String Message, int m, int k) {

        String EMessage = "";

        BigInteger b1;
        BigInteger b2 = new BigInteger("" + Alphap.length);
        int i = 0;

        if (GCD(m, k) == 1) {
            while (i < Message.length()) {

                int r = m * getElementIndex(Message.charAt(i)) + k;

                b1 = new BigInteger("" + r);
                EMessage += "" + getElement(Integer.parseInt("" + b1.mod(b2)));

                i++;
            }
        }
        return EMessage;

    }

    public String affineDecryption(String Message, int m, int k) {

        String EMessage = "";

        BigInteger b1;

        BigInteger b2 = new BigInteger("" + Alphap.length);
        int i = 0;

        if (GCD(m, k) == 1) {
            while (i < Message.length()) {

                int r = modularInverse(m, Alphap.length) * (getElementIndex(Message.charAt(i)) - k);

                b1 = new BigInteger("" + r);
                EMessage += "" + getElement(Integer.parseInt("" + b1.mod(b2)));
                i++;
            }
        }
        return EMessage;

    }

    public void getKeys() {

        String plan = JOptionPane.showInputDialog("Enter yout plan ");
        String cipher = JOptionPane.showInputDialog("Enter your Cipher ");

        // note using the operator | as a single ot || // 
        if (plan.contains(" ") | cipher.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Error paln or Cipher Contains space ..");
        } else {
            int a1 = getElementIndex(plan.charAt(0));
            int a2 = getElementIndex(plan.charAt(1));
            int c1 = getElementIndex(cipher.charAt(0));
            int c2 = getElementIndex(cipher.charAt(1));

            int mkey, k;

            int var1 = c1 - c2;
            int var2 = a1 - a2;

            int var3 = (a1 * c2) - (a2 * c1);

            int GCD = GCD(var1, var2);

            var1 /= GCD;
            int demo = var2 / GCD;
            GCD = GCD(var3, var2);

            var3 /= GCD;
            var2 /= GCD;

            if (GCD(demo, Alphap.length) == 1 && GCD(var2, Alphap.length) == 1) {

                int minverse = modularInverse(demo, Alphap.length);

                mkey = var1 * minverse;

                k = var3 * modularInverse(var2, Alphap.length);

                mkey = mod(mkey, Alphap.length);

                k = mod(k, Alphap.length);

                System.out.println(mkey);
                System.out.println(k);
            } else {

                JOptionPane.showMessageDialog(null, " Erorr ");
            }
        }
    }

}
