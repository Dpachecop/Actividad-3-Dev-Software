package co.edu.udec.taskmgr.domain.entidades;

import co.edu.udec.taskmgr.domain.enums.UserRole;



public class User {
    private String email;
    private String password;
    private String name;
    private UserRole role;

    public User(String email, String password, String name, UserRole role) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be empty");
        if (password == null || password.length() < 6) throw new IllegalArgumentException("Password too short");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        if (role == null) throw new IllegalArgumentException("Role cannot be null");

        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public UserRole getRole() { return role; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }   
    
}
