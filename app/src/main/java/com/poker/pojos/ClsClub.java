package com.poker.pojos;

public class ClsClub {
    private String nombre;
    private String id_club;

    public ClsClub(String nombre, String id_club) {
        this.nombre = nombre;
        this.id_club = id_club;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_club() {
        return id_club;
    }

    public void setId_club(String id_club) {
        this.id_club = id_club;
    }
}
