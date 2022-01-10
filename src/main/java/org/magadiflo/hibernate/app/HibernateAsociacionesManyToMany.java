package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Alumno;
import org.magadiflo.hibernate.app.entity.Curso;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Alumno a1 = new Alumno("Liz", "Gonzales");
            Alumno a2 = new Alumno("Wilachis", "Mauriola");

            Curso c1 = new Curso("Matemática Aplicada", "Caselli Gismondi");
            Curso c2 = new Curso("Lógica de programación", "Manco Pulido");

            a1.getCursos().add(c1);
            a1.getCursos().add(c2);

            a2.getCursos().add(c1);

            em.persist(a1);
            em.persist(a2);
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
