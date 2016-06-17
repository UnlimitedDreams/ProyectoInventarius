/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vw.components;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Family
 */
public class Producto {

    private String codigo;
    private String nombre;
    private double Costo;
    private double iva;
    private double precio_venta;
    private int cantidad;
    private int desc;
    private int categoria;
    private int precio_final;
    private int stock;

    public Producto(String codigo, String nombre, int Costo, int iva, int precio_venta, int cantidad, int desc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Costo = Costo;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
        this.desc = desc;
        this.iva = iva;
    }

    public Producto(String codigo, String nombre, double precio_venta, int cantidad, double iva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
        this.iva = iva;
    }

    public Producto(String codigo, String nombre, double Costo, double precio_venta, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Costo = Costo;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
    }

    public Producto() {
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", Costo=" + Costo + ", iva=" + iva + ", precio_venta=" + precio_venta + ", cantidad=" + cantidad + ", desc=" + desc + ", categoria=" + categoria + '}';
    }

    public int getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(int precio_final) {
        this.precio_final = precio_final;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    



}
