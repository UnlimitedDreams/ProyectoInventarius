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
public class seccion {
    private int cod_seccion;
    private String descripcion;

    public seccion(int cod_seccion, String descripcion) {
        this.cod_seccion = cod_seccion;
        this.descripcion = descripcion;
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

   
    

    
}
