/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.taskmgr.application.usecases.user.update;

import co.edu.udec.taskmgr.domain.entidades.User;
import co.edu.udec.taskmgr.domain.puertos.IUserRepository;

/**
 *
 * @author Oscar Mercado
 */
public class UpdateUserHandler implements IUpdateUser{
    private final IUserRepository userRepository;

    public UpdateUserHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(UpdateUserCommand command) {
        User existingUser = userRepository.findByEmail(command.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("Usuario no encontrado con email: " + command.getEmail());
        }

        // Actualizar datos
        existingUser.setName(command.getName());
        existingUser.setPassword(command.getPassword());

        return userRepository.update(existingUser);
    }
}
