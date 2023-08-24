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
import java.util.ArrayList;
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
                        cabecera.setFechaActualizacion(LocalDate.parse(campos[7]));
                    }
                    cabecera.setStatusActualizacion(Boolean.parseBoolean(campos[8]));

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
        if (data instanceof CabeceraTransaccion) {
            CabeceraTransaccion cabecera = (CabeceraTransaccion) data;
            List<String[]> cabeceraList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(";");
                    String nroDoc = campos[0];
                    if (nroDoc.equals(cabecera.getNroDocu())) {
                        String fechaActualizacionStr = (cabecera.getFechaActualizacion() != null)
                                ? cabecera.getFechaActualizacion().toString()
                                : "null";
                        campos[1] = cabecera.getFechaDocu().toString();
                        campos[2] = cabecera.getHoraDocu().toString();
                        campos[3] = String.valueOf(cabecera.getTipoDocu());
                        campos[4] = cabecera.getDescripcionDocu();
                        campos[5] = cabecera.getHechoPor();
                        campos[6] = String.valueOf(cabecera.getMontoTransaccion());
                        campos[7] = fechaActualizacionStr;
                        campos[8] = String.valueOf(cabecera.isStatusActualizacion());
                    }
                    cabeceraList.add(campos);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer el archivo cabecera_transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String[] campos : cabeceraList) {
                    writer.write(String.join(";", campos));
                    writer.newLine();
                }
                writer.flush(); // Make sure to flush the writer
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo cabeceratransacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de CabeceraTransaccion");
        }
        return false;
    }
    private List<CabeceraTransaccion> obtenerCabecerasPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
    List<CabeceraTransaccion> cabecerasFiltradas = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(";");
            LocalDate fechaTransaccion = LocalDate.parse(campos[1]); // Campo de fecha de creación en CabeceraTransaccion
            
            if ((fechaTransaccion.isEqual(fechaInicio) || fechaTransaccion.isAfter(fechaInicio)) && (fechaTransaccion.isBefore(fechaFin) || fechaTransaccion.isEqual(fechaFin))){
                CabeceraTransaccion cabecera = new CabeceraTransaccion();
                cabecera.setNroDocu(campos[0]);
                cabecera.setFechaDocu(LocalDate.parse(campos[1])); // Ajustar el índice según tus datos
                cabecera.setTipoDocu(Integer.parseInt(campos[2])); // Ajustar el índice según tus datos
                cabecera.setDescripcionDocu(campos[3]); // Ajustar el índice según tus datos
                cabecera.setHechoPor(campos[4]); // Ajustar el índice según tus datos
                cabecera.setMontoTransaccion(Double.parseDouble(campos[5])); // Ajustar el índice según tus datos
                cabecera.setFechaActualizacion(LocalDate.parse(campos[6])); // Ajustar el índice según tus datos
                cabecera.setStatusActualizacion(Boolean.parseBoolean(campos[7])); // Ajustar el índice según tus datos
                cabecerasFiltradas.add(cabecera);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al leer el archivo cabecera_transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return cabecerasFiltradas;
}

}
