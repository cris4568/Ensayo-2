/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dam.DAO;

import com.dam.model.Persona;
import java.util.List;


/**
 *
 * @author Cris
 */
public interface PersonaDAO {
    Persona obtenerPersonaPorUsuarioYContraseña(String usuario, String contraseña);
    List<Persona> getAllPersonas();
    void createPersona(Persona persona);
    void updatePersona(Persona persona);
    void removePersona(Persona persona);

}
