package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.entity.Factura;
import org.magadiflo.hibernate.app.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccionalFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 1L);

            Factura f1 = new Factura("Compra de supermercado", 500L);
            Factura f2 = new Factura("Compra de tecnología", 7500L);

            cliente.addFactura(f1).addFactura(f2);

            //em.merge(cliente);Es opcional, no es necesario ya que el cliente queda en el contexto de persistencia al haber sido buscado con find(...)
            em.getTransaction().commit(); //El commit, si detecta cambios automaticamente hará el updated

            System.out.println("Cliente guardado: " + cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
