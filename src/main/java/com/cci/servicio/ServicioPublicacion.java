/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cci.servicio;


import com.cci.modelo.Documento;
import com.cci.modelo.Imagen;
import com.cci.modelo.Publicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author josem
 */
public class ServicioPublicacion extends Servicio implements CRUD<Publicacion> {

    @Override
    public Boolean insertar(Publicacion t) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ServicioArchivo servA = new ServicioArchivo();

        try {
            Conectar();

            String sql = "INSERT INTO publicacion (descripcion,usuario_id,numero_favoritos,categoria) VALUES (?,?,?,?)";
            stmt = getConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, t.getDescripcion());
            stmt.setInt(2, t.getUsuario().getId());
            stmt.setInt(3, t.getNumero_favoritos());
            stmt.setString(4, t.getCategoria());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int usuarioId = rs.getInt(1);
                    t.setId(usuarioId);
                    if (t.getDocumentos() != null) {
                        for (Documento documento : t.getDocumentos()) {
                            documento.setPublicacion(t);
                            servA.insertarDocumento(documento);
                        }
                    }
                    if (t.getImagenes() != null) {
                        for (Imagen imagen : t.getImagenes()) {
                            imagen.setPublicacion(t);
                            servA.insertarImagen(imagen);
                        }
                    }
                }
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

    @Override
    public Boolean modificar(Publicacion t) {
        return null;
    }

    @Override
    public Boolean eliminar(Publicacion t) {
        return null;
    }

    @Override
    public List<Publicacion> findAll() {
        return null;
    }

    @Override
    public Optional<Publicacion> findPK() {
        return null;
    }

    public List<Publicacion> findAll(int cantidadPublicaciones) throws ClassNotFoundException {
        List<Publicacion> publicaciones = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        ServicioUsuario servUsuario = new ServicioUsuario();
        ServicioArchivo servA = new ServicioArchivo();

        try {
            Conectar();
            // La consulta SQL se actualiza para usar un límite
            String query = "SELECT id, descripcion, usuario_id, fecha_publicacion, fecha_actualizacion, numero_favoritos, categoria "
                    + "FROM publicacion "
                    + "ORDER BY fecha_publicacion DESC "
                    + "LIMIT ?";

            stmt = getConexion().prepareStatement(query);
            stmt.setInt(1, cantidadPublicaciones);  // Límite
            rs = stmt.executeQuery();

            while (rs.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setId(rs.getInt("id"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                publicacion.setUsuario(servUsuario.usuarioPK(rs.getInt("usuario_id")));
                publicacion.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
                publicacion.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                publicacion.setNumero_favoritos(rs.getInt("numero_favoritos"));
                publicacion.setCategoria(rs.getString("categoria"));
                publicacion.setDocumentos(servA.buscarDocumento(publicacion));
                publicacion.setImagenes(servA.buscarImagen(publicacion));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return publicaciones;
    }
    
    public List<Publicacion> findAllByCategorias(String namecategoria) throws ClassNotFoundException {
        List<Publicacion> publicaciones = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        ServicioUsuario servUsuario = new ServicioUsuario();
        ServicioArchivo servA = new ServicioArchivo();

        try {
            Conectar();
            // La consulta SQL se actualiza para usar un límite
            String query = "SELECT id, descripcion, usuario_id, fecha_publicacion, fecha_actualizacion, numero_favoritos, categoria "
                    + "FROM publicacion "
                    + "WHERE categoria = ? "
                    + "ORDER BY fecha_publicacion DESC ";

            stmt = getConexion().prepareStatement(query);
            stmt.setString(1, namecategoria);
          
            rs = stmt.executeQuery();

            while (rs.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setId(rs.getInt("id"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                publicacion.setUsuario(servUsuario.usuarioPK(rs.getInt("usuario_id")));
                publicacion.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
                publicacion.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                publicacion.setNumero_favoritos(rs.getInt("numero_favoritos"));
                publicacion.setCategoria(rs.getString("categoria"));
                publicacion.setDocumentos(servA.buscarDocumento(publicacion));
                publicacion.setImagenes(servA.buscarImagen(publicacion));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return publicaciones;
    }

    public List<Publicacion> findAllPublicaciones(int id) throws ClassNotFoundException {
        List<Publicacion> publicaciones = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        ServicioUsuario servUsuario = new ServicioUsuario();
        ServicioArchivo servA = new ServicioArchivo();

        try {
            Conectar();
            // La consulta SQL se actualiza para usar un límite
            String query = "SELECT id, descripcion, usuario_id, fecha_publicacion, fecha_actualizacion, numero_favoritos, categoria "
                    + "FROM publicacion "
                    + "WHERE usuario_id = ? "
                    + "ORDER BY fecha_publicacion DESC";

            stmt = getConexion().prepareStatement(query);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setId(rs.getInt("id"));
                publicacion.setDescripcion(rs.getString("descripcion"));
                publicacion.setUsuario(servUsuario.usuarioPK(rs.getInt("usuario_id")));
                publicacion.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
                publicacion.setFecha_actualizacion(rs.getTimestamp("fecha_actualizacion"));
                publicacion.setNumero_favoritos(rs.getInt("numero_favoritos"));
                publicacion.setCategoria(rs.getString("categoria"));
                publicacion.setDocumentos(servA.buscarDocumento(publicacion));
                publicacion.setImagenes(servA.buscarImagen(publicacion));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CerrarResultSet(rs);
            CerrarStatement(stmt);
            Desconectar();
        }
        return publicaciones;
    }

    public Boolean eliminarPublicacion(Publicacion t) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        boolean exito = false;
        ServicioArchivo servA = new ServicioArchivo();

        try {
            Conectar();
            String sql = "DELETE FROM publicacion WHERE id = ?";
            stmt = getConexion().prepareStatement(sql);
            stmt.setInt(1, t.getId());

            servA.eliminarDocumento(t);
            servA.eliminarImagen(t);
            servA.eliminarFavorito(t);
            
            int filasAfectadas = stmt.executeUpdate();
            exito = filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CerrarStatement(stmt);
            Desconectar();
        }
        return exito;
    }

    public int obtenerReaccion(int publicacionId, int usuarioId) throws ClassNotFoundException {
    int reaccion = 0;
    ResultSet rs = null;
    PreparedStatement stmt = null;

    try {
        Conectar();
        String query = "SELECT reaccion FROM reacciones WHERE publicacion_id = ? AND usuario_id = ?";

        stmt = getConexion().prepareStatement(query);
        stmt.setInt(1, publicacionId);
        stmt.setInt(2, usuarioId);
        rs = stmt.executeQuery();

        if (rs.next()) {
            reaccion = rs.getInt("reaccion");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        CerrarResultSet(rs);
        CerrarStatement(stmt);
        Desconectar();
    }

    return reaccion;
}

public void actualizarReaccion(int publicacionId, int usuarioId, int nuevaReaccion) throws ClassNotFoundException {
    PreparedStatement stmt = null;

    try {
        Conectar();
        String query = "INSERT INTO reacciones (publicacion_id, usuario_id, reaccion) VALUES (?, ?, ?) "
                     + "ON DUPLICATE KEY UPDATE reaccion = ?";

        stmt = getConexion().prepareStatement(query);
        stmt.setInt(1, publicacionId);
        stmt.setInt(2, usuarioId);
        stmt.setInt(3, nuevaReaccion);
        stmt.setInt(4, nuevaReaccion);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        CerrarStatement(stmt);
        Desconectar();
    }
}

public int likePublicacion(int publicacionId, int usuarioId) throws ClassNotFoundException {
    int reaccionActual = obtenerReaccion(publicacionId, usuarioId);
    int nuevaReaccion = 1;

    if (reaccionActual == 1) {
        nuevaReaccion = 0;
    } else if (reaccionActual == 2 || reaccionActual == 0) {
        nuevaReaccion = 1;
    }

    actualizarReaccion(publicacionId, usuarioId, nuevaReaccion);
    return nuevaReaccion;
}

public int dislikePublicacion(int publicacionId, int usuarioId) throws ClassNotFoundException {
    int reaccionActual = obtenerReaccion(publicacionId, usuarioId);
    int nuevaReaccion = 2;

    if (reaccionActual == 2) {
        nuevaReaccion = 0;
    } else if (reaccionActual == 1 || reaccionActual == 0) {
        nuevaReaccion = 2;
    }

    actualizarReaccion(publicacionId, usuarioId, nuevaReaccion);
    return nuevaReaccion;
}

}
