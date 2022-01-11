package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Alumno;
import org.magadiflo.hibernate.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        /**
         * FETCH, recordar que la palabra FETCH es importante que vaya en la consulta ya que sino no va a poblar
         * los cursos de los alumnos
         */
        List<Alumno> alumnos = em.createQuery("SELECT DISTINCT a FROM Alumno a LEFT OUTER JOIN FETCH a.cursos", Alumno.class).getResultList();
        alumnos.forEach(a -> System.out.println(a.getNombre() + ", cursos: " + a.getCursos()));

        em.close();
    }
}
