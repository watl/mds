package com.example.medisut.mds;

/**
 * Created by MediSut on 8/14/2014.
 */
public class DatPopulation {
    private String marca;
    private String status;
    private String modelo;
    private String stock;
    private String cant;
    private String porc;
    private String objectId;

    public String getObjid() {
        return objectId;
    }

    public void setObjid(String objectId) {
        this.objectId = objectId;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public String getPorc() {
        return porc;
    }

    public void setPorc(String porc) {
        this.porc = porc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
