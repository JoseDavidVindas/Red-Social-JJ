/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.controller;

import com.cci.modelo.Archivo;
import com.cci.modelo.Documento;
import com.cci.modelo.Imagen;
import com.cci.modelo.PublicacionTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Jose
 */
public class PublisController {
      private List<Archivo> archivos;
    private Archivo archivo;
    List<UploadedFile> files;
    private Documento documento;
    private Imagen imagen;
    private List<Documento> documentos;
    private List<Imagen> imagenes;
 
@ManagedBean(name = "publicacionController")
@SessionScoped
public class PublicacionController implements Serializable {

    private List<PublicacionTO> publicaciones;
    private PublicacionTO nuevaPublicacion;

     public void handleFileUploadEvent(FileUploadEvent event) throws IOException {
        System.out.println("===>>> " + event.getFile().getFileName() + " size: " + event.getFile().getSize());
        documento = new Documento();
        imagen = new Imagen();
        this.copyFile(event.getFile().getFileName(), event.getFile().getInputStream(), false);

    }
    public PublicacionController() {
        publicaciones = new ArrayList<>();
        nuevaPublicacion = new PublicacionTO();
    }

    public List<PublicacionTO> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionTO> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public PublicacionTO getNuevaPublicacion() {
        return nuevaPublicacion;
    }

    public void setNuevaPublicacion(PublicacionTO nuevaPublicacion) {
        this.nuevaPublicacion = nuevaPublicacion;
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (nuevaPublicacion.getImagenes().size() < 10) {
            nuevaPublicacion.getImagenes().add(file.getContent()); 
        }
    }

    public void crearPublicacion() {
        publicaciones.add(nuevaPublicacion);
        nuevaPublicacion = new PublicacionTO(); 
    }
   
        private void copyFile(String fileName, InputStream inputStream, boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }


}
