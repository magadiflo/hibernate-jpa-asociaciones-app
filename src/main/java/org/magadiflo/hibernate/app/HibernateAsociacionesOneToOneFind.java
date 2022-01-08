package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.ClienteDetalle;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToOneFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);

            cliente.setClienteDetalle(detalle);

            em.getTransaction().commit();
            System.out.println("Cliente detalle guardado: " + cliente);
            System.out.println("cliente_detalle: " + cliente.getClienteDetalle());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
