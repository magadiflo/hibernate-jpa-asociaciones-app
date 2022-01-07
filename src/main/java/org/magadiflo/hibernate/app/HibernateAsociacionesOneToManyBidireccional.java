package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.Factura;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente();
            cliente.setNombre("Ronaldiño");
            cliente.setApellido("Gaucho");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("Compra de supermercado", 500L);
            Factura f2 = new Factura("Compra de tecnología", 7500L);

//            Establecemos la relación en ambos lados
//            cliente.getFacturas().add(f1);
//            cliente.getFacturas().add(f2);
//            f1.setCliente(cliente);
//            f2.setCliente(cliente);

            //El código comentado de arriba se puede resumir en un método creado en cliente
            cliente.addFactura(f1).addFactura(f2);

            em.persist(cliente);
            em.getTransaction().commit();

            System.out.println("Cliente guardado: " + cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
