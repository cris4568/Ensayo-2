/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.DAO;


import com.dam.model.Persona;
import com.dam.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Cris
 */
public class PersonaDAOImpl implements PersonaDAO {

    @Override
    public Persona obtenerPersonaPorUsuarioYContraseña(String usuario, String contraseña) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Persona> query = session.createQuery(
                    "FROM Persona WHERE LOWER(nombreUsuario) = :usuario AND contraseña = :contraseña",
                    Persona.class
            );
            query.setParameter("usuario", usuario.toLowerCase());
            query.setParameter("contraseña", contraseña);

            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public void createPersona(Persona persona) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
            System.err.println(e);
        }
    }

    @Override
    public void updatePersona(Persona persona) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(persona);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();
            }
            System.err.println(e);
        }
    }

    @Override
    public void removePersona(Persona persona) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(persona);
            transaction.commit();
        } catch (HibernateError exception) {
            if (transaction != null) {

                transaction.rollback();

            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public List<Persona> getAllPersonas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Persona> query = builder.createQuery(Persona.class);

            query.from(Persona.class);

            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }


}
