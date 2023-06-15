package com.example.elperlanegra.modelos;

import java.util.List;

public class PedidoModel {
    String id;
    String fecha;
    String estado;
    List<ProductoModel> productoModelList;

    public PedidoModel() {
    }

    public PedidoModel(String id, String fecha, String estado , List<ProductoModel> productoModelList) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.productoModelList = productoModelList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public List<ProductoModel> getProductoModelList() {
        return productoModelList;
    }

    public void setProductoModelList(List<ProductoModel> productoModelList) {
        this.productoModelList = productoModelList;
    }


    public void setDocumentId(String documentId) {
    }
}
