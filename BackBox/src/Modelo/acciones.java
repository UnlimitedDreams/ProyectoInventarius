
package Modelo;
/**
 * Esta clase se encarga de cargar las acciones de un rol
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class acciones {
   private int cod_actividad; 
   private int cod_accion;
   private String accion;
   private int orden;

    public acciones(int cod_actividad, int orden) {
        this.cod_actividad = cod_actividad;
        this.orden = orden;
    }
   
    public acciones(int cod_accion, String accion) {
        this.cod_accion = cod_accion;
        this.accion = accion;        
    }

    public int getCod_seccion() {
        return cod_actividad;
    }

    public void setCod_seccion(int cod_seccion) {
        this.cod_actividad = cod_seccion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getCod_actividad() {
        return cod_actividad;
    }

    public void setCod_actividad(int cod_actividad) {
        this.cod_actividad = cod_actividad;
    }

    public int getCod_accion() {
        return cod_accion;
    }

    public void setCod_accion(int cod_accion) {
        this.cod_accion = cod_accion;
    }
    

    @Override
    public String toString() {
        return "acciones{" + "cod_actividad=" + cod_actividad + ", accion=" + accion + ", orden=" + orden + '}';
    }

}
