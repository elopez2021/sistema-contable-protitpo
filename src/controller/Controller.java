/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;

/**
 *
 * @author Zoila López
 */
public interface Controller {

    void init();
    // Método para guardar un nuevo registro en el modelo

    boolean save(Object data);

    //Metodo para obtener todos los registros
    List<String[]> list();

    // Método para actualizar un registro existente en el modelo
    boolean update(Object data);

    // Método para eliminar un registro del modelo
    boolean delete(Object data);
}
