package co.edu.udec.taskmgr.domain.entidades;

public class Medico {

    private long idMedico;
    private String nombre; 
    private String direccion; 
    private String telefono;
    private String poblacion; 
    private String provincia; 
    private String codigo_postal;
    private String num_seguridad_social;
    private String num_colegiado; 
    private String categoria;

    public Medico(String nombre, String direccion, String telefono, String poblacion, String provincia,
                  String codigo_postal, String num_seguridad_social, String num_colegiado, String categoria) {

        this.nombre = (nombre == null || nombre.isBlank()) ? "" : nombre;
        this.direccion = (direccion == null) ? "" : direccion;
        this.telefono = (telefono == null) ? "" : telefono;
        this.poblacion = (poblacion == null) ? "" : poblacion;
        this.provincia = (provincia == null) ? "" : provincia;
        this.codigo_postal = (codigo_postal == null) ? "" : codigo_postal;
        this.num_seguridad_social = (num_seguridad_social == null) ? "" : num_seguridad_social;
        this.num_colegiado = (num_colegiado == null) ? "" : num_colegiado;
        this.categoria = (categoria == null) ? "" : categoria;
    }

    public Medico(long idMedico, String nombre, String direccion, String telefono, String poblacion, String provincia,
                  String codigo_postal, String num_seguridad_social, String num_colegiado, String categoria) {
        this(nombre, direccion, telefono, poblacion, provincia, codigo_postal, num_seguridad_social, num_colegiado, categoria);
        this.idMedico = idMedico;
    }

    public long getIdMedico() { return idMedico; }
    public void setIdMedico(long idMedico) { this.idMedico = idMedico; }

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getPoblacion() { return poblacion; }
    public String getProvincia() { return provincia; }
    public String getCodigo_postal() { return codigo_postal; }
    public String getNum_seguridad_social() { return num_seguridad_social; }
    public String getNum_colegiado() { return num_colegiado; }
    public String getCategoria() { return categoria; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public void setNum_seguridad_social(String num_seguridad_social) {
        this.num_seguridad_social = num_seguridad_social;
    }

    public void setNum_colegiado(String num_colegiado) {
        this.num_colegiado = num_colegiado;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    
    
    
}

