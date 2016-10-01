
package Modelo;

/**
 * Esta clase se encarga de Cargar, Las categorias del software.
 * @author: Unlimited Dreams
 * @version: 25/08/2016
 */
public class List_Object {

    private int codigo;
    private String nombre;

    public List_Object(int cod, String nom) {
        this.codigo = cod;
        this.nombre = nom;
    }

    public List_Object() {
    }

    public int getCod() {
        return codigo;
    }

    public void setCod(int cod) {
        this.codigo = cod;
    }

    public String getNom() {
        return nombre;
    }

    public void setNom(String nom) {
        this.nombre = nom;
    }

}
