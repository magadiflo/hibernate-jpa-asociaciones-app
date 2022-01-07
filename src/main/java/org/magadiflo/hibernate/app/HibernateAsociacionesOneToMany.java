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

            System.out.println("Cliente guardado: " + cliente);

            em.getTransaction().begin();

            //Suponiendo que es una nueva consulta, buscaremos nuevamente al mismo cliente
            cliente = em.find(Cliente.class, cliente.getId());
            cliente.getDirecciones().remove(d1);

            em.getTransaction().commit();

            System.out.println("Cliente actualizado: " + cliente);
        } catch (Exception e) {
            em.getTransaction().begin();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
