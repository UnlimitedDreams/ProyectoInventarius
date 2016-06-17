/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

/**
 *
 * @author admin
 */
public class test {

    public static void main(String[] agrs) {
        String a = "";
        int b = detectarNumero(a);
    }

    public static int detectarNumero(String a) {
        int valor = 0;
        try {
            valor = Integer.parseInt(a);
        } catch (Exception ex) {
            valor = 0;
            System.out.println("Error sistema");
        }
        return valor;
    }

}
