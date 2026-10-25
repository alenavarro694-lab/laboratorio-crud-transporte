package umg.DAO;

import umg.modelo.ConductorCamion;
import umg.modelo.Camionero;
import umg.modelo.Camion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorCamionDAO {

    public boolean insertar(ConductorCamion cc) {
        String sql = "INSERT INTO conductor_camion (idcamionero, idcamion, fecha) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cc.getConductor().getIdCamionero());
            ps.setInt(2, cc.getCamion().getIdCamion());
            ps.setDate(3, Date.valueOf(cc.getFecha()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM conductor_camion WHERE idconductorcamion = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ConductorCamion> listar() {
        List<ConductorCamion> lista = new ArrayList<>();
        String sql = "SELECT cc.idconductorcamion, cc.fecha, " +
                     "c.idcamionero, c.nombre, c.apellidos, " +
                     "cam.idcamion, cam.modelo, cam.no_placa " +
                     "FROM conductor_camion cc " +
                     "JOIN camionero c ON cc.idcamionero = c.idcamionero " +
                     "JOIN camion cam ON cc.idcamion = cam.idcamion";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ConductorCamion cc = new ConductorCamion();
                cc.setIdConductorCamion(rs.getInt("idconductorcamion"));
                cc.setFecha(rs.getDate("fecha").toLocalDate());

                Camionero c = new Camionero();
                c.setIdCamionero(rs.getInt("idcamionero"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                cc.setConductor(c);

                Camion cam = new Camion();
                cam.setIdCamion(rs.getInt("idcamion"));
                cam.setModelo(rs.getString("modelo"));
                cam.setNoPlaca(rs.getString("no_placa"));
                cc.setCamion(cam);

                lista.add(cc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}