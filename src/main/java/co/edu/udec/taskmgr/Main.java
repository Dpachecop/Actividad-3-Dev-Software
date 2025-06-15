package co.edu.udec.taskmgr;

import co.edu.udec.taskmgr.domain.entidades.Medico;
import co.edu.udec.taskmgr.domain.entidades.Empleado;
import co.edu.udec.taskmgr.domain.puertos.IMedicoRepository;
import co.edu.udec.taskmgr.domain.puertos.IEmpleadoRepository;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.repository.MedicoRepositoryImpl;
import co.edu.udec.taskmgr.infrastructure.repository.EmpleadoRepositoryImp;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Inicializando base de datos...");
        SQLiteDatabaseInitializer.initialize();

        // Instancias de los repositorios
        IMedicoRepository medicoRepo = new MedicoRepositoryImpl();
        IEmpleadoRepository empleadoRepo = new EmpleadoRepositoryImp();

       // EJECUCION DE PRUEBA PARA LA CLASE / TABLA MEDICO
        System.out.println("Insertando medico...");
        Medico m = new Medico("Dra. Clara", "Av. Siempre Viva", "3001234567", "Bogota", "Cundinamarca",
                              "110111", "987654321", "456789", "Ginecologa");
        medicoRepo.save(m);

        List<Medico> medicos = medicoRepo.showAllMedicos();
        System.out.println("Medicos encontrados: " + medicos.size());

        Medico encontrado = medicoRepo.findByIdMedico((int) medicos.get(0).getIdMedico());
        System.out.println("Medico recuperado: " + encontrado.getNombre());

        encontrado.setCategoria("Ginecologia General");
        medicoRepo.update(encontrado);
        System.out.println("Medico actualizado: " + medicoRepo.findByIdMedico((int) encontrado.getIdMedico()).getCategoria());

        medicoRepo.deleteById(encontrado.getIdMedico());
        System.out.println("Medico eliminado: " + (medicoRepo.findByIdMedico((int) encontrado.getIdMedico()) == null));

        // EJECUCION DE PRUEBA PARA LA CLASE / TABLA EMPLEADO
        System.out.println("\nInsertando empleado...");
        Empleado e = new Empleado(0, "Oscar Mercado", "Cl 45", "3014567890", "Barranquilla", "Atlantico",
                                  "080001", "123456", "789012", "Ingeniero");

        empleadoRepo.save(e);
        List<Empleado> empleados = empleadoRepo.findAll();
        System.out.println("Empleados encontrados: " + empleados.size());

        Empleado empEncontrado = empleadoRepo.findById_empleado(empleados.get(0).getId_empleado());
        System.out.println("Empleado recuperado: " + empEncontrado.getNombre());

        empEncontrado.setTipo_empleado("Senior Developer");
        empleadoRepo.update(empEncontrado);
        System.out.println("Empleado actualizado: " + empleadoRepo.findById_empleado(empEncontrado.getId_empleado()).getTipo_empleado());

        boolean eliminado = empleadoRepo.delete(empEncontrado.getId_empleado());
        System.out.println("Empleado eliminado: " + eliminado);
    }
}
