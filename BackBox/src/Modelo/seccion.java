
package Modelo;

import java.util.ArrayList;

/**
 * Esta clase se encargar de cargar todas las sessiones o actividades de un rol
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class seccion {
    private int cod_seccion;
    private String descripcion;
    private String estado;
    private int posicion;
    ArrayList<acciones> list_acciones=new ArrayList();

    public seccion(int cod_seccion, String descripcion) {
        this.cod_seccion = cod_seccion;
        this.descripcion = descripcion;
    }

    public seccion(int cod_seccion, String descripcion, String estado, int posicion) {
        this.cod_seccion = cod_seccion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.posicion = posicion;
    }

 
    

    public int getCod_seccion() {
        return cod_seccion;
    }

    public void setCod_seccion(int cod_seccion) {
        this.cod_seccion = cod_seccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<acciones> getList_acciones() {
        return list_acciones;
    }

    public void setList_acciones(ArrayList<acciones> list_acciones) {
        this.list_acciones = list_acciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    

     
   
    

    
}
