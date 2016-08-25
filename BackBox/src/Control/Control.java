package Control;

import java.sql.*;

public class Control {

    public static String driver = "org.postgresql.Driver";
    public static String connectString = "jdbc:postgresql://localhost:5432/Inventario";
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
            r = true;
            return r;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return r;
        }

    }

    public static boolean ejecuteQuery(String x) throws SQLException {
        boolean r = true;
        try {
            rs = stat.executeQuery(x);
        } catch (SQLException e) {
            System.out.println("ERROR AL HACER QUERY " + e.toString());

            r = false;
        }
        return r;
    }

    public static boolean ejecuteUpdate(String query) {
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
    public static void cerrarConexion() {
        try {
            stat.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en cerrar la base de datos" + e.toString());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        conectar();
    }
}
