package co.edu.udec.taskmgr.infrastructure.repository;

import co.edu.udec.taskmgr.domain.entidades.Task;
import co.edu.udec.taskmgr.domain.enums.TaskStatus;
import co.edu.udec.taskmgr.domain.puertos.ITaskRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements ITaskRepository {

    @Override
    public void save(Task task) {
        String sql = "INSERT INTO tasks (title, description, status, user_email) VALUES (?, ?, ?, ?)";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus().name());
            stmt.setString(4, task.getUserEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la tarea", e);
        }
    }

    @Override
    public List<Task> findByUserEmail(String email) {
        String sql = "SELECT id, title, description, status, user_email FROM tasks WHERE user_email = ?";
        List<Task> tareas = new ArrayList<>();

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task tarea = new Task(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    TaskStatus.valueOf(rs.getString("status")),
                    rs.getString("user_email")
                );
                tareas.add(tarea);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar tareas por correo", e);
        }

        return tareas;
    }

    @Override
    public Task findById(long id) {
        String sql = "SELECT id, title, description, status, user_email FROM tasks WHERE id = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Task(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    TaskStatus.valueOf(rs.getString("status")),
                    rs.getString("user_email")
                );
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la tarea por ID", e);
        }
    }

    @Override
    public void update(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ?, user_email = ? WHERE id = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus().name());
            stmt.setString(4, task.getUserEmail());
            stmt.setLong(5, task.getId());

            int filas = stmt.executeUpdate();
            if (filas == 0) {
                throw new RuntimeException("No se encontró la tarea con ID: " + task.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la tarea", e);
        }
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }
}
