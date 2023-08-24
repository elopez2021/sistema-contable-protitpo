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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import model.CatalogoCuenta;

/**
 *
 * @author Zoila López
 */
public class CatalogoController implements Controller {

    private static final String RUTA_ARCHIVO = "src/database/catalogo.txt";

    public CatalogoController() {
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
                JOptionPane.showMessageDialog(null, "Error al crear el archivo catalogo.txt", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public boolean save(Object data) {
        if (data instanceof CatalogoCuenta) {
            CatalogoCuenta cuenta = (CatalogoCuenta) data;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
                writer.write(cuenta.getNro_cta() + ";"
                        + cuenta.getDescripcion_cta() + ";"
                        + cuenta.isTipo_cta() + ";"
                        + cuenta.getNivel_cta() + ";"
                        + cuenta.getCta_padre() + ";"
                        + cuenta.getGrupo_cta() + ";"
                        + cuenta.getFecha_creacion_cta() + ";"
                        + cuenta.getHora_creacion_cta() + ";"
                        + cuenta.getDebito_acum_cta() + ";"
                        + cuenta.getCredito_acum_cta() + ";"
                        + cuenta.getBalance_cta());
                writer.newLine();
                writer.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de CatalogoCuenta");
            return false;
        }
    }

    public boolean existeCuenta(String cuenta) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String codigo = campos[0];
                if (codigo.equals(cuenta)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo catalogo.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public CatalogoCuenta buscarCuenta(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String codigo1 = campos[0];
                if (codigo1.equals(codigo)) {
                    CatalogoCuenta cuenta = new CatalogoCuenta(Integer.parseInt(campos[0]), campos[1], Boolean.parseBoolean(campos[2]),
                            Integer.parseInt(campos[3]), Integer.parseInt(campos[4]), Integer.parseInt(campos[5]),
                            LocalDate.parse(campos[6]), LocalTime.parse(campos[7]), Double.parseDouble(campos[8]), Double.parseDouble(campos[9]),
                            Double.parseDouble(campos[10]));
                    return cuenta;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo catalogo.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public List<CatalogoCuenta> obtenerCatalogoCuentas(CatalogoCuenta filtro) {
        List<CatalogoCuenta> cuentasFiltradas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                // Crea una nueva instancia de CatalogoCuenta y asigna los valores
                CatalogoCuenta cuenta = new CatalogoCuenta();
                cuenta.setNro_cta(Integer.parseInt(campos[0]));
                cuenta.setDescripcion_cta(campos[1]);
                cuenta.setTipo_cta(Boolean.parseBoolean(campos[2]));
                cuenta.setNivel_cta(Integer.parseInt(campos[3]));
                cuenta.setCta_padre(campos[4].equals("null") ? null : Integer.parseInt(campos[4]));
                cuenta.setGrupo_cta(Integer.parseInt(campos[5]));
                cuenta.setFecha_creacion_cta(LocalDate.parse(campos[6]));
                cuenta.setHora_creacion_cta(LocalTime.parse(campos[7]));
                cuenta.setDebito_acum_cta(Double.parseDouble(campos[8]));
                cuenta.setCredito_acum_cta(Double.parseDouble(campos[9]));
                cuenta.setBalance_cta(Double.parseDouble(campos[10]));

                // Aplica los filtros
                boolean cumpleFiltros = true;

                if (filtro.getNro_cta() != -1 && filtro.getNro_cta() != cuenta.getNro_cta()) {
                    cumpleFiltros = false;
                }
                if (filtro.getDescripcion_cta() != null && !filtro.getDescripcion_cta().equals(cuenta.getDescripcion_cta())) {
                    cumpleFiltros = false;
                }
                if (filtro.isTipo_cta() != null && !filtro.isTipo_cta().equals(cuenta.isTipo_cta())) {
                    cumpleFiltros = false;
                }

                if (filtro.getNivel_cta() != -1 && filtro.getNivel_cta() != cuenta.getNivel_cta()) {
                    cumpleFiltros = false;
                }
                if (filtro.getCta_padre() != -1 && filtro.getCta_padre() != cuenta.getCta_padre()) {
                    cumpleFiltros = false;
                }
                if (filtro.getGrupo_cta() != -1 && filtro.getGrupo_cta() != cuenta.getGrupo_cta()) {
                    cumpleFiltros = false;
                }

                if (cumpleFiltros) {
                    cuentasFiltradas.add(cuenta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cuentasFiltradas;
    }

    @Override
    public List<String[]> list() {
        List<String[]> listaCatalogo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                listaCatalogo.add(campos);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo del catálogo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return listaCatalogo;
    }

    @Override
    public boolean update(Object data) {
        if (data instanceof CatalogoCuenta) {
            CatalogoCuenta cuenta = (CatalogoCuenta) data;
            List<String[]> cuentasList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] campos = line.split(";");
                    int nroCta = Integer.parseInt(campos[0]);
                    if (nroCta == cuenta.getNro_cta()) {
                        campos[1] = cuenta.getDescripcion_cta();
                        campos[2] = String.valueOf(cuenta.isTipo_cta());
                        campos[3] = String.valueOf(cuenta.getNivel_cta());
                        campos[4] = String.valueOf(cuenta.getCta_padre());
                        campos[5] = String.valueOf(cuenta.getGrupo_cta());
                        campos[8] = String.valueOf(cuenta.getDebito_acum_cta());
                        campos[9] = String.valueOf(cuenta.getCredito_acum_cta());
                        campos[10] = String.valueOf(cuenta.getBalance_cta());
                    }
                    cuentasList.add(campos);
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer el archivo catalogo_cuentas.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
                for (String[] campos : cuentasList) {
                    writer.write(String.join(";", campos));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo catalogo_cuentas.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            System.err.println("El objeto data no es una instancia de CatalogoCuenta");
        }
        return false;
    }
    
    public String[] obtenerNumerosCuentasDetalles() {
        List<Integer> numerosCuentasGenerales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                CatalogoCuenta cuenta = new CatalogoCuenta();
                cuenta.setNro_cta(Integer.parseInt(campos[0]));
                cuenta.setDescripcion_cta(campos[1]);
                cuenta.setTipo_cta(Boolean.parseBoolean(campos[2]));
                cuenta.setNivel_cta(Integer.parseInt(campos[3]));
                cuenta.setCta_padre(Integer.parseInt(campos[4]));
                cuenta.setGrupo_cta(Integer.parseInt(campos[5]));
                cuenta.setFecha_creacion_cta(LocalDate.parse(campos[6]));
                cuenta.setHora_creacion_cta(LocalTime.parse(campos[7]));
                cuenta.setDebito_acum_cta(Double.parseDouble(campos[8]));
                cuenta.setCredito_acum_cta(Double.parseDouble(campos[9]));
                cuenta.setBalance_cta(Double.parseDouble(campos[10]));

                if (!cuenta.isTipo_cta()) { // Si es cuenta a detalle
                    numerosCuentasGenerales.add(cuenta.getNro_cta());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer el archivo cuentas.txt", "Error", JOptionPane.ERROR_MESSAGE);
        }

        String[] resultados = new String[numerosCuentasGenerales.size()];
        
        for (int i = 0; i < numerosCuentasGenerales.size(); i++) {
            resultados[i] = String.valueOf(numerosCuentasGenerales.get(i));
        }

        return resultados;
    }
    
    public String encontrarDescripcionCuentaDetalle(int numeroCuenta) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(";");
                String descripcionCuenta = campos[1];
                if(String.valueOf(numeroCuenta).equals(campos[0]) && campos[2].equals("false")){
                    return descripcionCuenta;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores aquí
        }
        return null;
    }

}