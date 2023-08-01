/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class CatalogoCuenta {
    private int nro_cta;
    private String descripcion_cta;
    private int tipo_cta;
    private int nivel_cta;
    private int cta_padre;
    private int grupo_cta;
    private Date fecha_creacion_cta;
    private Date hora_creacion_cta;
    private Double debito_acum_cta;
    private Double credito_acum_cta;
    private Double balance_cta;

    // Constructor
    public CatalogoCuenta(int nro_cta, String descripcion_cta, int tipo_cta, int nivel_cta, int cta_padre, int grupo_cta,
                          Date fecha_creacion_cta, Date hora_creacion_cta, Double debito_acum_cta, Double credito_acum_cta,
                          Double balance_cta) {
        this.nro_cta = nro_cta;
        this.descripcion_cta = descripcion_cta;
        this.tipo_cta = tipo_cta;
        this.nivel_cta = nivel_cta;
        this.cta_padre = cta_padre;
        this.grupo_cta = grupo_cta;
        this.fecha_creacion_cta = fecha_creacion_cta;
        this.hora_creacion_cta = hora_creacion_cta;
        this.debito_acum_cta = debito_acum_cta;
        this.credito_acum_cta = credito_acum_cta;
        this.balance_cta = balance_cta;
    }

    public int getNro_cta() {
        return nro_cta;
    }

    public void setNro_cta(int nro_cta) {
        this.nro_cta = nro_cta;
    }

    public String getDescripcion_cta() {
        return descripcion_cta;
    }

    public void setDescripcion_cta(String descripcion_cta) {
        this.descripcion_cta = descripcion_cta;
    }

    public int getTipo_cta() {
        return tipo_cta;
    }

    public void setTipo_cta(int tipo_cta) {
        this.tipo_cta = tipo_cta;
    }

    public int getNivel_cta() {
        return nivel_cta;
    }

    public void setNivel_cta(int nivel_cta) {
        this.nivel_cta = nivel_cta;
    }

    public int getCta_padre() {
        return cta_padre;
    }

    public void setCta_padre(int cta_padre) {
        this.cta_padre = cta_padre;
    }

    public int getGrupo_cta() {
        return grupo_cta;
    }

    public void setGrupo_cta(int grupo_cta) {
        this.grupo_cta = grupo_cta;
    }

    public Date getFecha_creacion_cta() {
        return fecha_creacion_cta;
    }

    public void setFecha_creacion_cta(Date fecha_creacion_cta) {
        this.fecha_creacion_cta = fecha_creacion_cta;
    }

    public Date getHora_creacion_cta() {
        return hora_creacion_cta;
    }

    public void setHora_creacion_cta(Date hora_creacion_cta) {
        this.hora_creacion_cta = hora_creacion_cta;
    }

    public Double getDebito_acum_cta() {
        return debito_acum_cta;
    }

    public void setDebito_acum_cta(Double debito_acum_cta) {
        this.debito_acum_cta = debito_acum_cta;
    }

    public Double getCredito_acum_cta() {
        return credito_acum_cta;
    }

    public void setCredito_acum_cta(Double credito_acum_cta) {
        this.credito_acum_cta = credito_acum_cta;
    }

    public Double getBalance_cta() {
        return balance_cta;
    }

    public void setBalance_cta(Double balance_cta) {
        this.balance_cta = balance_cta;
    }

    
}