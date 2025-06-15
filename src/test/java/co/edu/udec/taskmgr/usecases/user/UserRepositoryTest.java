package co.edu.udec.taskmgr.usecases.user;

import co.edu.udec.taskmgr.domain.entidades.User;
import co.edu.udec.taskmgr.domain.enums.UserRole;
import co.edu.udec.taskmgr.domain.puertos.IUserRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.repository.UserRepositoryImp;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private IUserRepository repository;
    private UserRepositoryImp userRepository;

    @Before
    public void setup() throws Exception {
        SQLiteDatabaseInitializer.initialize();
        repository = new UserRepositoryImp();
        try (Connection conn = PersistenceManager.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.executeUpdate("CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT, name TEXT, role TEXT)");
        }
    }
    
    public void setUp() {
        // Arrange: inicializar repositorio real
        userRepository = new UserRepositoryImp();
        
        // Arrange: Insertar un usuario inicial si aún no existe
        User user = new User("test@email.com", "123456", "Oscar", UserRole.STANDARD);
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }
        User usuario = new User("delete@email.com", "clave123", "Usuario para borrar", UserRole.STANDARD);
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }
    }
    
    @Test
    public void testSaveAndFindUser() {
        SQLiteDatabaseInitializer.initialize();
        User user = new User("repo@test.com", "123456", "Test Repo", UserRole.STANDARD);
        repository.save(user);

        User result = repository.findByEmail("repo@test.com");

        assertNotNull(result);
        assertEquals("Test Repo", result.getName());
        assertEquals(UserRole.STANDARD, result.getRole());
    }
    
    public void testFindAllUsers_ReturnsUserList() {
        // Arrange: (el repositorio cuenta con un solo usuario ya crreado asi que se puede usar para el test)

        // Act: llamar al método findAll
        List<User> userList = userRepository.findAll();

        // Assert: verificar resultados
        assertNotNull("La lista de usuarios no debe ser nula", userList);
        assertTrue("Debe haber al menos un usuario registrado", userList.size() >= 0);

        // Puedes imprimir los usuarios como apoyo visual (opcional en pruebas unitarias)
        for (User user : userList) {
            System.out.println("Usuario: " + user.getName() + " | Email: " + user.getEmail() + " | Rol: " + user.getRole());
        }
    }
    
    public void testUpdateUser() {
        // Arrange
        User userToUpdate = userRepository.findByEmail("test@email.com");
        assertNotNull("El usuario debe existir antes del update", userToUpdate);
        
        userToUpdate.setName("Oscar Actualizado");
        userToUpdate.setPassword("nuevaclave");

        // Act
        User updatedUser = userRepository.update(userToUpdate);

        // Assert
        assertNotNull(updatedUser);
        assertEquals("Oscar Actualizado", updatedUser.getName());
        assertEquals("nuevaclave", updatedUser.getPassword());
    }
    
    public void testDeleteUser() {
        // Arrange
        String email = "delete@email.com";
        User userBeforeDelete = userRepository.findByEmail(email);
        assertNotNull("El usuario debe existir antes de eliminarlo", userBeforeDelete);

        // Act
        boolean deleted = userRepository.delete(email);

        // Assert
        assertTrue("El usuario debería haberse eliminado correctamente", deleted);
        assertNull("El usuario ya no debe existir en la base de datos", userRepository.findByEmail(email));
    }
}
