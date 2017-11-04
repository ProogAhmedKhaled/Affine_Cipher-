/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import javax.swing.JOptionPane;

/**
 *
 * @author O.o
 */
public class Security {

    public static void main(String[] args) {

        //  String st = JOptionPane.showInputDialog("Enter your Letters");
        int c = Integer.parseInt(JOptionPane.showInputDialog("Choose \n 1- Standard Letters a to z \n 2- Custom Letters "));

        AffineMethod Affine = new AffineMethod(c);

        // af.display();
        int c2 = Integer.parseInt(JOptionPane.showInputDialog("Choose \n 1- Encryotion \n 2-Decryption \n "
                + "3- Get Keys"));
        switch (c2) {

            case 1:
                String PlaneText = JOptionPane.showInputDialog("Enter your Message that you want to Encrypt !! ");
                int M = Integer.parseInt(JOptionPane.showInputDialog("Enter Multicative Key (M) "));
                int K = Integer.parseInt(JOptionPane.showInputDialog("Enter Addative Key (K)"));
                String CipherMessage;

                if (M > 0 && K > 0) {
                    CipherMessage = Affine.affineEncryption(PlaneText, M, K);

                    System.out.println(CipherMessage);
                }
                break;

            case 2:

                String CipherText = JOptionPane.showInputDialog("Enter your Message that you want to Dycrypt !! ");
                int MInverse = Integer.parseInt(JOptionPane.showInputDialog("Enter Multicative Key (M) "));
                int KSuptsract = Integer.parseInt(JOptionPane.showInputDialog("Enter Addative Key (K)"));
                String PlanMessga;

                PlanMessga = Affine.affineDecryption(CipherText, MInverse, KSuptsract);

                System.out.println(PlanMessga);

                break;

            case 3:
                Affine.getKeys();
                break;

        }

    }

}
