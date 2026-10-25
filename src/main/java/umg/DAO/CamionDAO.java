package umg.DAO;

import umg.modelo.Camion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class CamionDAO {

    public boolean insertar(Camion c) {
        String sql = "INSERT INTO camion (modelo, tipo, potencia, noplaca) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getModelo());
            ps.setString(2, c.getTipo());
            ps.setBigDecimal(3, c.getPotencia());
            ps.setString(4, c.getNoPlaca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Camion c) {
        String sql = "UPDATE camion SET modelo = ?, tipo = ?, potencia = ?, noplaca = ? WHERE idcamion = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getModelo());
            ps.setString(2, c.getTipo());
            ps.setBigDecimal(3, c.getPotencia());
            ps.setString(4, c.getNoPlaca());
            ps.setInt(5, c.getIdCamion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM camion WHERE idcamion = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Camion> listar() {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT * FROM camion";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Camion c = new Camion();
                c.setIdCamion(rs.getInt("idcamion"));
                c.setModelo(rs.getString("modelo"));
                c.setTipo(rs.getString("tipo"));
                c.setPotencia(rs.getBigDecimal("potencia"));
                c.setNoPlaca(rs.getString("noplaca"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}