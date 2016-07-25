/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.swing.JOptionPane;

/**
 *
 * @author Family
 */
public class Entrada {

    public static void muestreMensajeV(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "BackBox", JOptionPane.PLAIN_MESSAGE);
    }

    public static void muestreMensajeV(String mensaje, int Messagetype) {
        JOptionPane.showMessageDialog(null, mensaje, "BackBox", Messagetype);
    }

    public static int leaNroEnteroV(String mensaje) {
        String snro = "";
        int nro = 0;
        snro = JOptionPane.showInputDialog(null, mensaje, "BackBox", JOptionPane.INFORMATION_MESSAGE);
        nro = Integer.parseInt(snro);
        return nro;
    }

    public static double leaNroRealV(String mensaje) {
        String snro = "";
        double nro = 0;
        snro = JOptionPane.showInputDialog(null, mensaje, "BackBox", JOptionPane.INFORMATION_MESSAGE);
        nro = Double.parseDouble(snro);
        return nro;
    }

    public static char LeaCaracterV(String mensaje) {
        char x;

        String str;
        str = "";
        str = JOptionPane.showInputDialog(mensaje, "A");
        x = str.charAt(0);

        return x;
    }

    public static String leaCadenaV(String mensaje) {
        String f = null;
        f = JOptionPane.showInputDialog(mensaje);
        return f;
    }

    public static int menu(String titulo, String menu, String[] opciones) {
        int respuesta = 0;
        respuesta = JOptionPane.showOptionDialog(null,
                menu,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //don't use a custom Icon
                opciones, //the titles of buttons
                opciones[0]);

        return respuesta + 1;
    }

}
