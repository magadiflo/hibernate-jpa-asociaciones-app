package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Alumno;
import org.magadiflo.hibernate.app.entity.Curso;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesManyToManyFindBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Alumno a1 = em.find(Alumno.class, 1L);
            Alumno a2 = em.find(Alumno.class, 2L);

            Curso c1 = em.find(Curso.class, 1L);//new Curso("Matemática Aplicada", "Caselli Gismondi");
            Curso c2 = em.find(Curso.class, 2L);//new Curso("Lógica de programación", "Manco Pulido");

            a1.addCurso(c1);
            a1.addCurso(c2);

            a2.addCurso(c1);

            em.getTransaction().commit();

            System.out.println(a1);
            System.out.println(a2);

            em.getTransaction().begin();

            Curso c3 = new Curso();
            c3.setId(2L);

            a1.removeCurso(c3);
            em.getTransaction().commit();
            System.out.println("Luego de eliminar curso del alumno: " + a1);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
