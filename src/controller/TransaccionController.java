/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import model.TransaccionContable;

/**
 *
 * @author Admin
 */
public class TransaccionController implements Controller {

    private static final String RUTA_ARCHIVO = "src/database/transacciones.txt";

    public TransaccionController() {
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
                JOptionPane.showMessageDialog(null, "Error al crear el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public boolean save(Object data) {
        if (data instanceof TransaccionContable) {
            TransaccionContable transaccion = (TransaccionContable) data;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
                writer.write(transaccion.getNro_doc() + ";"
                        + transaccion.getSecuencia_doc() + ";"
                        + transaccion.getCuenta_contable() + ";"
                        + transaccion.getValor_debito() + ";"
                        + transaccion.getValor_credito() + ";"
                        + transaccion.getComentario()
                );
                writer.newLine();
                writer.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de TransaccionContable");
        }
        return false;
    }

    @Override
    public List<String[]> list() {
        List<String[]> listaTransacciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                listaTransacciones.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return listaTransacciones;
    }

    public List<TransaccionContable> obtenerTransaccionesPorNumeroDocumento(String nroDoc) {
        List<TransaccionContable> transacciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String nroDocActual = campos[0];
                if (nroDocActual.equals(nroDoc)) {
                    TransaccionContable transaccion = new TransaccionContable();
                    transaccion.setNro_doc(campos[0]);
                    transaccion.setSecuencia_doc(Integer.parseInt(campos[1]));
                    transaccion.setCuenta_contable(Integer.parseInt(campos[2]));
                    transaccion.setValor_debito(Double.parseDouble(campos[3]));
                    transaccion.setValor_credito(Double.parseDouble(campos[4]));
                    if(campos.length > 4){
                        transaccion.setComentario(campos[5]);
                    }
                    
                    transacciones.add(transaccion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error al leer el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return transacciones;
    }

    @Override
    public boolean update(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
