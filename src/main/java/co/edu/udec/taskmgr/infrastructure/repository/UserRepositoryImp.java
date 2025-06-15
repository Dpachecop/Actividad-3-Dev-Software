package co.edu.udec.taskmgr.infrastructure.repository;



import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.domain.puertos.IUserRepository;
import co.edu.udec.taskmgr.domain.enums.UserRole;
import co.edu.udec.taskmgr.domain.entidades.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements IUserRepository {

    @Override
    public void save(User user) {
        try (Connection conn = PersistenceManager.getConnection()) {
            String sql = "INSERT INTO users (email, password, name, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getRole().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Connection conn = PersistenceManager.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("name"),
                    UserRole.valueOf(rs.getString("role"))
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

    try (Connection conn = PersistenceManager.getConnection()) {
        String sql = "SELECT * FROM users";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            User user = new User(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                UserRole.valueOf(rs.getString("role"))
            );
            users.add(user);
        }

        return users;

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving users", e);
        }
    }
    
    @Override
    public User update(User user) {
        String sql = "UPDATE users SET name = ?, password = ?, role = ? WHERE email = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().toString());
            stmt.setString(4, user.getEmail());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("No se pudo actualizar el usuario. Email no encontrado: " + user.getEmail());
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(String email) {
        try (Connection conn = PersistenceManager.getConnection()) {
            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }
}
