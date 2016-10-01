package Modelo;

/**
 * Esta clase es el modelo de la tabla de Acciones permitidas para el Usuario
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */


public class AccionesUsu {
    private int codigoAccion;
    private String descripcion;

    public AccionesUsu() {
    }

    public AccionesUsu(int codigoAccion, String descripcion) {
        this.codigoAccion = codigoAccion;
        this.descripcion = descripcion;
    }

    public int getCodigoAccion() {
        return codigoAccion;
    }

    public void setCodigoAccion(int codigoAccion) {
        this.codigoAccion = codigoAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
