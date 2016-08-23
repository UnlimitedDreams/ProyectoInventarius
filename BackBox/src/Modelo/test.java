/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.text.DecimalFormat;
import org.fife.ui.rsyntaxtextarea.Theme;

/**
 *
 * @author ASUS_01
 */
public class test {

    public static void main(String juan[]) throws InterruptedException {
        String v = "Kit-1";
        if(v.contains("Kit")){
            System.out.println("Si");
        }
        System.out.println(v.substring(2, v.length()));
    }
}
