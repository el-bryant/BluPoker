package com.poker.pojos;

public class ClsUsuarios {
    private String apellidos;
    private String celular;
    private int fichas;
    private String nombres;
    private String pwd;
    private String sexo;
    private String usuario;

    public ClsUsuarios(String apellidos, String celular, int fichas, String nombres, String pwd, String sexo, String usuario) {
        this.apellidos = apellidos;
        this.celular = celular;
        this.fichas = fichas;
        this.nombres = nombres;
        this.pwd = pwd;
        this.sexo = sexo;
        this.usuario = usuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
