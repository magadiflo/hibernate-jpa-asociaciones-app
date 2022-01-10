package org.magadiflo.hibernate.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    @Column(name = "forma_pago")
    private String formaPago;

    @Embedded
    private Auditoria audit = new Auditoria();

    //CascadeType.ALL, cada que se crea un Cliente, también creará a sus relaciones o sea Direcciones
    //orphanRemoval = true, al eliminar un cliente automaticamente se eliminarán todos las direcciones asociadas a él
    //fetch = FetchType.LAZY, para la relación que termine en ...Many (ejmpl. @OneToMany) por defecto es Lazy, es decir,
    //traerá los datos de la relación sólo cuando se le solicite
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //Crea la llave foránea en la BD, tabla direcciones
    //@JoinColumn(name = "cliente_id")
    //Crea una tabla intermedia llamada tbl_clientes_direcciones donde se almacenan las llaves de clientes y direcciones
    @JoinTable(name = "tbl_clientes_direcciones",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "direccion_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"direccion_id"}))
    private List<Direccion> direcciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;

    //fetch = FetchType.EAGER, para la relación que termine en ...One (ejmpl. @OneToOne) por defecto es EAGER,
    //cargará por defecto la información asociada a las relaciones
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private ClienteDetalle clienteDetalle;

    public Cliente() {
        this.direcciones = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public Auditoria getAudit() {
        return audit;
    }

    public void setAudit(Auditoria audit) {
        this.audit = audit;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public ClienteDetalle getClienteDetalle() {
        return clienteDetalle;
    }

    public void setClienteDetalle(ClienteDetalle clienteDetalle) {
        this.clienteDetalle = clienteDetalle;
    }

    //Establecemos la relación en ambos lados para su guardado
    public Cliente addFactura(Factura factura){
        this.facturas.add(factura);
        factura.setCliente(this);
        return this;
    }

    public void removeFactura(Factura factura) {
        this.facturas.remove(factura);
        factura.setCliente(null);
    }

    public void addDetalle(ClienteDetalle detalle){
        this.clienteDetalle = detalle;
        detalle.setCliente(this);
    }

    public void removeDetalle(){
        this.clienteDetalle.setCliente(null);
        this.clienteDetalle = null;
    }

    @Override
    public String toString() {
        LocalDateTime creado = this.audit != null ? audit.getCreadoEn() : null;
        LocalDateTime editado = this.audit != null ? audit.getEditadoEn() : null;
        final StringBuilder sb = new StringBuilder("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", formaPago='").append(formaPago).append('\'');
        sb.append(", creadoEn=").append(creado);
        sb.append(", editadoEn=").append(editado);
        sb.append(", direcciones=").append(direcciones);
        sb.append(", facturas=").append(facturas);
        sb.append(", clienteDetalle=").append(clienteDetalle);
        sb.append('}');
        return sb.toString();
    }

}