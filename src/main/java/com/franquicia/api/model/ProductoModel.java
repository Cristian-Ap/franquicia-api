package com.franquicia.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "productos")
public class ProductoModel {
    @Id
    private String id;
    private String nombre;
    private int stock;
    private String idSucursal;

    public ProductoModel() {
    }

    public ProductoModel(String nombre, int stock, String idSucursal) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.stock = stock;
        this.idSucursal = idSucursal;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return "Producto = { id = " + id + " , nombre = " + nombre + " , idSucursal = " + idSucursal+ " }";
    }
}
