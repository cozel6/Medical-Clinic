package com.example.clinic.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class GenericDAO<T, K> {

    protected final EntityManager em;
    private final Class<T> clazz;

    public GenericDAO(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
    }

    public void save(T ent) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(ent);
        tx.commit();
    }

    public T find(K id) {
        return em.find(clazz, id);
    }

    public List<T> findAll(){
        return em.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    public void update(T ent){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(ent);
        tx.commit();
    }

    public void delete(T ent){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(ent) ? ent : em.merge(ent));
        tx.commit();
    }
}
