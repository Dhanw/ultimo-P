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
public class Parametros {
    
    private int ID;
    private String Tipo;
    private String Cod;
    private int Cantidad;

    public Parametros() {
    }

    public Parametros(int ID, String Tipo, String Cod, int Cantidad) {
        this.ID = ID;
        this.Tipo = Tipo;
        this.Cod = Cod;
        this.Cantidad = Cantidad;
    }

    public int getID() {
        return ID;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getCod() {
        return Cod;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public void setCod(String Cod) {
        this.Cod = Cod;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    
}
