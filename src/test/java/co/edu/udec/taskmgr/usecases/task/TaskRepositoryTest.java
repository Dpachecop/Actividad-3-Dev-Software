package co.edu.udec.taskmgr.usecases.task;

import co.edu.udec.taskmgr.domain.entidades.Task;
import co.edu.udec.taskmgr.domain.enums.TaskStatus;
import co.edu.udec.taskmgr.domain.puertos.ITaskRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.repository.TaskRepositoryImpl;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class TaskRepositoryTest {

    private ITaskRepository repository;

    @Before
    public void setup() throws Exception {
        SQLiteDatabaseInitializer.initialize();
        repository = new TaskRepositoryImpl();
        try (Connection conn = PersistenceManager.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS tasks");
            stmt.executeUpdate("CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, status TEXT, user_email TEXT)");
        }
         limpiarBaseDeDatos();
    }
    
    private void limpiarBaseDeDatos() {
    String sql = "DELETE FROM tasks";
    try (Connection conn = co.edu.udec.taskmgr.infrastructure.config.PersistenceManager.getConnection();
         Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(sql);
    } catch (Exception e) {
        throw new RuntimeException("Error limpiando tareas antes de las pruebas", e);
    }
}

    @Test
    public void testSaveAndFindByUserEmail() {
        SQLiteDatabaseInitializer.initialize();
        Task task = new Task("Test Task", "Testing persistence", TaskStatus.PENDING, "test@user.com");
        repository.save(task);

        List<Task> tasks = repository.findByUserEmail("test@user.com");

        assertFalse(tasks.isEmpty());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }
    
     @Test
    public void testFindById() {
        // Arrange
        Task tarea = new Task("Buscar por ID", "Probando findById", TaskStatus.IN_PROGRESS, "test@correo.com");
        repository.save(tarea);

        // Act
        List<Task> tareas = repository.findByUserEmail("test@correo.com");
        Task encontrada = repository.findById(tareas.get(0).getId());

        // Assert
        assertNotNull(encontrada);
        assertEquals("Buscar por ID", encontrada.getTitle());
    }

    @Test
    public void testUpdate() {
        // Arrange
        Task tarea = new Task("Actualizar", "Inicial", TaskStatus.PENDING, "update@correo.com");
        repository.save(tarea);
        Task guardada = repository.findByUserEmail("update@correo.com").get(0);

        // Act
        guardada.setTitle("Actualizada");
        guardada.setDescription("Descripción modificada");
        guardada.setStatus(TaskStatus.COMPLETED);
        repository.update(guardada);

        Task actualizada = repository.findById(guardada.getId());

        // Assert
        assertEquals("Actualizada", actualizada.getTitle());
        assertEquals(TaskStatus.COMPLETED, actualizada.getStatus());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Task tarea = new Task("Eliminar", "Será borrada", TaskStatus.PENDING, "delete@correo.com");
        repository.save(tarea);
        long id = repository.findByUserEmail("delete@correo.com").get(0).getId();

        // Act
        repository.deleteById(id);
        Task eliminada = repository.findById(id);

        // Assert
        assertNull(eliminada);
    }
}
