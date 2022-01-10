package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Alumno;
import org.magadiflo.hibernate.app.entity.Curso;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesManyToManyFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Alumno a1 = em.find(Alumno.class, 1L);
            Alumno a2 = em.find(Alumno.class, 2L);

            Curso c1 = em.find(Curso.class, 1L);//new Curso("Matemática Aplicada", "Caselli Gismondi");
            Curso c2 = em.find(Curso.class, 2L);//new Curso("Lógica de programación", "Manco Pulido");

            a1.getCursos().add(c1);
            a1.getCursos().add(c2);

            a2.getCursos().add(c1);

            em.getTransaction().commit();

            System.out.println(a1);
            System.out.println(a2);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
