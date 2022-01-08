package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.ClienteDetalle;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre("Eva");
            cliente.setApellido("Calderón");
            cliente.setFormaPago("paypal");

            ClienteDetalle detalle = new ClienteDetalle(true, 150L);

//            cliente.setClienteDetalle(detalle);
//            detalle.setCliente(cliente);
            cliente.addDetalle(detalle);

            em.persist(cliente);

            em.getTransaction().commit();
            System.out.println("Cliente guardado: " + cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
