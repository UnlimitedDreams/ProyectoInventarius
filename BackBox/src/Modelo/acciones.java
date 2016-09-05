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
   private int orden;

    public acciones(int cod_actividad, int orden) {
        this.cod_actividad = cod_actividad;
        this.orden = orden;
    }
   
   

    public acciones(int cod_actividad, String accion) {
        this.cod_actividad = cod_actividad;
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

    @Override
    public String toString() {
        return "acciones{" + "cod_actividad=" + cod_actividad + ", accion=" + accion + ", orden=" + orden + '}';
    }
    
    

   
   
}
