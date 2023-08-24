/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class TransaccionContable {
    private String nro_doc;
    private Integer secuencia_doc;
    private int cuenta_contable;
    private Double valor_debito;
    private Double valor_credito;
    private String comentario;

    // Constructor
    TransaccionContable(String nro_doc, Integer secuencia_doc, int cuenta_contable, Double valor_debito,
                       Double valor_credito, String comentario) {
        this.nro_doc = nro_doc;
        this.secuencia_doc = secuencia_doc;
        this.cuenta_contable = cuenta_contable;
        this.valor_debito = valor_debito;
        this.valor_credito = valor_credito;
        this.comentario = comentario;
    }

    public TransaccionContable() {
        
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public Integer getSecuencia_doc() {
        return secuencia_doc;
    }

    public void setSecuencia_doc(Integer secuencia_doc) {
        this.secuencia_doc = secuencia_doc;
    }

    public int getCuenta_contable() {
        return cuenta_contable;
    }

    public void setCuenta_contable(int cuenta_contable) {
        this.cuenta_contable = cuenta_contable;
    }

    public Double getValor_debito() {
        return valor_debito;
    }

    public void setValor_debito(Double valor_debito) {
        this.valor_debito = valor_debito;
    }

    public Double getValor_credito() {
        return valor_credito;
    }

    public void setValor_credito(Double valor_credito) {
        this.valor_credito = valor_credito;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isTipo_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getNro_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getDescripcion_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getGrupo_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getNivel_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getCta_padre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getFecha_creacion_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getHora_creacion_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getDebito_acum_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getCredito_acum_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getBalance_cta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
