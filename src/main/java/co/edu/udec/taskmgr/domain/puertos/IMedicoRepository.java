/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.udec.taskmgr.domain.puertos;

import co.edu.udec.taskmgr.domain.entidades.Medico;
import java.util.List;

/**
 *
 * @author Danie
 */
public interface IMedicoRepository {
    
    void save(Medico task); // Crear

    Medico findByIdMedico (int id); // Leer (por usuario)

    List<Medico> showAllMedicos(); // Mostrar todos los medicos

    void update(Medico medico); // Actualizar 

    void deleteById(long id); // Eliminar
}
