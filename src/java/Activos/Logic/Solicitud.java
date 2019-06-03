/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Logic;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorac
 */
public class Solicitud {
    //Tipos de solicitudes
    public static final int COMPRA = 1;
    public static final int DONACION = 2;

    //Estados
    public static final int RECIBIDA = 1;
    public static final int POR_VERIFICAR = 2;
    public static final int RECHAZADA = 3;
    public static final int ESPERA_ROTULACION = 4;
    public static final int PROCESADA = 5;

    int ID;
    String comprobante;
    Date fecha;
    int tipo;
    int estado;
    Funcionario registrador;
    Dependencia dependencia;
    int cantidad;
    double total;
    List<Bien> bienes;
    String motivoRechazo;

    public Solicitud() {
        this.comprobante = "";
        LocalDate localdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = Date.valueOf(localdate.format(formatter));
        this.fecha = date;
        this.tipo = 1;
        this.estado = 1;
        this.registrador = new Funcionario();
        this.dependencia = new Dependencia();
        this.cantidad = 0;
        this.total = 0;
        this.bienes = new ArrayList<>();
        this.motivoRechazo = "";
    }

    public Solicitud(String comprobante, Date fecha, int tipo, int estado, Funcionario registrador, Dependencia dependencia, int cantidad, double total, List<Bien> bienes, String motivoRechazo) {
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
        this.registrador = registrador;
        this.dependencia = dependencia;
        this.cantidad = cantidad;
        this.total = total;
        this.bienes = bienes;
        this.cantidad = 0;

        for (Bien b : bienes) {
            total = total + b.getPrecio();
            this.cantidad = this.cantidad + b.getCantidad();
        }
        this.motivoRechazo = motivoRechazo;
    }

    public Solicitud(String comprobante, Date fecha, int tipo, Funcionario registrador, Dependencia dependencia, List<Bien> bienes, String motivoRechazo) {
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.registrador = registrador;
        this.dependencia = dependencia;
        this.bienes = bienes;
        this.cantidad = 0;

        for (Bien b : bienes) {
            total = total + b.getPrecio();
            this.cantidad = this.cantidad + b.getCantidad();
        }
        this.motivoRechazo = motivoRechazo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFechaString() {
        LocalDate localdate = fecha.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localdate.format(formatter);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Funcionario getRegistrador() {
        return registrador;
    }

    public void setRegistrador(Funcionario registrador) {
        this.registrador = registrador;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Bien> getBienes() {
        return bienes;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public void setBienes(List<Bien> bienes) {
        this.bienes = bienes;
        this.cantidad = 0;

        for (Bien b : bienes) {
            total = total + b.getPrecio();
            this.cantidad = this.cantidad + b.getCantidad();
        }
    }

    public String getDescripcionTipo() {
        switch (tipo) {
            case COMPRA:
                return "Compra";
            case DONACION:
                return "Donacion";
            default:
                return "Indefinido";
        }
    }

    public String getDescripcionEstado() {
        switch (estado) {
            case RECIBIDA:
                return "Recibida";
            case POR_VERIFICAR:
                return "Por Verificar";
            case RECHAZADA:
                return "Rechazada";
            case ESPERA_ROTULACION:
                return "Espera Rotulacion";
            case PROCESADA:
                return "Procesada";
        }
        return "Indefinda";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Solicitud)) {
            return false;
        }
        Solicitud otro = (Solicitud) other;
        return otro.getID() == ID;
    }

}
