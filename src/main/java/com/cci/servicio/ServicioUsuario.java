/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.servicio;



import com.cci.modelo.UsuarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Usuario
 */
public class ServicioUsuario extends Servicio implements CRUD<UsuarioTO> {

    public UsuarioTO validarUsuario(String correo, String contrasena) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioTO usuarioTORetorno = null;

        try {

            Conectar();

            String sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String mail = rs.getString("correo");
                String nom = rs.getString("nombre");
                String pass = rs.getString("contrasena");
                String bio = rs.getString("biografia");

                usuarioTORetorno = new UsuarioTO(id, mail, pass, nom, bio);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return usuarioTORetorno;
    }

    @Override
    public Boolean insertar(UsuarioTO t) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Conectar();

            String sql = "INSERT INTO usuario (nombre,correo,contrasena) VALUES (?,?,?)";
            stmt = getConexion().prepareStatement(sql);
            stmt.setString(1, t.getNombre());
            stmt.setString(2, t.getCorreo());
            stmt.setString(3, t.getContrasena());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
    }

    public UsuarioTO usuarioPK(int idU) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioTO usuarioTORetorno = null;

        try {

            Conectar();

            String sql = "SELECT * FROM usuario WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setInt(1, idU);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String mail = rs.getString("correo");
                String nom = rs.getString("nombre");
                String pass = rs.getString("contrasena");

                usuarioTORetorno = new UsuarioTO(id, mail, pass, nom);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return usuarioTORetorno;
    }

    public Boolean actualizarBiografia(int idUsuario, String biografia) {
        PreparedStatement stmt = null;
        try {
            Conectar();
            String sql = "UPDATE usuario SET biografia = ? WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setString(1, biografia);
            stmt.setInt(2, idUsuario);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            CerrarStatement(stmt);
            Desconectar();
        }
    }

    public Boolean actualizarFotoPerfil(int idUsuario, String urlFoto) {
        PreparedStatement stmt = null;
        try {
            Conectar();
            String sql = "UPDATE usuario SET foto_perfil = ? WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setString(1, urlFoto);
            stmt.setInt(2, idUsuario);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            CerrarStatement(stmt);
            Desconectar();
        }
    }

    public List<UsuarioTO> buscarUsuarios(String terminoBusqueda) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UsuarioTO> usuarios = new ArrayList<>();

        try {
            Conectar();

            // La consulta utiliza LIKE para buscar coincidencias parciales
            String sql = "SELECT * FROM usuario WHERE nombre LIKE ?";
            stmt = getConexion().prepareStatement(sql);
            // Usamos el término de búsqueda con '%' para buscar coincidencias parciales
            String termino = "%" + terminoBusqueda + "%";
            stmt.setString(1, termino);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mail = rs.getString("correo");
                String nom = rs.getString("nombre");
                String pass = rs.getString("contrasena");
                String bio = rs.getString("biografia");

                UsuarioTO usuarioTO = new UsuarioTO(id, mail, pass, nom, bio);
                usuarios.add(usuarioTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return usuarios;
    }

  
    @Override
    public Boolean modificar(UsuarioTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean eliminar(UsuarioTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<UsuarioTO> findPK() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
