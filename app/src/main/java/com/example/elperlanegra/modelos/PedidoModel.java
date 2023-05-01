package com.example.elperlanegra.modelos;

import java.util.Date;
import java.util.List;

public class PedidoModel {
    String id;
    Date fecha;
    //String cliente;
    String estado;
    List<ProductoModel> productos;

    public PedidoModel() {
    }

    public PedidoModel(String id, Date fecha, String estado, List<ProductoModel> productos) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.productos = productos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoModel> productos) {
        this.productos = productos;
    }
}
