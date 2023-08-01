/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Usuarios;

/**
 *
 * @author Zoila López
 */
public class UsuarioController implements Controller {

    private static final String RUTA_ARCHIVO = "src/database/usuarios.txt";
    private static final String RUTA_ARCHIVO_ACCESO = "src/database/acceso.txt";

    // Constructor
    public UsuarioController() {
        init();
    }

    @Override
    public void init() {
        // Verificar si el archivo "usuarios.txt" existe, y si no existe, crearlo
        File usuariosFile = new File(RUTA_ARCHIVO);
        if (!usuariosFile.exists()) {
            try {
                usuariosFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al crear el archivo usuarios.txt");
            }
        }
    }

    // Método para verificar las credenciales del usuario (login)
    public static boolean existeUsuario(String login_usuario, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String login = campos[3];
                String pass = campos[1];
                if (login.equals(login_usuario) && pass.equals(password)) {
                    return true; // Credenciales válidas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al leer el archivo usuarios.txt");
        }
        return false; // Credenciales incorrectas o usuario no encontrado
    }

    // Método para obtener el tipo de acceso desde el archivo acceso.txt
    public static String obtenerTipoAcceso(String login_usuario, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String login = campos[3];
                String pass = campos[1];
                String tipoAcceso = campos[2];
                if (login.equals(login_usuario) && pass.equals(password)) {
                    return (tipoAcceso.equals("1")) ? "admin" : "empleado";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al leer el archivo usuarios.txt");
        }
        return null;

    }

    @Override
    public void save(Object data) {
        // Implementar la lógica para guardar un nuevo registro en el modelo
        if (data instanceof Usuarios) {
            Usuarios usuario = (Usuarios) data;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
                writer.write(usuario.getLoginUsuario() + ";"
                        + usuario.getPassUsuario() + ";"
                        + usuario.getNivelAcceso() + ";"
                        + usuario.getNombreUsuario() + ";"
                        + usuario.getApellidosUsuarios() + ";"
                        + usuario.getEmailUsuario());
                writer.newLine();
                writer.flush(); // Escribir los cambios en el archivo
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al guardar el usuario en el archivo usuarios.txt");
            }
        } else {
            System.err.println("El objeto data no es una instancia de Usuarios");
        }
    }

    @Override
    public void list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
