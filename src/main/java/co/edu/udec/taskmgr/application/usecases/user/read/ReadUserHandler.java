/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.taskmgr.application.usecases.user.read;

import co.edu.udec.taskmgr.domain.entidades.User;
import co.edu.udec.taskmgr.domain.puertos.IUserRepository;
import java.util.List;
/**
 *
 * @author Oscar Mercado
 */
public class ReadUserHandler implements IReadUser{
    private final IUserRepository userRepository;

    public ReadUserHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(ReadUserCommand query) {
        return userRepository.findAll();
    }
}
