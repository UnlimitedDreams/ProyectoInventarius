/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Family
 */
public class Producto {

    private String codigo;
    private int codigoProducto;
    private String nombre;
    private double Costo;
    private int iva;
    private int codigoIva;
    private double precio_venta;
    private int cantidad;
    private int desc;
    private int categoria;
    private double precio_final;
    private int stock;
    private double ValorIva;
    private String Esta;
    private int CantBD;
    private int IvaCargeArchivo;
    private boolean MarcaKits;

    public Producto(String codigo, String nombre, double Costo, int iva, double precio_venta, int cantidad, int desc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Costo = Costo;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
        this.desc = desc;
        this.iva = iva;
    }

    public Producto(String codigo, String nombre, double precio_venta, int cantidad, int iva, int stock, double valorIva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
        this.iva = iva;
        this.stock = stock;
        this.ValorIva = valorIva;
    }

    public Producto(String codigo, String nombre, double Costo, double precio_venta, int cantidad, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Costo = Costo;
        this.precio_venta = precio_venta;
        this.cantidad = cantidad;
        this.stock = stock;
    }

    public Producto(String codigo, String nombre, double precio_venta, int desc, double precio_final) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.desc = desc;
        this.precio_final = precio_final;
    }

    public Producto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Producto(String codigo, int codigoProducto, String nombre, double Costo, double precio_venta) {
        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.Costo = Costo;
        this.precio_venta = precio_venta;
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

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
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

    public double getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(double precio_final) {
        this.precio_final = precio_final;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getValorIva() {
        return ValorIva;
    }

    public void setValorIva(double ValorIva) {
        this.ValorIva = ValorIva;
    }

    public String getEsta() {
        return Esta;
    }

    public void setEsta(String Esta) {
        this.Esta = Esta;
    }

    public int getCantBD() {
        return CantBD;
    }

    public void setCantBD(int CantBD) {
        this.CantBD = CantBD;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoIva() {
        return codigoIva;
    }

    public void setCodigoIva(int codigoIva) {
        this.codigoIva = codigoIva;
    }

    public int getIvaCargeArchivo() {
        return IvaCargeArchivo;
    }

    public void setIvaCargeArchivo(int IvaCargeArchivo) {
        this.IvaCargeArchivo = IvaCargeArchivo;
    }

    public boolean isMarcaKits() {
        return MarcaKits;
    }

    public void setMarcaKits(boolean MarcaKits) {
        this.MarcaKits = MarcaKits;
    }
    

}
