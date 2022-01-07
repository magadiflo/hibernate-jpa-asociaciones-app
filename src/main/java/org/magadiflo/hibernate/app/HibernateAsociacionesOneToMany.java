package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.Direccion;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre("Karen");
            cliente.setApellido("Caldas");

            Direccion d1 = new Direccion("San Pedro", 150);
            Direccion d2 = new Direccion("Chimbote", 450);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            em.persist(cliente);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().begin();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
