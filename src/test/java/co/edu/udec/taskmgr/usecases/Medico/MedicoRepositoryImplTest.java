package co.edu.udec.taskmgr.usecases.Medico;
import co.edu.udec.taskmgr.domain.entidades.Medico;
import co.edu.udec.taskmgr.domain.puertos.IMedicoRepository;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.infrastructure.repository.MedicoRepositoryImpl;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class MedicoRepositoryImplTest {

    private IMedicoRepository repository;

    @Before
    public void setUp() {
        SQLiteDatabaseInitializer.initialize(); // Crear la tabla si no existe
        repository = new MedicoRepositoryImpl();
        limpiarTabla();
    }

    private void limpiarTabla() {
        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM medico");
        } catch (Exception e) {
            throw new RuntimeException("Error limpiando la tabla médico", e);
        }
    }

    @Test
    public void testSaveAndFindById() {
        // Arrange
        Medico m = new Medico("Juan", "Calle 123", "3014567890", "Cartagena", "Bolívar", "130001", "12345678", "987654", "General");
        repository.save(m);

        // Act
        Medico guardado = repository.showAllMedicos().get(0);
        Medico encontrado = repository.findByIdMedico((int) guardado.getIdMedico());

        // Assert
        assertNotNull(encontrado);
        assertEquals("Juan", encontrado.getNombre());
    }

    @Test
    public void testShowAllMedicos() {
        // Arrange
        repository.save(new Medico("Ana", "Calle 456", "3024567890", "Barranquilla", "Atlántico", "080001", "23456789", "876543", "Pediatra"));
        repository.save(new Medico("Luis", "Carrera 45", "3034567890", "Santa Marta", "Magdalena", "470001", "34567890", "765432", "Cardiólogo"));

        // Act
        List<Medico> medicos = repository.showAllMedicos();

        // Assert
        assertEquals(2, medicos.size());
    }

    @Test
    public void testUpdate() {
        // Arrange
        Medico original = new Medico("Pedro", "Calle 789", "3044567890", "Cali", "Valle", "760001", "45678901", "654321", "Internista");
        repository.save(original);
        Medico guardado = repository.showAllMedicos().get(0);

        // Act
        guardado.setNombre("Pedro A.");
        guardado.setCategoria("Geriatra");
        repository.update(guardado);
        Medico actualizado = repository.findByIdMedico((int) guardado.getIdMedico());

        // Assert
        assertEquals("Pedro A.", actualizado.getNombre());
        assertEquals("Geriatra", actualizado.getCategoria());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Medico m = new Medico("Sofía", "Avenida 10", "3054567890", "Medellín", "Antioquia", "050001", "56789012", "543210", "Dermatóloga");
        repository.save(m);
        long id = repository.showAllMedicos().get(0).getIdMedico();

        // Act
        repository.deleteById(id);
        Medico eliminado = repository.findByIdMedico((int) id);

        // Assert
        assertNull(eliminado);
    }
}
