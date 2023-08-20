/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import model.CabeceraTransaccion;

/**
 *
 * @author Zoila López
 */
public class Cabecera implements Controller {
    private static final String RUTA_ARCHIVO = "src/database/cabeceratransacciones.txt";

    public Cabecera() {
        init();
    }

    @Override
    public void init() {
        File usuariosFile = new File(RUTA_ARCHIVO);
        if (!usuariosFile.exists()) {
            try {
                usuariosFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al crear el archivo cabeceratransacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public boolean save(Object data) {
        if (data instanceof CabeceraTransaccion) {
            CabeceraTransaccion cabecera = (CabeceraTransaccion) data;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
                writer.write(cabecera.getNroDocu() + ";" + 
                        cabecera.getFechaDocu() + ";" + 
                        cabecera.getTipoDocu() + ";" +                        
                        cabecera.getDescripcionDocu() + ";" +
                        cabecera.getHechoPor() + ";" + 
                        cabecera.getMontoTransaccion() + ";" + 
                        cabecera.getFechaActualizacion() + ";" + 
                        cabecera.isStatusActualizacion()
                );
                writer.newLine();
                writer.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de CabeceraTransaccion");
        }
        return false;
    }

    @Override
    public List<String[]> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
