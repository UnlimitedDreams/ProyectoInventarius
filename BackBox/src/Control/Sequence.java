/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.Control;

/**
 * Esta clase se encargar de las secuencias de todas las tablas, recibiendo la consulta como parametro
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */

public class Sequence {

    
/**
     * Método que se encargar de retornar la secuencia de la tabla.
     *
     * @param sql El parámetro sql define la consulta de la tabla.
     * @return El número de secuencoa de la tabla
     */
    public static int seque(String sql) throws ClassNotFoundException {
        Control.conectar();
        int cod = 0;
        try {
            Control.ejecuteQuery(sql);
            while (Control.rs.next()) {
                cod = Control.rs.getInt(1);
            }
        } catch (Exception ex) {

        } finally {
            Control.cerrarConexion();
        }
        return cod + 1;
    }
}
