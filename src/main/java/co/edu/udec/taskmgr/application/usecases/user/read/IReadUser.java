/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.udec.taskmgr.application.usecases.user.read;

import co.edu.udec.taskmgr.domain.entidades.User;
import java.util.List;
/**
 *
 * @author Oscar Mercado
 */
public interface IReadUser {
    List<User> handle(ReadUserCommand query);
}
