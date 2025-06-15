/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.udec.taskmgr.domain.puertos;


import co.edu.udec.taskmgr.domain.entidades.Empleado;
import java.util.List;

/**
 *
 * @author Oscar Mercado
 */
public interface IEmpleadoRepository {
    
    Empleado findById_empleado(int id_empleado);
    void save(Empleado user);
    List<Empleado> findAll();
    Empleado update(Empleado id_empleado);
    boolean delete(int id_empleado);
    
}
