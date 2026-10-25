package umg.DAO;

import umg.modelo.Ciudad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuidadDAO {

    public boolean insertar(Ciudad c) {
        String sql = "INSERT INTO ciudad (nombre) VALUES (?)";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Ciudad c) {
        String sql = "UPDATE ciudad SET nombre = ? WHERE idciudad = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdCiudad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM ciudad WHERE idciudad = ?";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ciudad> listar() {
        List<Ciudad> lista = new ArrayList<>();
        String sql = "SELECT * FROM ciudad";
        try (Connection con = ConexionDB.abrir(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ciudad c = new Ciudad();
                c.setIdCiudad(rs.getInt("idciudad"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}