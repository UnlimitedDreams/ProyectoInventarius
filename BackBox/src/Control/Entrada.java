/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.swing.JOptionPane;

/**
 * Esta clase se usa para los mensajes
 *
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class Entrada {

    public static void muestreMensajeV(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "BackBox", JOptionPane.PLAIN_MESSAGE);
    }

    public static void muestreMensajeV(String mensaje, int Messagetype) {
        JOptionPane.showMessageDialog(null, mensaje, "BackBox", Messagetype);
    }

    public static int leaNroEnteroV(String mensaje) {
        String snro = JOptionPane.showInputDialog(null, mensaje, "BackBox", JOptionPane.INFORMATION_MESSAGE);
        return Integer.parseInt(snro.equalsIgnoreCase("") ? "0" : snro);
    }

    public static double leaNroRealV(String mensaje) {
        String snro = JOptionPane.showInputDialog(null, mensaje, "BackBox", JOptionPane.INFORMATION_MESSAGE);
        return Double.parseDouble(snro.equalsIgnoreCase("") ? "0" : snro);
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
        return "" + JOptionPane.showInputDialog(mensaje);
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
