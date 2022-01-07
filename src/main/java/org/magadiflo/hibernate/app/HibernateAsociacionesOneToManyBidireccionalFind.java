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

            //Eliminando la factura con id = 1 del cliente. NOTA: se tiene que eliminar en ambos sentidos
            em.getTransaction().begin();

            //Primera forma de eliminar, yendo a buscar la factura con id = 1
            //Factura f3 = em.find(Factura.class, 1L);

            //Segunda forma de eliminar, creando una instancia de la factura y asignandole el id = 1, pero debemos tener el método equals en factura
            Factura f3 = new Factura("Compra de supermercado", 500L);
            f3.setId(1L);

//            Se puede eliminar de esta manera
//            cliente.getFacturas().remove(f3);
//            f3.setCliente(null);

//            O crear un método en la clase cliente
            cliente.removeFactura(f3);

            em.getTransaction().commit();
            System.out.println("Cliente factura eliminada: " + cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
