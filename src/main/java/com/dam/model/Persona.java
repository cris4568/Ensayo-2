/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author Cris     
 */
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false, unique = true)
    private String nombreUsuario;
    @Column(nullable = false)
    private String contraseña;
    @Column(nullable = false, unique = true)
    private String correoElectronico;
    @Column(nullable = false)
    private LocalDateTime ultimaSesion;

    
    @OneToMany(mappedBy = "autor",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Libro> librosEscritos;

    @ManyToMany(mappedBy = "lectores",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Libro> librosLeidos;

    @OneToMany(mappedBy = "persona",cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Comentario> comentarios;


    public Persona(Long id, String nombre, String nombreUsuario, String contraseña, String correoElectronico, LocalDateTime ultimaSesion, Set<Libro> librosEscritos, Set<Libro> librosLeidos, Set<Comentario> comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.correoElectronico = correoElectronico;
        this.ultimaSesion = ultimaSesion;
        this.librosEscritos = librosEscritos;
        this.librosLeidos = librosLeidos;
        this.comentarios = comentarios;
    }

    public Persona() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Set<Libro> getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(Set<Libro> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }

    public Set<Libro> getLibrosLeidos() {
        return librosLeidos;
    }

    public void setLibrosLeidos(Set<Libro> librosLeidos) {
        this.librosLeidos = librosLeidos;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public LocalDateTime getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(LocalDateTime ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

   
}
