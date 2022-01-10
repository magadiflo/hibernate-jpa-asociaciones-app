package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        /**
         * FETCH, en la consulta lo que hace es poblar direcciones en el objeto cliente
         */
        Cliente cliente = em.createQuery("SELECT c FROM Cliente c LEFT JOIN FETCH c.direcciones LEFT JOIN FETCH c.clienteDetalle WHERE c.id = :id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println("Cliente: " + cliente.getNombre() + ", direcciones: " + cliente.getDirecciones());


        em.close();
    }
}
