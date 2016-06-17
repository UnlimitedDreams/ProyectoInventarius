/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ControlSrv {
   
    public static String query;
    public static Statement stat;
    public static ResultSet rs;
    public static Connection connection;

    public static boolean conectar() {
        Properties propiedades = new Properties();
        InputStream entrada = null;
        boolean r = false;
        try {
            entrada = new FileInputStream("Configuracion.properties");
            propiedades.load(entrada);

            Class.forName(propiedades.getProperty("driver"));
            connection = DriverManager.getConnection(
                    propiedades.getProperty("connectString"),
                    propiedades.getProperty("usuario"),
                    propiedades.getProperty("clave"));
            stat = connection.createStatement();
            r = true;
            return r;
        } catch (SQLException | ClassNotFoundException | FileNotFoundException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            return r;
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            return r;
        }

    }

    public static boolean ejecuteQuery() {
        boolean r = false;
        try {
            rs = stat.executeQuery(query);
            r = true;
        } catch (Exception e) {
            System.out.println("Hubo un Error en Ejecute Query -> " + e);
            r = false;
        }
        return r;
    }

    public static boolean ejecuteQuery(String x) {
        boolean r = true;
        try {
            rs = stat.executeQuery(x);
        } catch (SQLException e) {
            System.out.println("ERROR AL HACER QUERY " + e.toString());
            r = false;
        }
        return r;
    }

    public static boolean ejecuteUpdate() {
        boolean r = true;
        try {
            stat.executeUpdate(query);
            r = true;
        } catch (SQLException e) {
            System.out.println("ERROR Al HACER UPDTAPE" + e.toString());
            r = false;
        }
        return r;
    }

    public static boolean ejecuteUpdate(String q) {
        query = q;
        return (ejecuteUpdate());
    }

    public static boolean primero() {
        boolean r = true;
        try {

            if (Control.rs.first()) {
                r = true;
            } else {
                r = false;
            }

        } catch (SQLException e) {
            System.out.println("Error en Primero Actividad" + e.toString());
            r = false;

        }

        return r;
    }

    public static boolean anterior() {
        boolean r = true;
        try {
            if (Control.rs.previous()) {
                r = true;
            } else {
                r = false;
            }
        } catch (SQLException e) {
            System.out.println("Error en Primero Actividad" + e.toString());
            r = false;

        }
        return r;

    }

    public static boolean siguiente() {
        boolean r = true;
        try {
            if (Control.rs.next()) {
                r = true;
            } else {
                r = false;
            }
        } catch (SQLException e) {
            System.out.println("Error en Primero Actividad" + e.toString());
            r = false;

        }
        return r;

    }

    public static boolean ultimo() {
        boolean r = true;
        try {
            if (Control.rs.last()) {
                r = true;
            } else {
                r = false;
            }
        } catch (SQLException e) {
            System.out.println("Error en Primero Actividad" + e.toString());
            r = false;
        }
        return r;
    }

    public static void cerrarConexion() {
        try {
            stat.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error en cerrar la base de datos" + e.toString());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        conectar();
    } 
}
