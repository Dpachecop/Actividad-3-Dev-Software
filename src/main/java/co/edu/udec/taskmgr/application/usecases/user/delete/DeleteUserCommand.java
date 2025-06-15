/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.taskmgr.application.usecases.user.delete;

/**
 *
 * @author Oscar Mercado
 */
public class DeleteUserCommand {
    private final String email;

    public DeleteUserCommand(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
