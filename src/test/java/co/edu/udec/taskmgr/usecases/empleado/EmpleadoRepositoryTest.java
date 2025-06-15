package co.edu.udec.taskmgr.usecases.empleado;

import co.edu.udec.taskmgr.domain.entidades.Empleado;
import co.edu.udec.taskmgr.domain.puertos.IEmpleadoRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.repository.EmpleadoRepositoryImp;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class EmpleadoRepositoryTest {

    private IEmpleadoRepository repo;

    @Before
    public void setUp() {
        SQLiteDatabaseInitializer.initialize();
        repo = new EmpleadoRepositoryImp();
        limpiar();
    }

    private void limpiar() {
        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM empleado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSaveYFindById() {
        Empleado e = new Empleado(0, "Luis", "Cra 10", "301", "Cartagena", "Bolívar", "13001", "123", "789", "Administrador");
        repo.save(e);

        Empleado guardado = repo.findAll().get(0);
        Empleado encontrado = repo.findById_empleado(guardado.getId_empleado());

        assertNotNull(encontrado);
        assertEquals("Luis", encontrado.getNombre());
    }

    @Test
    public void testFindAll() {
        repo.save(new Empleado(0, "Ana", "Dir1", "311", "Ctg", "Bol", "1101", "456", "888", "Soporte"));
        repo.save(new Empleado(0, "Pedro", "Dir2", "312", "Barranquilla", "Atl", "2202", "789", "999", "Diseñador"));

        List<Empleado> lista = repo.findAll();
        assertEquals(2, lista.size());
    }

    @Test
    public void testUpdate() {
        repo.save(new Empleado(0, "Oscar", "Dir", "300", "Pob", "Prov", "000", "111", "222", "Dev"));
        Empleado original = repo.findAll().get(0);

        original.setNombre("Oscar Mercado");
        original.setTipo_empleado("Senior Dev");
        Empleado actualizado = repo.update(original);

        assertEquals("Oscar Mercado", actualizado.getNombre());
        assertEquals("Senior Dev", actualizado.getTipo_empleado());
    }

    @Test
    public void testDelete() {
        repo.save(new Empleado(0, "Borrar", "Dir", "301", "Pob", "Prov", "001", "002", "003", "X"));
        int id = repo.findAll().get(0).getId_empleado();

        boolean eliminado = repo.delete(id);
        Empleado resultado = repo.findById_empleado(id);

        assertTrue(eliminado);
        assertNull(resultado);
    }
}
