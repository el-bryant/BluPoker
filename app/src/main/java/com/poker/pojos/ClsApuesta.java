package com.poker.pojos;

public class ClsApuesta {
    private String id_apuesta;
    private String id_usuario_mesa;
    private String cantidad_fichas;
    private String ronda;

    public ClsApuesta(String id_apuesta, String id_usuario_mesa, String cantidad_fichas, String ronda) {
        this.id_apuesta = id_apuesta;
        this.id_usuario_mesa = id_usuario_mesa;
        this.cantidad_fichas = cantidad_fichas;
        this.ronda = ronda;
    }

    public String getId_apuesta() {
        return id_apuesta;
    }

    public void setId_apuesta(String id_apuesta) {
        this.id_apuesta = id_apuesta;
    }

    public String getId_usuario_mesa() {
        return id_usuario_mesa;
    }

    public void setId_usuario_mesa(String id_usuario_mesa) {
        this.id_usuario_mesa = id_usuario_mesa;
    }

    public String getCantidad_fichas() {
        return cantidad_fichas;
    }

    public void setCantidad_fichas(String cantidad_fichas) {
        this.cantidad_fichas = cantidad_fichas;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }
}
