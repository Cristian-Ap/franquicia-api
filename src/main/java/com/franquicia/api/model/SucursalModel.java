package com.franquicia.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "sucursales")
public class SucursalModel {
    @Id
    private String id;
    private String nombre;
    private String idFranquicia;

    public SucursalModel() {
    }

    public SucursalModel(String nombre, String idFranquicia) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.idFranquicia = idFranquicia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdFranquicia() {
        return idFranquicia;
    }

    public void setIdFranquicia(String idFranquicia) {
        this.idFranquicia = idFranquicia;
    }

    @Override
    public String toString() {
        return "Sucursal = { id = " + id + ", nombre = " + nombre + " , idFranquicia = " + idFranquicia + "}";
    }
}
