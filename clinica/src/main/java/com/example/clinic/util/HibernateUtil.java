package com.example.clinic.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



public class HibernateUtil {
    private final  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicPU");
    
        public static EntityManager getEntityManager() {
            return emf.createEntityManager();
        }
    
        public static void shutdown() {
                emf.close();
        }
    }
