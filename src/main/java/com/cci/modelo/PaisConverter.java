/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.modelo;

import com.cci.servicio.ServicioPais;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Usuario
 */
/**
 * Conversor para convertir entre el ID de un país y el objeto PaisTO.
 */
@FacesConverter("paisConverter")
public class PaisConverter implements Converter {

    private ServicioPais servicioPais = new ServicioPais(); // Asegúrate de que este servicio esté bien implementado

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            int id = Integer.parseInt(value);
            return servicioPais.obtenerPaisPorId(id); // Método para obtener el objeto PaisTO por su id
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Pais id: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if (object == null) {
            return "";
        }

        if (object instanceof PaisTO) {
            return String.valueOf(((PaisTO) object).getId());
        } else {
            throw new IllegalArgumentException("Object is not an instance of PaisTO");
        }
    }
}
