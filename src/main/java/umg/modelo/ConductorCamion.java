package umg.modelo;

import java.time.LocalDate;

public class ConductorCamion {
    private int idConductorCamion;
    private Camionero conductor;
    private Camion camion;
    private LocalDate fecha;

    public ConductorCamion() {}

    public ConductorCamion(int idConductorCamion, Camionero conductor, Camion camion, LocalDate fecha) {
        this.idConductorCamion = idConductorCamion;
        this.conductor = conductor;
        this.camion = camion;
        this.fecha = fecha;
    }

    public int getIdConductorCamion() { return idConductorCamion; }
    public void setIdConductorCamion(int idConductorCamion) { this.idConductorCamion = idConductorCamion; }

    public Camionero getConductor() { return conductor; }
    public void setConductor(Camionero conductor) { this.conductor = conductor; }

    public Camion getCamion() { return camion; }
    public void setCamion(Camion camion) { this.camion = camion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}