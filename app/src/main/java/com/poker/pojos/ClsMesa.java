package com.poker.pojos;

public class ClsMesa {
    private String id_mesa;
    private String entrada_minima;
    private String modalidad;
    private String ciegaMinima;
    private String ciegaMaxima;
    private String usuariosActivos;
    private String cantidadUsuarios;

    public ClsMesa(String id_mesa, String entrada_minima, String modalidad, String ciegaMinima, String ciegaMaxima, String usuariosActivos, String cantidadUsuarios) {
        this.id_mesa = id_mesa;
        this.entrada_minima = entrada_minima;
        this.modalidad = modalidad;
        this.ciegaMinima = ciegaMinima;
        this.ciegaMaxima = ciegaMaxima;
        this.usuariosActivos = usuariosActivos;
        this.cantidadUsuarios = cantidadUsuarios;
    }

    public String getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(String id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getEntrada_minima() {
        return entrada_minima;
    }

    public void setEntrada_minima(String entrada_minima) {
        this.entrada_minima = entrada_minima;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCiegaMinima() {
        return ciegaMinima;
    }

    public void setCiegaMinima(String ciegaMinima) {
        this.ciegaMinima = ciegaMinima;
    }

    public String getCiegaMaxima() {
        return ciegaMaxima;
    }

    public void setCiegaMaxima(String ciegaMaxima) {
        this.ciegaMaxima = ciegaMaxima;
    }

    public String getUsuariosActivos() {
        return usuariosActivos;
    }

    public void setUsuariosActivos(String usuariosActivos) {
        this.usuariosActivos = usuariosActivos;
    }

    public String getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(String cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }
}
