package co.edu.udec.taskmgr.domain.puertos;

import co.edu.udec.taskmgr.domain.entidades.Task;
import java.util.List;

public interface ITaskRepository {

    void save(Task task); // Crear

    List<Task> findByUserEmail(String email); // Leer (por usuario)

    Task findById(long id); // Leer por ID

    void update(Task task); // Actualizar (por ID)

    void deleteById(long id); // Eliminar
}