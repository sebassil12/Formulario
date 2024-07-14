package POO;

import java.util.Date;

public class User {
    private int id;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String sexo;
    private String foto;
    private String colegio;
    private String universidad;
    private String trabajo;
    private String datoReferente;

    public User(int id, String nombres, String apellidos, Date fechaNacimiento, String sexo, String foto,
                String colegio, String universidad, String trabajo, String datoReferente) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.foto = foto;
        this.colegio = colegio;
        this.universidad = universidad;
        this.trabajo = trabajo;
        this.datoReferente = datoReferente;
    }
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getColegio() { return colegio; }
    public void setColegio(String colegio) { this.colegio = colegio; }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }

    public String getTrabajo() { return trabajo; }
    public void setTrabajo(String trabajo) { this.trabajo = trabajo; }

    public String getDatoReferente() { return datoReferente; }
    public void setDatoReferente(String datoReferente) { this.datoReferente = datoReferente; }
}


