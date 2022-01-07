package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.Factura;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesManyToOne {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre("Gaspar");
            cliente.setApellido("Díaz");
            cliente.setFormaPago("credito");
            em.persist(cliente);

            Factura factura = new Factura("Compras de oficina", 5600L);
            factura.setCliente(cliente); //El cliente debe existir en la BD, sino debemos crearlo antes como en este ejemplo
            em.persist(factura);
            System.out.println(factura);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
