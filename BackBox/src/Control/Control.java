package Control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;

public class Control {

    public static String driver = "org.postgresql.Driver";
    public static String connectString = "jdbc:postgresql://localhost:5431/Inventario";
    public static String user = "postgres";
    public static String password = "Juan";//ya esta listo
    public static String query;
    public static Statement stat;
    public static ResultSet rs;
    public static Connection con;

    public static boolean conectar() throws ClassNotFoundException {
        boolean r = false;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stat = con.createStatement();
            System.out.println("Conecto Bien");
            r = true;
            return r;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return r;
        }

    }
//METODOS PARA POSICIONAR EN EL RESULT SET
    // INDEPENDIENTE DE CUAL SEA 
    // LA TABLA

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
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en cerrar la base de datos" + e.toString());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        conectar();
        System.out.println(KeyStroke.getKeyStroke(KeyEvent.VK_U, (int)ActionEvent.TEXT_EVENT_MASK));
    }
}
