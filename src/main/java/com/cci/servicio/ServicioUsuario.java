/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.servicio;

import com.cci.modelo.Rol;
import com.cci.modelo.PaisTO;
import com.cci.modelo.UsuarioTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import com.cci.modelo.PaisTO;

import com.cci.modelo.PaisTO;

/**
 *
 * @author Usuario
 */
public class ServicioUsuario extends Servicio implements CRUD<UsuarioTO> {

    private ServicioPais servicioPais = new ServicioPais();

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
                int rol = rs.getInt("rol");
                Date fechR = rs.getDate("fecha_registro");
                int idPais = rs.getInt("fk_pais");
                PaisTO pais = servicioPais.obtenerPaisPorId(idPais);
                usuarioTORetorno = new UsuarioTO(id, mail, pass, nom, rol, fechR, pais);

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

            String sql = "INSERT INTO usuario (nombre, correo, contrasena, fecha_registro, rol, fk_pais) VALUES (?,?,?,?,?,?)";
            stmt = getConexion().prepareStatement(sql);
            stmt.setString(1, t.getNombre());
            stmt.setString(2, t.getCorreo());
            stmt.setString(3, t.getContrasena());
            stmt.setDate(4, new java.sql.Date(System.currentTimeMillis())); // Establece la fecha actual
            stmt.setInt(5, t.getRol());
            stmt.setInt(6, t.getPais() != null ? t.getPais().getId() : null); // Establece el país si está disponible
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

    /* public UsuarioTO usuarioPK(int idU) {
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
                int rol = rs.getInt("rol");
                Date fechR = rs.getDate("fecha_registro");
                int pais = rs.getInt("pais");
                usuarioTORetorno = new UsuarioTO(id, mail, pass, nom, rol, fechR, pais);

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
    }*/
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

    /*public List<UsuarioTO> buscarUsuarios(String terminoBusqueda) {
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
                int rol = rs.getInt("rol");
                Date fechR = rs.getDate("fecha_registro");
<<<<<<< HEAD
                int pais = rs.getInt("pais");
                UsuarioTO usuarioTO = new UsuarioTO(id, mail, pass, nom, rol);
=======
                int idPais = rs.getInt("fk_pais");
                PaisTO pais = servicioPais.obtenerPaisPorId(idPais);
                UsuarioTO usuarioTO = new UsuarioTO(id, mail, pass, nom, rol, fechR, pais);
>>>>>>> 2ecb612d2b2ff73474e589e4475b834a29b89747
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
<<<<<<< HEAD
    }
    
    public Rol rolPK(int idR) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Rol rol = null;

        try {

            Conectar();

            String sql = "SELECT * FROM rol WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setInt(1, idR);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nombre");

                rol = new Rol(id, nom);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return rol;
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
                int rol = rs.getInt("rol_id");
                String pass = rs.getString("contrasena");
                String foto = rs.getString("foto_perfil");

                usuarioTORetorno = new UsuarioTO(id, mail, pass, nom, rol);
                usuarioTORetorno.setFotoPerfil(foto);

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
=======
    }*/
    public List<UsuarioTO> buscarUsuarios(String terminoBusqueda) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UsuarioTO> usuarios = new ArrayList<>();

        try {
            Conectar();

            // La consulta ahora se une a la tabla 'pais' para obtener la URL de la imagen de la bandera
            String sql = "SELECT u.*, p.imagen, p.nombre FROM usuario u "
                    + "JOIN pais p ON u.fk_pais = p.id "
                    + "WHERE u.nombre LIKE ?";
            stmt = getConexion().prepareStatement(sql);
            String termino = "%" + terminoBusqueda + "%";
            stmt.setString(1, termino);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mail = rs.getString("correo");
                String nom = rs.getString("nombre");
                String pass = rs.getString("contrasena");
                int rol = rs.getInt("rol");
                Date fechR = rs.getDate("fecha_registro");
                String imagen = rs.getString("imagen");  // Obtiene la URL de la imagen de la bandera
                String nombrePais = rs.getString("p.nombre");
                PaisTO pais = new PaisTO(rs.getInt("fk_pais"), nombrePais, imagen);
                UsuarioTO usuarioTO = new UsuarioTO(id, mail, pass, nom, rol, fechR, pais);
                usuarios.add(usuarioTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return usuarios;
    }
    
    public Rol rolPK(int idR) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Rol rol = null;

        try {

            Conectar();

            String sql = "SELECT * FROM rol WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setInt(1, idR);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nombre");

                rol = new Rol(id, nom);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Paso 5
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return rol;
    }

    public void actualizarUsuariosConPais() {
        String sql = "UPDATE usuario SET fk_pais = ? WHERE id = ?";

        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            // Establece el ID del país que deseas asignar (ejemplo: Argentina con ID 1)
            int idPais = 1;

            // Consulta todos los usuarios que necesiten ser actualizados
            String selectSql = "SELECT id FROM usuario WHERE fk_pais IS NULL";
            try (PreparedStatement selectStmt = getConexion().prepareStatement(selectSql); ResultSet rs = selectStmt.executeQuery()) {

                // Recorre todos los usuarios que necesitan actualización
                while (rs.next()) {
                    int usuarioId = rs.getInt("id");

                    // Actualiza el país para cada usuario
                    ps.setInt(1, idPais); // Establece el ID del país
                    ps.setInt(2, usuarioId); // Establece el ID del usuario

                    int filasActualizadas = ps.executeUpdate();
                    if (filasActualizadas > 0) {
                        System.out.println("Usuario con ID " + usuarioId + " actualizado con país ID " + idPais);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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

    UsuarioTO usuarioPK(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
