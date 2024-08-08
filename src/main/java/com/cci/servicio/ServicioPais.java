/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.servicio;

import com.cci.modelo.PaisTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "servicioPais")
@RequestScoped
public class ServicioPais extends Servicio{
    
        public List<PaisTO> obtenerTodosLosPaises() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PaisTO> paises = new ArrayList<>();

        try {
            Conectar();

            String sql = "SELECT * FROM pais"; // Asegúrate de que esta tabla existe y tiene los datos necesarios
            stmt = getConexion().prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String imagen = rs.getString("imagen");
                paises.add(new PaisTO(id, nombre, imagen));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return paises;
    }
        
        public PaisTO obtenerPaisPorId(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PaisTO pais = null;

        try {
            Conectar();

            String sql = "SELECT * FROM pais WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String imagen = rs.getString("imagen");
                pais = new PaisTO(id, nombre, imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return pais;
    }
        
        
}
