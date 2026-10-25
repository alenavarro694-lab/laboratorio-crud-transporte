/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.modelo;

/**
 *
 * @author puchi
 */
public class Ciudad {
    private int idCiudad;
    private String nombre;

    public Ciudad(int idCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
    }
    
    public Ciudad (){
    
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "idCiudad=" + idCiudad + ", nombre=" + nombre + '}';
    }
    
    
}
