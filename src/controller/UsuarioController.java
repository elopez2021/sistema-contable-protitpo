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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuarios;

/**
 *
 * @author Zoila López
 */
public class UsuarioController implements Controller {

    private static final String RUTA_ARCHIVO = "src/database/usuarios.txt";

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
                JOptionPane.showMessageDialog(null, "Error al crear el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para verificar las credenciales del usuario (login)
    public static boolean existeUsuario(String login_usuario, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String login = campos[0];
                String pass = campos[1];
                if (login.equals(login_usuario) && pass.equals(password)) {
                    return true; // Credenciales válidas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Credenciales incorrectas o usuario no encontrado
    }

    public static boolean existeLogin(String login_usuario) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String login = campos[0];
                if (login.equals(login_usuario)) {
                    return true; // Credenciales válidas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Credenciales incorrectas o usuario no encontrado
    }

    public Usuarios buscarUsuario(String login) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String loginUsuario = campos[0];
                if (loginUsuario.equals(login)) {
                    Usuarios usuario = new Usuarios();
                    usuario.setLoginUsuario(campos[0]);
                    usuario.setPassUsuario(campos[1]);
                    usuario.setNivelAcceso(Integer.parseInt(campos[2]));
                    usuario.setNombreUsuario(campos[3]);
                    usuario.setApellidosUsuarios(campos[4]);
                    usuario.setEmailUsuario(campos[5]);
                    return usuario;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Usuario no encontrado
    }

    // Método para obtener el tipo de acceso desde el archivo acceso.txt
    public static String obtenerTipoAcceso(String login_usuario, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String login = campos[0];
                String pass = campos[1];
                String tipoAcceso = campos[2];
                if (login.equals(login_usuario) && pass.equals(password)) {
                    return (tipoAcceso.equals("1")) ? "admin" : "empleado";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }

    @Override
    public boolean save(Object data) {
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
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de Usuarios");
        }
        return false;
    }

    @Override
    public List<String[]> list() {
        List<String[]> listaUsuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                listaUsuarios.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return listaUsuarios;

    }

    @Override
    public boolean update(Object data) {

        if (data instanceof Usuarios) {
            Usuarios usuario = (Usuarios) data;
            List<String[]> usuariosList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(";");
                    String loginUsuario = campos[0];
                    if (loginUsuario.equals(usuario.getLoginUsuario())) {
                        campos[1] = usuario.getPassUsuario();
                        campos[2] = String.valueOf(usuario.getNivelAcceso());
                        campos[3] = usuario.getNombreUsuario();
                        campos[4] = usuario.getApellidosUsuarios();
                        campos[5] = usuario.getEmailUsuario();
                    }
                    usuariosList.add(campos);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String[] campos : usuariosList) {
                    writer.write(String.join(";", campos));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else {
            System.err.println("El objeto data no es una instancia de Usuarios");
        }
        return false;
    }

    @Override
    public boolean delete(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean verificarEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String emailusuario = campos[5];
                if (emailusuario.equals(email)) {
                    return true; // email existe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo usuarios.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Email no encontrado
    }

}
