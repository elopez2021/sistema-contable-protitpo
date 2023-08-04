/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Usuarios {
    private String loginUsuario;
    private String passUsuario;
    private int nivelAcceso;
    private String nombreUsuario;
    private String apellidosUsuarios;
    private String emailUsuario;

    public Usuarios(String loginUsuario, String passUsuario, int nivelAcceso, String nombreUsuario, String apellidosUsuarios, String emailUsuario) {
        this.loginUsuario = loginUsuario;
        this.passUsuario = passUsuario;
        this.nivelAcceso = nivelAcceso;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuarios = apellidosUsuarios;
        this.emailUsuario = emailUsuario;
    }

    public Usuarios() {
        
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuarios() {
        return apellidosUsuarios;
    }

    public void setApellidosUsuarios(String apellidosUsuarios) {
        this.apellidosUsuarios = apellidosUsuarios;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    
    
}
