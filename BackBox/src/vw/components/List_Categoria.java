/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vw.components;

/**
 *
 * @author Family
 */
public class List_Categoria {
    private int cod;
    private String nom;

    public List_Categoria(int cod, String nom) {
        this.cod = cod;
        this.nom = nom;
    }

    public List_Categoria() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
