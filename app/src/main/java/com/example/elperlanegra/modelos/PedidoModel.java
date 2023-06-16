package com.example.elperlanegra.modelos;

public class PedidoModel {
    String idPedido;
    String fecha;
    String estado;
    String montoTotal;
    String totalProducto;
    String precio;
    String cantidad;
    //List<ProductoModel> productoModelList;

    public PedidoModel() {
    }

    public PedidoModel(String idPedido, String fecha, String estado, String montoTotal /*, List<ProductoModel> productoModelList*/) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.montoTotal = montoTotal;
        //this.productoModelList = productoModelList;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
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

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

/*
    public List<ProductoModel> getProductoModelList() {
        return productoModelList;
    }

    public void setProductoModelList(List<ProductoModel> productoModelList) {
        this.productoModelList = productoModelList;
    }*/

    public String getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(String totalProducto) {
        this.totalProducto = totalProducto;
    }

    public void setDocumentId(String documentId) {
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
