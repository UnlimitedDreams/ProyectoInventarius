/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.DecimalFormat;

/**
 *
 * @author ASUS_01
 */
public class test {

    public static void main(String juan[]) {
        DecimalFormat formateador = new DecimalFormat("#########");
        String valor = "5";
        String b[] = valor.split(".");
        double a = 0;
        System.out.println("- " +Double.parseDouble(valor));
//        System.out.println("- " + Integer.parseInt(valor));
//        System.out.println("- " + b[1]);
        //  System.out.println("-- : " + formateador.format(valor));
//        a=Integer.parseInt(formateador.format(valor));
//        System.out.println("IVa : " + a);
    }
}
