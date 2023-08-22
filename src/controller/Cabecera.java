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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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
                writer.write(cabecera.getNroDocu() + ";"
                        + cabecera.getFechaDocu() + ";"
                        + cabecera.getHoraDocu() + ";"
                        + cabecera.getTipoDocu() + ";"
                        + cabecera.getDescripcionDocu() + ";"
                        + cabecera.getHechoPor() + ";"
                        + cabecera.getMontoTransaccion() + ";"
                        + cabecera.getFechaActualizacion() + ";"
                        + cabecera.isStatusActualizacion()
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

    public CabeceraTransaccion obtenerCabeceraPorNumero(String numeroDocumento) throws ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                if (campos[0].equalsIgnoreCase(numeroDocumento)) {
                    CabeceraTransaccion cabecera = new CabeceraTransaccion();
                    cabecera.setNroDocu(campos[0]);
                    cabecera.setFechaDocu(LocalDate.parse(campos[1])); 
                    cabecera.setHoraDocu(LocalTime.parse(campos[2]));
                    cabecera.setTipoDocu(Integer.parseInt(campos[3]));
                    cabecera.setDescripcionDocu(campos[4]);
                    cabecera.setHechoPor(campos[5]);
                    cabecera.setMontoTransaccion(Double.parseDouble(campos[6]));

                    // Parsea la fecha de actualización si está
                    if (campos.length > 5 && !campos[7].equals("null")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según tu archivo
                        try {
                            Date fechaActualizacion = dateFormat.parse(campos[7]);
                            cabecera.setFechaActualizacion(fechaActualizacion);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            System.err.println("No se pudo ajusta el formato de la fechaDocumento");
                            // Manejo de errores si la fecha no se puede analizar correctamente
                        }
                        cabecera.setStatusActualizacion(Boolean.parseBoolean(campos[7]));
                    }
                    
                    return cabecera;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de transacciones", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Número de documento no encontrado
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
