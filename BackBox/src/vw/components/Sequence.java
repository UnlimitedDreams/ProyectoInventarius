/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import Control.Control;

/**
 *
 * @author Family
 */
public class Sequence {

    public static int seque(String sql) throws ClassNotFoundException {
        Control.conectar();
        Control.ejecuteQuery(sql);
        int cod = 0;
        try {
            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
            }
            Control.cerrarConexion();
        } catch (Exception ex) {

        }
        return cod + 1;
    }
}
