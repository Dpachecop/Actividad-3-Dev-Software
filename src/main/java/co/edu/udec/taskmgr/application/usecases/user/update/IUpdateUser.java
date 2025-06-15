/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.udec.taskmgr.application.usecases.user.update;

import co.edu.udec.taskmgr.domain.entidades.User;

/**
 *
 * @author Oscar Mercado
 */
public interface IUpdateUser {
    User handle(UpdateUserCommand command);
}
