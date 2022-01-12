package org.magadiflo.hibernate.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.magadiflo.hibernate.app.entity.Cliente;
import org.magadiflo.hibernate.app.util.JpaUtil;

import java.util.List;

public class HibernateFetchOneToManyCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);
        cliente.fetch("direcciones", JoinType.LEFT);
        cliente.fetch("clienteDetalle", JoinType.LEFT);

        query.select(cliente).distinct(true);
        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones() + ", clienteDetalle: " + c.getClienteDetalle()));

        em.close();
    }
}
