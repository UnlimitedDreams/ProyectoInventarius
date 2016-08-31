/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Asus
 */
public class acciones {
   private int cod_actividad; 
   private String accion;

    public acciones(int cod_seccion, String accion) {
        this.cod_actividad = cod_seccion;
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

    @Override
    public String toString() {
        return "acciones{" + "cod_actividad=" + cod_actividad + ", accion=" + accion + '}';
    }

   
   
}
