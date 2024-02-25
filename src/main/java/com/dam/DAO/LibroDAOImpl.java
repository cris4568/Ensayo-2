/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.DAO;

import com.dam.model.Comentario;
import com.dam.model.Libro;
import com.dam.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Cris
 */
public class LibroDAOImpl implements LibroDAO {

    @Override
    public void addLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(libro);
            transaction.commit();
        } catch (HibernateError exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public List<Libro> getAllLibros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Libro> query = builder.createQuery(Libro.class);

            query.from(Libro.class);

            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void addComentario(Comentario comentario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(comentario);
            transaction.commit();
        } catch (HibernateError exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void updateLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {

                transaction.rollback();
            }
            System.err.println(e);
        }
    }

    @Override
    public void removeLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(libro);
            transaction.commit();
        } catch (HibernateError exception) {
            if (transaction != null) {

                transaction.rollback();

            }
            System.err.println(exception.getMessage());
        }
    }

}
