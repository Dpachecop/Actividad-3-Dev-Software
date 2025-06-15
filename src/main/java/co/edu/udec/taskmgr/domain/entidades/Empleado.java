/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.taskmgr.domain.entidades;

/**
 *
 * @author Oscar Mercado
 */
public class Empleado {
    private int id_empleado;
    private String nombre;
    private String direccion;
    private String telefono;
    private String poblacion;
    private String provincia;
    private String codigo_postal;
    private String nif;
    private String num_seguridad_social;
    private String tipo_empleado;

    public Empleado(int id_empleado, String nombre, String direccion, String telefono, String poblacion, String provincia, String codigo_postal, String nif, String num_seguridad_social, String tipo_empleado) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.codigo_postal = codigo_postal;
        this.nif = nif;
        this.num_seguridad_social = num_seguridad_social;
        this.tipo_empleado = tipo_empleado;
    }

    public int getId_empleado() {return id_empleado;    }
    public void setId_empleado(int id_empleado) {this.id_empleado = id_empleado;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getPoblacion() {return poblacion;}
    public void setPoblacion(String poblacion) {this.poblacion = poblacion;}

    public String getProvincia() {return provincia;}
    public void setProvincia(String provincia) {this.provincia = provincia;}

    public String getCodigo_postal() {return codigo_postal;}
    public void setCodigo_postal(String codigo_postal) {this.codigo_postal = codigo_postal;}

    public String getNif() {return nif;}
    public void setNif(String nif) {this.nif = nif;}

    public String getNum_seguridad_social() {return num_seguridad_social;}
    public void setNum_seguridad_social(String num_seguridad_social) {this.num_seguridad_social = num_seguridad_social;}

    public String getTipo_empleado() {return tipo_empleado;}
    public void setTipo_empleado(String tipo_empleado) {this.tipo_empleado = tipo_empleado;}   
}