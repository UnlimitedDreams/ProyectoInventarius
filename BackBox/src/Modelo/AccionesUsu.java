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
