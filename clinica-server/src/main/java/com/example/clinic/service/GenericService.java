package com.example.clinic.service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class GenericService<T, K> {
    protected final EntityManager em;
    private final Class<T> clazz;

    protected GenericService(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
    }

    public T create(T entity) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(entity);
        tx.commit();
        return entity;
    }

    public T read(K id) {
        return em.find(clazz, id);
    }

    public List<T> readAll() {
        return em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz)
                 .getResultList();
    }

    public T update(T entity) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        T merged = em.merge(entity);
        tx.commit();
        return merged;
    }

    public void delete(T entity) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        tx.commit();
    }
}
