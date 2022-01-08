package org.magadiflo.hibernate.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes_detalles")
public class ClienteDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean prime;

    @Column(name = "puntos_acumulados")
    private Long puntosAcumulados;

    public ClienteDetalle() {
    }

    public ClienteDetalle(boolean prime, Long puntosAcumulados) {
        this.prime = prime;
        this.puntosAcumulados = puntosAcumulados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }

    public Long getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(Long puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClienteDetalle{");
        sb.append("id=").append(id);
        sb.append(", prime=").append(prime);
        sb.append(", puntosAcumulados=").append(puntosAcumulados);
        sb.append('}');
        return sb.toString();
    }
}
