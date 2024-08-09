/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.controller;

import com.cci.modelo.UsuarioTO;
import com.cci.servicio.ServicioUsuario;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;


/**
 *
 * @author Usuario
 */
@ManagedBean(name = "buscarUsuarioController")
@ViewScoped
public class BuscarUsuarioController implements Serializable {

        //private ServicioUsuario service;
        private List<UsuarioTO> usuarios; 
        private List<UsuarioTO> usuariosFiltrados;
        private String terminoBusqueda;
       
         
    public BuscarUsuarioController() {
    }
    
     @PostConstruct
    public void init() {
         ServicioUsuario servicioUsuario = new ServicioUsuario();
        if (terminoBusqueda == null || terminoBusqueda.isEmpty()) {
            // Cargar todos los usuarios
            usuarios = servicioUsuario.buscarUsuarios("");
        } else {
            // Buscar usuarios según el término de búsqueda
            usuarios = servicioUsuario.buscarUsuarios(terminoBusqueda);
        }
        usuariosFiltrados = new ArrayList<>(usuarios);
    }

    public List<UsuarioTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<UsuarioTO> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<UsuarioTO> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }
    
    
    
    
}