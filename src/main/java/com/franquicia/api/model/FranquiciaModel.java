package com.franquicia.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "franquicias")
public class FranquiciaModel {
    @Id
    private String id;
    private String nombre;

    public FranquiciaModel() {
    }

    public FranquiciaModel(String nombre) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Franquicia = { id = " + id + " , nombre = "+ nombre + " }";
    }
}
