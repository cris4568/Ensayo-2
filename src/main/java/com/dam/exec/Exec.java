/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.exec;

import com.dam.DAO.LibroDAO;
import com.dam.DAO.LibroDAOImpl;
import com.dam.DAO.PersonaDAO;
import com.dam.DAO.PersonaDAOImpl;
import com.dam.model.Comentario;
import com.dam.model.Libro;
import com.dam.model.Persona;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Cris
 */
public class Exec {

    public static void main(String[] args) {
        PersonaDAO personaDAO = new PersonaDAOImpl();
        LibroDAO libroDAO = new LibroDAOImpl();

        // Crear personas
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Pepe");
        nuevaPersona.setNombreUsuario("pepeElMejor");
        nuevaPersona.setContraseña("LaMejorContrasña");
        nuevaPersona.setCorreoElectronico("pepe@gmail.com");
        nuevaPersona.setUltimaSesion(LocalDateTime.now());
        personaDAO.createPersona(nuevaPersona);

        Persona persona2 = new Persona();
        persona2.setNombre("Marcos");
        persona2.setNombreUsuario("MarcosElPana");
        persona2.setContraseña("Marcos");
        persona2.setCorreoElectronico("marcos@gmail.com");
        persona2.setUltimaSesion(LocalDateTime.now());
        personaDAO.createPersona(persona2);

        // Crear libros
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo("El libro de la vida");
        nuevoLibro.setNumeroPaginas(200);
        nuevoLibro.setAutor(nuevaPersona);
        libroDAO.addLibro(nuevoLibro);

        Libro libro2 = new Libro();
        libro2.setTitulo("El que escribí borracho");
        libro2.setNumeroPaginas(400);
        libro2.setAutor(nuevaPersona);
        libroDAO.addLibro(libro2);

        Libro libro3 = new Libro();
        libro3.setTitulo("Marcos, yo");
        libro3.setNumeroPaginas(300);
        libro3.setAutor(persona2);
        libroDAO.addLibro(libro3);

        // Hacer login
        Persona usuario = personaDAO.obtenerPersonaPorUsuarioYContraseña("pepeElMejor", "LaMejorContrasña");
        // Actualizar la última vez que el usuario hizo login
        usuario.setUltimaSesion(LocalDateTime.now());

        //Marcar libros como leídos
        Set<Libro> leidos1 = usuario.getLibrosLeidos();
        Set<Libro> leidos2 = usuario.getLibrosLeidos();
        if (leidos1 == null) {
            leidos1 = new HashSet<>();
        }
        if (leidos2 == null) {
            leidos2 = new HashSet<>();
        }
        leidos1.add(libro3);
        usuario.setLibrosLeidos(leidos1);
        leidos2.add(libro2);
        leidos2.add(nuevoLibro);
        persona2.setLibrosLeidos(leidos2);

        Set<Persona> lectores1 = nuevoLibro.getLectores();
        Set<Persona> lectores2 = nuevoLibro.getLectores();
        if (lectores1 == null) {
            lectores1 = new HashSet<>();
        }
        if (lectores2 == null) {
            lectores2 = new HashSet<>();
        }
        lectores1.add(persona2);
        nuevoLibro.setLectores(lectores1);
        libro2.setLectores(lectores1);

        lectores2.add(usuario);
        libro3.setLectores(lectores2);

        libroDAO.updateLibro(nuevoLibro);
        libroDAO.updateLibro(libro2);
        libroDAO.updateLibro(libro3);

        // Comentar y valorar un libro
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setTextoComentario("Un buen libro!");
        nuevoComentario.setValoracion(4);
        nuevoComentario.setPersona(usuario);
        nuevoComentario.setLibro(nuevoLibro);
        libroDAO.addComentario(nuevoComentario);

        personaDAO.updatePersona(nuevaPersona);
        System.out.println("--------------------------------------------");
        System.out.println("Libros leídos por " + usuario.getNombre() + ":");
        for (Libro libro : usuario.getLibrosLeidos()) {
            System.out.println(libro.getTitulo());
        }

        System.out.println("--------------------------------------------");
        System.out.println("Libros escritos por " + usuario.getNombre() + ":");
        for (Libro libro : usuario.getLibrosEscritos()) {
            System.out.println(libro.getTitulo());
        }

        System.out.println("--------------------------------------------");
        System.out.println("\nTodos los libros disponibles:");
        for (Libro libro : libroDAO.getAllLibros()) {
            System.out.println(libro.getTitulo());
        }

        System.out.println("--------------------------------------------");
        System.out.println("\nTodos los usuarios:");
        for (Persona p : personaDAO.getAllPersonas()) {
            System.out.println(p.getNombreUsuario());
        }

        System.out.println("--------------------------------------------");
        System.out.println("\nTodos los usuarios:");
        for (Persona p : personaDAO.getAllPersonas()) {
            System.out.println(p.getNombreUsuario());
        }

    }
}
