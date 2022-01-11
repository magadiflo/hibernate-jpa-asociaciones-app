package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        /**
         * Recordando que FETCH, lo usamos para poblar las direcciones (..FETCH c.direcciones) y
         * para poblar los clienteDetalle (..FETCH c.clienteDetalle)
         */
        List<Cliente> clientes = em.createQuery("SELECT DISTINCT c FROM Cliente c LEFT OUTER JOIN FETCH c.direcciones LEFT OUTER JOIN FETCH c.clienteDetalle", Cliente.class).getResultList();
        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));
        em.close();
    }
}
