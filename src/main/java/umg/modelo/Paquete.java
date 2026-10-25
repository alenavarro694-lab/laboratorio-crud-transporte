package umg.modelo;

import java.math.BigDecimal;

public class Paquete {
    private int idPaquete;
    private String descripcion;
    private String destinatario;
    private BigDecimal peso;
    private String direccion;
    private Camionero camionero;
    private Ciudad ciudad;

    public Paquete() {}

    public Paquete(int idPaquete, String descripcion, String destinatario, BigDecimal peso, String direccion, Camionero camionero, Ciudad ciudad) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.peso = peso;
        this.direccion = direccion;
        this.camionero = camionero;
        this.ciudad = ciudad;
    }

    public int getIdPaquete() { return idPaquete; }
    public void setIdPaquete(int idPaquete) { this.idPaquete = idPaquete; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Camionero getCamionero() { return camionero; }
    public void setCamionero(Camionero camionero) { this.camionero = camionero; }

    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }
}