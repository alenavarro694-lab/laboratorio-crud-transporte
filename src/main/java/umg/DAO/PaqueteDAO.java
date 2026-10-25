package umg.DAO;

import umg.modelo.Paquete;
import umg.modelo.Camionero;
import umg.modelo.Ciudad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PaqueteDAO {

    public boolean insertar(Paquete p) {
        String sql = "INSERT INTO paquete (descripcion, destinatario, peso, direccion, idcamionero, idciudad) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setBigDecimal(3, p.getPeso());
            ps.setString(4, p.getDireccion());
            ps.setInt(5, p.getCamionero().getIdCamionero());
            ps.setInt(6, p.getCiudad().getIdCiudad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Paquete p) {
        String sql = "UPDATE paquete SET descripcion = ?, destinatario = ?, peso = ?, direccion = ?, idcamionero = ?, idciudad = ? WHERE idpaquete = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setBigDecimal(3, p.getPeso());
            ps.setString(4, p.getDireccion());
            ps.setInt(5, p.getCamionero().getIdCamionero());
            ps.setInt(6, p.getCiudad().getIdCiudad());
            ps.setInt(7, p.getIdPaquete());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM paquete WHERE idpaquete = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Paquete> listar() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.nombre as nom_cam, c.apellidos as ap_cam, ci.nombre as nom_ciu " +
                     "FROM paquete p " +
                     "JOIN camionero c ON p.idcamionero = c.idcamionero " +
                     "JOIN ciudad ci ON p.idciudad = ci.idciudad";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paquete p = new Paquete();
                p.setIdPaquete(rs.getInt("idpaquete"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDestinatario(rs.getString("destinatario"));
                p.setPeso(rs.getBigDecimal("peso"));
                p.setDireccion(rs.getString("direccion"));

                Camionero c = new Camionero();
                c.setIdCamionero(rs.getInt("idcamionero"));
                c.setNombre(rs.getString("nom_cam"));
                c.setApellidos(rs.getString("ap_cam"));
                p.setCamionero(c);

                Ciudad ci = new Ciudad();
                ci.setIdCiudad(rs.getInt("idciudad"));
                ci.setNombre(rs.getString("nom_ciu"));
                p.setCiudad(ci);

                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}