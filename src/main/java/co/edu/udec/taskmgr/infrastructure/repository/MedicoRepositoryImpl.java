package co.edu.udec.taskmgr.infrastructure.repository;

import co.edu.udec.taskmgr.domain.entidades.Medico;
import co.edu.udec.taskmgr.domain.puertos.IMedicoRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepositoryImpl implements IMedicoRepository {

    @Override
    public void save(Medico medico) {
        String sql = "INSERT INTO medico (nombre, direccion, telefono, poblacion, provincia, codigo_postal, num_seguridad_social, num_colegiado, categoria) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getDireccion());
            stmt.setString(3, medico.getTelefono());
            stmt.setString(4, medico.getPoblacion());
            stmt.setString(5, medico.getProvincia());
            stmt.setString(6, medico.getCodigo_postal());
            stmt.setString(7, medico.getNum_seguridad_social());
            stmt.setString(8, medico.getNum_colegiado());
            stmt.setString(9, medico.getCategoria());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar médico", e);
        }
    }

    @Override
    public Medico findByIdMedico(int id) {
        String sql = "SELECT * FROM medico WHERE id_medico = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Medico(
                    rs.getLong("id_medico"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigo_postal"),
                    rs.getString("num_seguridad_social"),
                    rs.getString("num_colegiado"),
                    rs.getString("categoria")
                );
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar médico por ID", e);
        }
    }

    @Override
    public List<Medico> showAllMedicos() {
        String sql = "SELECT * FROM medico";
        List<Medico> medicos = new ArrayList<>();

        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medico m = new Medico(
                    rs.getLong("id_medico"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigo_postal"),
                    rs.getString("num_seguridad_social"),
                    rs.getString("num_colegiado"),
                    rs.getString("categoria")
                );
                medicos.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar médicos", e);
        }

        return medicos;
    }

    @Override
    public void update(Medico medico) {
        String sql = "UPDATE medico SET nombre = ?, direccion = ?, telefono = ?, poblacion = ?, provincia = ?, codigo_postal = ?, " +
                     "num_seguridad_social = ?, num_colegiado = ?, categoria = ? WHERE id_medico = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNombre());
            stmt.setString(2, medico.getDireccion());
            stmt.setString(3, medico.getTelefono());
            stmt.setString(4, medico.getPoblacion());
            stmt.setString(5, medico.getProvincia());
            stmt.setString(6, medico.getCodigo_postal());
            stmt.setString(7, medico.getNum_seguridad_social());
            stmt.setString(8, medico.getNum_colegiado());
            stmt.setString(9, medico.getCategoria());
            stmt.setLong(10, medico.getIdMedico());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar médico", e);
        }
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM medico WHERE id_medico = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar médico", e);
        }
    }
}
