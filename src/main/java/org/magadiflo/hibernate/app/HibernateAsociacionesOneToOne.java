package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.ClienteDetalle;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToOne {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre("Adán");
            cliente.setApellido("Díaz");
            cliente.setFormaPago("credito");
            em.persist(cliente);

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);

            cliente.setClienteDetalle(detalle);

            em.getTransaction().commit();
            System.out.println("Detalle guardado: " + detalle);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
