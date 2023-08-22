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
                StringBuilder line = new StringBuilder();
                line.append(transaccion.getNro_doc()).append(";")
                        .append(transaccion.getSecuencia_doc()).append(";")
                        .append(transaccion.getCuenta_contable()).append(";")
                        .append(transaccion.getValor_debito()).append(";")
                        .append(transaccion.getValor_credito()).append(";");

                if (transaccion.getComentario() != null) {
                    line.append(transaccion.getComentario());
                } else {
                    line.append("null");
                }

                writer.write(line.toString());
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
                    if (campos.length > 5) {
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
        if (data instanceof TransaccionContable) {
            TransaccionContable transaccion = (TransaccionContable) data;
            List<String[]> transaccionesList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(";");
                    String nroDoc = campos[0];
                    int secuenciaDoc = Integer.parseInt(campos[1]);
                    if (nroDoc.equals(transaccion.getNro_doc()) && secuenciaDoc == transaccion.getSecuencia_doc()) {
                        campos[2] = String.valueOf(transaccion.getCuenta_contable());
                        campos[3] = String.valueOf(transaccion.getValor_debito());
                        campos[4] = String.valueOf(transaccion.getValor_credito());                        
                        campos[5] = transaccion.getComentario();
                    }
                    transaccionesList.add(campos);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String[] campos : transaccionesList) {
                    writer.write(String.join(";", campos));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de TransaccionContable");
        }
        return false;
    }

    public boolean existeTransaccion(String nroDoc, int secuencia) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String nroDocActual = campos[0];
                int secuenciaActual = Integer.parseInt(campos[1]);
                if (nroDocActual.equals(nroDoc) && secuenciaActual == secuencia) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
