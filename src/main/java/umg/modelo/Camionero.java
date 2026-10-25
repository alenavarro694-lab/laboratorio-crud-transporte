package umg.modelo;

import java.math.BigDecimal;

public class Camionero {
    private int idCamionero;
    private String nombre;
    private String apellidos;
    private String direccion;
    private BigDecimal salario;
    private String poblacion;
    private String telefono;

    public Camionero() {}

    public Camionero(int idCamionero, String nombre, String apellidos, String direccion, BigDecimal salario, String poblacion, String telefono) {
        this.idCamionero = idCamionero;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.salario = salario;
        this.poblacion = poblacion;
        this.telefono = telefono;
    }

    public int getIdCamionero() { return idCamionero; }
    public void setIdCamionero(int idCamionero) { this.idCamionero = idCamionero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public String getPoblacion() { return poblacion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
