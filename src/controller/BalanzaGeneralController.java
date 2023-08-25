/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import model.CatalogoCuenta;
import model.TransaccionContable;

/**
 *
 * @author Zoila López
 */
public class BalanzaGeneralController {

    private static final String RUTA_ARCHIVO_CATALOGO = "src/database/catalogo.txt";
    private static final String RUTA_ARCHIVO_TRANSACCIONES = "src/database/transacciones.txt";

    public String determinarTipoCuenta(int numeroCuenta) {
        String numeroCuentaStr = String.valueOf(numeroCuenta);
        char primerDigito = numeroCuentaStr.charAt(0);

        switch (primerDigito) {
            case '1':
                return "Activo";
            case '2':
                return "Pasivo";
            case '3':
                return "Capital";
            default:
                return "Desconocido";
        }
    }

    public Map<Integer, Double> calcularBalanzaGeneral(List<CatalogoCuenta> catalogo, List<TransaccionContable> transacciones) {
        Map<Integer, Double> balanzaGeneral = new HashMap<>();

        // Obtener los números de cuenta de activo, pasivo y capital
        Set<Integer> cuentasActivos = new HashSet<>();
        Set<Integer> cuentasPasivos = new HashSet<>();
        Set<Integer> cuentasCapital = new HashSet<>();

        for (CatalogoCuenta cuenta : catalogo) {
            String tipoCuenta = determinarTipoCuenta(cuenta.getNro_cta());
            if (tipoCuenta.equals("Activo")) {
                cuentasActivos.add(new Integer(cuenta.getNro_cta()));
            } else if (tipoCuenta.equals("Pasivo")) {
                cuentasPasivos.add(cuenta.getNro_cta());
            } else if (tipoCuenta.equals("Capital")) {
                cuentasCapital.add(cuenta.getNro_cta());
            }
        }

        // Calcular los saldos de las cuentas a partir de las transacciones
        for (TransaccionContable transaccion : transacciones) {
            Integer cuentaContable = transaccion.getCuenta_contable();

            if (cuentasActivos.contains(cuentaContable) || cuentasPasivos.contains(cuentaContable)
                    || cuentasCapital.contains(cuentaContable)) {
                double saldoActual = balanzaGeneral.getOrDefault(cuentaContable, 0.0);
                double debito = transaccion.getValor_debito();
                double credito = transaccion.getValor_credito();

                double nuevoSaldo = saldoActual + debito - credito;
                balanzaGeneral.put(cuentaContable, nuevoSaldo);
            }
        }

        return balanzaGeneral;
    }

    public List<CatalogoCuenta> obtenerCuentasCatalogo() {
        List<CatalogoCuenta> catalogo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_CATALOGO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                CatalogoCuenta cuenta = new CatalogoCuenta();
                cuenta.setNro_cta(Integer.parseInt(campos[0]));
                cuenta.setDescripcion_cta(campos[1]);
                cuenta.setTipo_cta(Boolean.parseBoolean(campos[2]));
                cuenta.setNivel_cta(Integer.parseInt(campos[3]));
                cuenta.setCta_padre(campos[4].equals("null") ? null : Integer.parseInt(campos[4]));
                cuenta.setGrupo_cta(Integer.parseInt(campos[5]));
                /*
                int grupoCta = Integer.parseInt(campos[5]);
                double debitoAcumCta = Double.parseDouble(campos[6]);
                double creditoAcumCta = Double.parseDouble(campos[7]);
                double balanceCta = Double.parseDouble(campos[8]);
                 */

                catalogo.add(cuenta);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo catalogo_cuentas.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return catalogo;
    }

    public List<TransaccionContable> obtenerTransacciones() {
        List<TransaccionContable> transacciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_TRANSACCIONES))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String nroDoc = campos[0];
                int secuenciaDoc = Integer.parseInt(campos[1]);
                int cuentaContable = Integer.parseInt(campos[2]);
                double valorDebito = Double.parseDouble(campos[3]);
                double valorCredito = Double.parseDouble(campos[4]);
                String comentario = campos[5];

                TransaccionContable transaccion = new TransaccionContable();
                transaccion.setNro_doc(nroDoc);
                transaccion.setSecuencia_doc(secuenciaDoc);
                transaccion.setCuenta_contable(cuentaContable);
                transaccion.setValor_debito(valorDebito);
                transaccion.setValor_credito(valorCredito);
                transaccion.setComentario(comentario);

                transacciones.add(transaccion);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo transacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return transacciones;
    }

}
