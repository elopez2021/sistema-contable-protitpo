/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CabeceraTransaccion;
import model.CatalogoCuenta;
import model.TransaccionContable;

/**
 *
 * @author Zoila López
 */
public class ProcesoCierreDiarioController {

    private static final String RUTA_ARCHIVO_CABECERAS = "src/database/cabeceratransacciones.txt";
    TransaccionController transaccionCtrl = new TransaccionController();
    CatalogoController catalogoCtrl = new CatalogoController();
    Cabecera cabeceraController = new Cabecera();

    // Método para realizar el cierre diario por fechas
    public boolean realizarCierreDiario(LocalDate fechaInicio, LocalDate fechaFin) {

        // Obtener transacciones dentro del rango de fechas
        List<CabeceraTransaccion> cabeceras = obtenerCabecerasPorRangoDeFechas(fechaInicio, fechaFin);
        if (cabeceras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No existe ninguna transaccion en ese rango de fecha", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        for (CabeceraTransaccion cabecera : cabeceras) {
            //Obtener todas las transacciones de esa cabecera
            List<TransaccionContable> transacciones = transaccionCtrl.obtenerTransaccionesPorNumeroDocumento(cabecera.getNroDocu());
            // Actualizar cuentas
            transacciones.forEach((transaccion) -> {
                actualizarCuentas(transaccion);
            });

            //actualizar el status de la cuenta y la fecha
            cabecera.setFechaActualizacion(LocalDate.now());
            cabecera.setStatusActualizacion(true);
            cabeceraController.update(cabecera);

        }
        return true;

    }

    public List<CabeceraTransaccion> obtenerCabecerasPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<CabeceraTransaccion> cabecerasFiltradas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO_CABECERAS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                LocalDate fechaTransaccion = LocalDate.parse(campos[1]); // Campo de fecha de creación en CabeceraTransaccion

                if ((fechaTransaccion.isEqual(fechaInicio) || fechaTransaccion.isAfter(fechaInicio)) && (fechaTransaccion.isBefore(fechaFin) || fechaTransaccion.isEqual(fechaFin))) {
                    CabeceraTransaccion cabecera = new CabeceraTransaccion();
                    cabecera.setNroDocu(campos[0]);
                    cabecera.setFechaDocu(LocalDate.parse(campos[1]));
                    cabecera.setHoraDocu(LocalTime.parse(campos[2]));
                    cabecera.setTipoDocu(Integer.parseInt(campos[3]));
                    cabecera.setDescripcionDocu(campos[4]);
                    cabecera.setHechoPor(campos[5]);
                    cabecera.setMontoTransaccion(Double.parseDouble(campos[6]));
                    //cabecera.setFechaActualizacion(LocalDate.parse(campos[7]));
                    cabecera.setStatusActualizacion(Boolean.parseBoolean(campos[8]));
                    cabecerasFiltradas.add(cabecera);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo cabeceratransacciones.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return cabecerasFiltradas;
    }

    private void actualizarCuentasPadres(CatalogoCuenta cuentaContable, double valorDebito, double valorCredito) {
        Integer cuentaPadre = cuentaContable.getCta_padre();

        if (cuentaPadre == null) {
            return;
        }

        while (cuentaPadre != 0) {
            CatalogoCuenta cuenta = catalogoCtrl.buscarCuenta(String.valueOf(cuentaPadre));

            if (cuenta != null) {
                if (cuentaContable.getGrupo_cta() == 1) { // == TipoCuenta.DEBITO
                    cuenta.setDebito_acum_cta(cuenta.getDebito_acum_cta() + valorDebito);
                } else if (cuentaContable.getGrupo_cta() == 2) { //TipoCuenta.CREDITO
                    cuenta.setCredito_acum_cta(cuenta.getCredito_acum_cta() + valorCredito);
                }

                cuenta.setBalance_cta(cuenta.getDebito_acum_cta() - cuenta.getCredito_acum_cta());

                // Actualizar la cuenta padre en el archivo de catálogo de cuentas
                catalogoCtrl.update(cuenta);

                // Obtener el siguiente nivel de cuenta padre
                cuentaPadre = cuenta.getCta_padre();
                if (cuentaPadre == null) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void actualizarCuentas(TransaccionContable transaccion) {
        CatalogoCuenta cuentaContable = catalogoCtrl.buscarCuenta(String.valueOf(transaccion.getCuenta_contable()));
        if (cuentaContable != null) {
            double valorDebito = transaccion.getValor_debito();
            double valorCredito = transaccion.getValor_credito();

            if (cuentaContable.getGrupo_cta() == 1) { // == TipoCuenta.DEBITO
                cuentaContable.setDebito_acum_cta(cuentaContable.getDebito_acum_cta() + valorDebito);
            } else if (cuentaContable.getGrupo_cta() == 2) { // TipoCuenta.CREDITO
                cuentaContable.setCredito_acum_cta(cuentaContable.getCredito_acum_cta() + valorCredito);
            }

            cuentaContable.setBalance_cta(cuentaContable.getDebito_acum_cta() - cuentaContable.getCredito_acum_cta());

            actualizarCuentasPadres(cuentaContable, valorDebito, valorCredito);

            // Actualizar la cuenta en el archivo de catálogo de cuentas
            catalogoCtrl.update(cuentaContable);

        }
    }

}
