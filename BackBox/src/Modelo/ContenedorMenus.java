package Modelo;

import java.util.ArrayList;

/**
 * Esta clase se encarga de Cargar, el Menu de cada Usuario. con sus titulos y subtitulos, de acuerdo al rol que tenga el usuario
 * @author: Unlimited Dreams
 * @version: 25/08/2016
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
