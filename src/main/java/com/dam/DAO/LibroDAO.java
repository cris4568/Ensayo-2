/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dam.DAO;

import com.dam.model.Comentario;
import com.dam.model.Libro;
import java.util.List;

/**
 *
 * @author Cris
 */
public interface LibroDAO {
    void addLibro(Libro libro);
    void updateLibro(Libro libro);
    void removeLibro(Libro libro);
    List<Libro> getAllLibros();
    void addComentario(Comentario comentario);
}
