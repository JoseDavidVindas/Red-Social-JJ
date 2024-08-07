/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.modelo;

/**
 *
 * @author Jose
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublicacionTO implements Serializable {

    private String usuarioNombre;
    private String descripcion;
    private List<byte[]> imagenes;

    public PublicacionTO() {
        imagenes = new ArrayList<>();
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<byte[]> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<byte[]> imagenes) {
        this.imagenes = imagenes;
    }
}
