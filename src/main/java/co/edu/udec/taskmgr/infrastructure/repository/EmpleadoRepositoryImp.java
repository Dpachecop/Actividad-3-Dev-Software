/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.taskmgr.infrastructure.repository;

import co.edu.udec.taskmgr.domain.entidades.Empleado;
import co.edu.udec.taskmgr.domain.puertos.IEmpleadoRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar Mercado
 */

public class EmpleadoRepositoryImp implements IEmpleadoRepository {

    @Override
    public Empleado findById_empleado(int id) {
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigo_postal"),
                    rs.getString("nif"),
                    rs.getString("num_seguridad_social"),
                    rs.getString("tipo_empleado")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando empleado por ID", e);
        }
    }

    @Override
    public void save(Empleado user) {
        String sql = "INSERT INTO empleado (nombre, direccion, telefono, poblacion, provincia, codigo_postal, nif, num_seguridad_social, tipo_empleado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getDireccion());
            stmt.setString(3, user.getTelefono());
            stmt.setString(4, user.getPoblacion());
            stmt.setString(5, user.getProvincia());
            stmt.setString(6, user.getCodigo_postal());
            stmt.setString(7, user.getNif());
            stmt.setString(8, user.getNum_seguridad_social());
            stmt.setString(9, user.getTipo_empleado());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar empleado", e);
        }
    }

    @Override
    public List<Empleado> findAll() {
        String sql = "SELECT * FROM empleado";
        List<Empleado> lista = new ArrayList<>();

        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigo_postal"),
                    rs.getString("nif"),
                    rs.getString("num_seguridad_social"),
                    rs.getString("tipo_empleado")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error listando empleados", e);
        }

        return lista;
    }

    @Override
    public Empleado update(Empleado user) {
        String sql = "UPDATE empleado SET nombre = ?, direccion = ?, telefono = ?, poblacion = ?, provincia = ?, " +
                     "codigo_postal = ?, nif = ?, num_seguridad_social = ?, tipo_empleado = ? WHERE id_empleado = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getDireccion());
            stmt.setString(3, user.getTelefono());
            stmt.setString(4, user.getPoblacion());
            stmt.setString(5, user.getProvincia());
            stmt.setString(6, user.getCodigo_postal());
            stmt.setString(7, user.getNif());
            stmt.setString(8, user.getNum_seguridad_social());
            stmt.setString(9, user.getTipo_empleado());
            stmt.setInt(10, user.getId_empleado());

            stmt.executeUpdate();
            return findById_empleado(user.getId_empleado());
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando empleado", e);
        }
    }
    @Override
    public boolean delete(int id_empleado) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_empleado);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando empleado", e);
        }
    }    
}