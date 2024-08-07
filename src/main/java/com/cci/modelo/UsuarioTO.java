/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Jefferson
 */
public class UsuarioTO implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String correo;
    private String contrasena;
    private String nombre;       
    private int rol;  
    //private String fotoPerfil;
    private Date fechaRegistro;
    private int pais;
    

    public UsuarioTO(int id, String correo, String contrasena, String nombre, int rol, Date fechaRegistro, int pais) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
        this.pais = pais;
    }

    

    public UsuarioTO() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    

    /*public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }*/

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.correo);
        hash = 53 * hash + Objects.hashCode(this.contrasena);
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + this.rol;
        hash = 53 * hash + Objects.hashCode(this.fechaRegistro);
        hash = 53 * hash + this.pais;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioTO other = (UsuarioTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.rol != other.rol) {
            return false;
        }
        if (this.pais != other.pais) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.contrasena, other.contrasena)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.fechaRegistro, other.fechaRegistro);
    }

    @Override
    public String toString() {
        return "UsuarioTO{" + "id=" + id + ", correo=" + correo + ", contrasena=" + contrasena + ", nombre=" + nombre + ", rol=" + rol + ", fechaRegistro=" + fechaRegistro + ", pais=" + pais + '}';
    }

   
    
}