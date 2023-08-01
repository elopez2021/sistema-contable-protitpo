/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Zoila López
 */
public interface Controller {
     // Método para guardar un nuevo registro en el modelo
    void save(Object data);

    // Método para actualizar un registro existente en el modelo
    void update(Object data);

    // Método para eliminar un registro del modelo
    void delete(Object data);
}
