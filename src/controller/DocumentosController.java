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
import model.Documentos;

/**
 *
 * @author Zoila López
 */
public class DocumentosController implements Controller {

    private static final String RUTA_ARCHIVO = "src/database/documentos.txt";

    public DocumentosController() {
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
                JOptionPane.showMessageDialog(null, "Error al crear el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public boolean save(Object data) {
        if (data instanceof Documentos) {
            Documentos tipoDocumento = (Documentos) data;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
                writer.write(tipoDocumento.getCodigo() + ";" + tipoDocumento.getDescripcion());
                writer.newLine();
                writer.flush();
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
        List<String[]> listaDocumentos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                listaDocumentos.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return listaDocumentos;
    }

    @Override
    public boolean update(Object data) {
        if (data instanceof Documentos) {
            Documentos documento = (Documentos) data;
            List<String[]> documentosList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(";");
                    String codigoDocumento = campos[0];
                    if (codigoDocumento.equals(String.valueOf(documento.getCodigo()))) {
                        campos[1] = documento.getDescripcion();
                    }
                    documentosList.add(campos);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String[] campos : documentosList) {
                    writer.write(String.join(";", campos));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de Documentos");
        }
        return false;
    }

    @Override
    public boolean delete(Object data) {

        if (data instanceof Documentos) {
            Documentos documento = (Documentos) data;
            List<String[]> documentosList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(";");
                    String codigo = campos[0];
                    if (!codigo.equals(String.valueOf(documento.getCodigo()))) {
                        documentosList.add(campos);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String[] campos : documentosList) {
                    writer.write(String.join(";", campos));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return false;
    }

    public boolean existeCodigo(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String codigo1 = campos[0];
                if (codigo1.equals(codigo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo codigo.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Documentos buscarDocumento(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String codigo1 = campos[0];
                if (codigo1.equals(codigo)) {
                    Documentos documento = new Documentos(Integer.parseInt(campos[0]), campos[1]);
                    return documento;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo documentos.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Usuario no encontrado
    }

}
