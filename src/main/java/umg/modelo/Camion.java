package umg.modelo;

import java.math.BigDecimal;

public class Camion {
    private int idCamion;
    private String modelo;
    private String tipo;
    private BigDecimal potencia;
    private String noPlaca;

    public Camion() {}

    public Camion(int idCamion, String modelo, String tipo, BigDecimal potencia, String noPlaca) {
        this.idCamion = idCamion;
        this.modelo = modelo;
        this.tipo = tipo;
        this.potencia = potencia;
        this.noPlaca = noPlaca;
    }

    public int getIdCamion() { return idCamion; }
    public void setIdCamion(int idCamion) { this.idCamion = idCamion; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getPotencia() { return potencia; }
    public void setPotencia(BigDecimal potencia) { this.potencia = potencia; }

    public String getNoPlaca() { return noPlaca; }
    public void setNoPlaca(String noPlaca) { this.noPlaca = noPlaca; }
}
