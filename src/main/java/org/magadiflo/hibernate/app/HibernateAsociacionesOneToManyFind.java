package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.Direccion;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToManyFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);

            Direccion d1 = new Direccion("Las Casuarinas", 33);
            Direccion d2 = new Direccion("Cerro Partido", 20);
            Direccion d3 = new Direccion("Trujillo", 150);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);
            cliente.getDirecciones().add(d3);

            em.merge(cliente);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().begin();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
