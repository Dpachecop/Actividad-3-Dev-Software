/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.taskmgr.application.usecases.user.update;

/**
 *
 * @author Oscar Mercado
 */
public class UpdateUserCommand {
    private String email;     // identificador para buscar al usuario
    private String name;
    private String password;

    public UpdateUserCommand(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // Getters
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPassword() { return password; }
}
