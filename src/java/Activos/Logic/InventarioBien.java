/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

/**
 *
 * @author Jose
 */
public class InventarioBien {
    int ID;
    String descripcion;
    String marca;
    String modelo;
    double precio;
    String cod;

    public InventarioBien(int ID, String descripcion, String marca, String modelo, double precio, String cod) {
        this.ID = ID;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.cod = cod;
    }

    public int getID() {
        return ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCod() {
        return cod;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    
    
}
