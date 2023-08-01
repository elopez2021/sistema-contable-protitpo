package model;

import java.util.Date;

public class CabeceraTransaccion {
    private String nroDocu;
    private String fechaDocu;
    private int tipoDocu;
    private String descripcionDocu;
    private String hechoPor;
    private double montoTransaccion;
    private Date fechaActualizacion;
    private boolean statusActualizacion;

    public CabeceraTransaccion(String nroDocu, String fechaDocu, int tipoDocu, String descripcionDocu, String hechoPor, double montoTransaccion, Date fechaActualizacion, boolean statusActualizacion) {
        this.nroDocu = nroDocu;
        this.fechaDocu = fechaDocu;
        this.tipoDocu = tipoDocu;
        this.descripcionDocu = descripcionDocu;
        this.hechoPor = hechoPor;
        this.montoTransaccion = montoTransaccion;
        this.fechaActualizacion = fechaActualizacion;
        this.statusActualizacion = statusActualizacion;
    }

    public String getNroDocu() {
        return nroDocu;
    }

    public void setNroDocu(String nroDocu) {
        this.nroDocu = nroDocu;
    }

    public String getFechaDocu() {
        return fechaDocu;
    }

    public void setFechaDocu(String fechaDocu) {
        this.fechaDocu = fechaDocu;
    }

    public int getTipoDocu() {
        return tipoDocu;
    }

    public void setTipoDocu(int tipoDocu) {
        this.tipoDocu = tipoDocu;
    }

    public String getDescripcionDocu() {
        return descripcionDocu;
    }

    public void setDescripcionDocu(String descripcionDocu) {
        this.descripcionDocu = descripcionDocu;
    }

    public String getHechoPor() {
        return hechoPor;
    }

    public void setHechoPor(String hechoPor) {
        this.hechoPor = hechoPor;
    }

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isStatusActualizacion() {
        return statusActualizacion;
    }

    public void setStatusActualizacion(boolean statusActualizacion) {
        this.statusActualizacion = statusActualizacion;
    }    
    
}
