/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ContenedorMenus {
    private ArrayList<acciones> listaAcciones=new ArrayList();
    private ArrayList<seccion> listaSeccion=new ArrayList();

    public ContenedorMenus() {
    }

    public ArrayList<acciones> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(ArrayList<acciones> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public ArrayList<seccion> getListaSeccion() {
        return listaSeccion;
    }

    public void setListaSeccion(ArrayList<seccion> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }
    
    
    
    
}
