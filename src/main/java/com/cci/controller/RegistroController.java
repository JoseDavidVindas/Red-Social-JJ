/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.controller;

/**
 *
 * @author Jose
 */
import com.cci.modelo.UsuarioTO;
import com.cci.servicio.ServicioUsuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "registroController")
@RequestScoped
public class RegistroController {
    private int pais;
    private int rol; //1 para profesor, 2 para estudiante
    private UsuarioTO usuario = new UsuarioTO();
    
    public void registrarUsuario() {
       
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        usuario.setRol(rol);
        usuario.setPais(pais);
        boolean registrado = servicioUsuario.insertar(usuario);
        
        if (registrado) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El usuario ha sido registrado con éxito."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el registro", "Hubo un error al registrar el usuario."));
        }
    }
    
    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }
    
    
    
    public void volver() {
        this.redireccionar("/IniciarSesion.xhtml");
    }
    
     // Getters y Setters
    public UsuarioTO getUsuario() {
        return usuario;
    }
    
    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }
    
}
