/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Control.Control;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class test {

    public static void main(String[] agrs) throws ClassNotFoundException, SQLException {
        int valor=1;
        int valor2=2;
       if(valor==1 && valor2==2){
           System.out.println("b");
       }else if(valor==1){
           System.out.println("a");
       }
//        Control.conectar();
//        int a = 1;
//        int b = 0;
//        try {
//            Control.con.setAutoCommit(false);
//
//            Control.ejecuteUpdate("insert into acciones values(100,'papa')");            
//            Control.ejecuteUpdate("insert into acciones values(101," + a + ")");
//            Control.ejecuteUpdate("insert into acciones values(102," + a + ")");
//            b=Integer.parseInt("a");
//
//        } catch (NumberFormatException ex) {
//            System.err.println("--------------");
//            //Control.con.rollback();
//        } 
//        finally {
//            System.out.println("entro ");
//            Control.con.commit();
//            Control.con.setAutoCommit(true);
//            Control.cerrarConexion();
//        }

    }

}
