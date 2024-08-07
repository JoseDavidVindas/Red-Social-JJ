/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.controller;

import com.cci.modelo.UsuarioTO;
import com.cci.servicio.ServicioUsuario;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import javax.faces.bean.ViewScoped;


/**
 *
 * @author Usuario
 */
@ManagedBean(name = "buscarUsuarioController")
@RequestScoped

public class BuscarUsuarioController implements Serializable {
@ManagedProperty(value = "#{servicioUsuario}")
        private ServicioUsuario service;
        private List<UsuarioTO> usuarios; 
        private List<UsuarioTO> filteredUsuarios;
        private boolean globalFilterOnly;
        private String terminoBusqueda;

    public BuscarUsuarioController() {
    }

        @PostConstruct
        public void init() {
            globalFilterOnly = false;
            // Obtener todos los usuarios de la base de datos
            usuarios = service.buscarUsuarios(terminoBusqueda); // Llamar al método de búsqueda con un término vacío para obtener todos los usuarios
        }

        public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
            String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }

            UsuarioTO usuario = (UsuarioTO) value;
            return usuario.getNombre().toLowerCase().contains(filterText)
                    || usuario.getCorreo().toLowerCase().contains(filterText);
        }

        public void toggleGlobalFilter() {
            setGlobalFilterOnly(!isGlobalFilterOnly());
        }

        public List<UsuarioTO> getUsuarios() {
            return usuarios;
        }

        public void setUsuarios(List<UsuarioTO> usuarios) {
            this.usuarios = usuarios;
        }

        public List<UsuarioTO> getFilteredUsuarios() {
            return filteredUsuarios;
        }

        public void setFilteredUsuarios(List<UsuarioTO> filteredUsuarios) {
            this.filteredUsuarios = filteredUsuarios;
        }

        public boolean isGlobalFilterOnly() {
            return globalFilterOnly;
        }

        public void setGlobalFilterOnly(boolean globalFilterOnly) {
            this.globalFilterOnly = globalFilterOnly;
        }

    public ServicioUsuario getService() {
        return service;
    }

    public void setService(ServicioUsuario service) {
        this.service = service;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }
        
    }


