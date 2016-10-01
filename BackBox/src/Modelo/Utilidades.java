package Modelo;

/**
 * Clase de utilidades para el sistema
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class Utilidades {

    /**
     * Metodo que se encarga de determinar si la variable String es numerica o     
     */
    public static boolean SoloNumeros(String cadenaNumerica) {
        try {
            Long.parseLong(cadenaNumerica);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
