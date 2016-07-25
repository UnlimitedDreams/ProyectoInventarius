/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ASUS_01
 */
public class test {

    public static void main(String[] juan) {
        
            String cmd = "C:\\Users\\ASUS_01\\Documents\\NetBeansProjects\\BackBox - Instalador\\src\\Inicio.bat";
//                    + "start postgresql-9.4.4-1-windows-x64.exe"; //Comando de apagado en linux            
            CargarBat(cmd);
           // Runtime.getRuntime().exec(cmd);
       
    }
    public static void CargarBat(String comando){ 
    try { 
        String linea; 
        Process p = Runtime.getRuntime().exec(comando); 
        BufferedReader input = new BufferedReader (new InputStreamReader (p.getInputStream())); 
        while ((linea = input.readLine()) != null) { 
             System.out.println(linea); 
         } 
           input.close(); 
    }catch (Exception err) { 
           err.printStackTrace(); 
    } 
  }  
}
