/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Data;

import Activos.Logic.Activo;
import Activos.Logic.Bien;
import Activos.Logic.Categoria;
import Activos.Logic.Dependencia;
import Activos.Logic.Funcionario;
import Activos.Logic.Puesto;
import Activos.Logic.Solicitud;
import Activos.Logic.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorac
 */
public class Dao {

    private RelDatabase db;

    public Dao() {
        db = new RelDatabase();
    }

    //--------------------------HELPERS--------------------------------------
    private Funcionario getFuncionarioH(ResultSet rs) throws SQLException {
        Funcionario func = new Funcionario();
        try {
            func.setID(rs.getInt("ID"));
            func.setIdentificacion(rs.getString("identificacion"));
            func.setNombre(rs.getString("nombre"));
            return func;
        } catch (SQLException ex) {
            return new Funcionario();
        }
    }

    private Usuario getUsuarioH(ResultSet rs) throws SQLException, Exception {
        Usuario user = new Usuario();
        try {
            user.setID(rs.getInt("ID"));
            user.setCuenta(rs.getString("cuenta"));
            user.setPassword(rs.getString("password"));
            user.setRol(rs.getInt("rol"));
            int idfunc = rs.getInt("funcionario");
            user.setFuncionario(this.getFuncionario(idfunc));
            return user;
        } catch (SQLException ex) {
            return new Usuario();
        }
    }

    private Dependencia getDependenciaH(ResultSet rs) throws SQLException, Exception {
        Dependencia dependencia = new Dependencia();
        try {
            dependencia.setID(rs.getInt("ID"));
            dependencia.setNombre(rs.getString("nombre"));
            dependencia.setUbicacion(rs.getString("ubicacion"));
            int setfun = rs.getInt("administrador");
            dependencia.setAdministrador(this.getFuncionario(setfun));
            return dependencia;
        } catch (SQLException ex) {
            return new Dependencia();
        }
    }

    private Solicitud getSolicitudH(ResultSet rs) throws SQLException, Exception {
        Solicitud solicitud = new Solicitud();
        try {
            solicitud.setID(rs.getInt("ID"));
            solicitud.setComprobante(rs.getString("comprobante"));
            solicitud.setFecha(rs.getDate("fecha"));
            solicitud.setTipo(rs.getInt("tipo"));
            solicitud.setCantidad(rs.getInt("cantidad"));
            solicitud.setTotal(rs.getFloat("total"));
            solicitud.setEstado(rs.getInt("estado"));
            int setfun = rs.getInt("dependencia");
            solicitud.setDependencia(getDependencia(setfun));
            int setfun2 = rs.getInt("registrador");
            solicitud.setRegistrador(this.getFuncionario(setfun2));
            solicitud.setBienes(this.getBienes(solicitud));
            solicitud.setMotivoRechazo(rs.getString("motivoRechazo") == null ? "" : rs.getString("motivoRechazo"));
            return solicitud;
        } catch (SQLException ex) {
            return new Solicitud();
        }
    }

    private Bien getBienH(ResultSet rs) throws SQLException, Exception {
        Bien bien = new Bien();
        try {
            bien.setID(rs.getInt("ID"));
            bien.setMarca(rs.getString("marca"));
            bien.setModelo(rs.getString("modelo"));
            bien.setDescripcion(rs.getString("descripcion"));
            bien.setPrecio(rs.getFloat("precio"));
            bien.setCantidad(rs.getInt("cantidad"));
            bien.setSolicitud(getSolicitudH(rs));
            return bien;
        } catch (SQLException ex) {
            return new Bien();
        }
    }
    // METODOS -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Funcionario ----------------------------------------------------------------------------

    public Funcionario getFuncionario(int id) throws SQLException, Exception {
        Funcionario func = null;
        String sql = "select * from Funcionarios where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getFuncionarioH(rs);
        } else {
            return new Funcionario();
        }
    }

    public void addFuncionario(Funcionario func) throws Exception {
        String sql = "insert into Funcionarios(identificacion, nombre)"
                + " values('%s','%s')";

        sql = String.format(sql, func.getIdentificacion(), func.getNombre());

        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el funcionario");
        } else {
            func.setID(PK);
        }
    }

    public List<Funcionario> getFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "select * from Funcionarios";
        ResultSet rs = db.executeQuery(sql);
        Funcionario func = null;
        while (rs.next()) {
            func = new Funcionario();
            func.setID(rs.getInt("ID"));
            func.setIdentificacion(rs.getString("identificacion"));
            func.setNombre(rs.getString("nombre"));
            funcionarios.add(func);
        }

        return funcionarios;
    }

    public List<Funcionario> getFuncionariosFromDependencia(Dependencia depe) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "select f.`ID` ID,f.identificacion identificacion ,f.nombre nombre "
                + "from Funcionarios f, Dependencias d, Puestos p "
                + "where p.funcionario = f.`ID`and p.dependencia = d.`ID` and d.`ID` = %d group by ID";
        sql = String.format(sql, depe.getID());

        ResultSet rs = db.executeQuery(sql);
        Funcionario func = null;
        while (rs.next()) {
            func = new Funcionario();
            func.setID(rs.getInt("ID"));
            func.setIdentificacion(rs.getString("identificacion"));
            func.setNombre(rs.getString("nombre"));
            funcionarios.add(func);
        }

        return funcionarios;
    }

// Usuario ------------------------------------------------------------------------------
    public Usuario getUsuario(String cuenta, String pass) throws SQLException, Exception {
        Usuario user = null;
        String sql = "select * from Usuarios where cuenta= '%s' AND password = '%s'";
        sql = String.format(sql, cuenta, pass);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getUsuarioH(rs);
        } else {
            return new Usuario();
        }
    }
    
    public List<Usuario> getUsuarios() throws SQLException, Exception {
        String sql = "select * from Usuarios";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
        List<Usuario> usuarios = new ArrayList();
        while (rs.next()) {
            usuarios.add(getUsuarioH(rs));
        }
        return usuarios;
    }

    public void addUsuario(Usuario usuario) throws Exception {
        String sql = "insert into Usuarios(cuenta, password, rol, funcionario)"
                + " values('%s','%s',%d, %d)";

        sql = String.format(sql, usuario.getCuenta(), usuario.getPassword(),
                usuario.getRol(), usuario.getFuncionario().getID());
        int PK = db.executeUpdateWithKeys(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el usuario");
        } else {
            usuario.setID(PK);
        }

    }

// Dependencia ------------------------------------------------------------------------------
    public Dependencia getDependencia(int id) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select * from Dependencias where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return new Dependencia();
        }

    }

    public Dependencia getDependencia_fromFuncionario(int id) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select * from Dependencias where administrador = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return new Dependencia();
        }
    }

    public Dependencia getDependencia_fromFuncionarioV2(int id) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select d.`ID`,d.nombre,d.ubicacion,d.administrador "
                + "from Funcionarios f, Dependencias d, Puestos p "
                + "where p.funcionario = f.`ID` and p.dependencia = d.`ID` and f.`ID` = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return new Dependencia();
        }
    }

    public Dependencia getDependencia_FromNombre(String nombre) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select * from Dependencias where nombre ='" + nombre + "'";
//        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return new Dependencia();
        }
    }

    public void addDependencia(Dependencia dependencia) throws Exception {
        String sql = "insert into Dependencias(nombre,ubicacion,administrador)"
                + " values('%s','%s',%d)";

        sql = String.format(sql, dependencia.getNombre(), dependencia.getUbicacion(), dependencia.getAdministrador().getID());
        int PK = db.executeUpdateWithKeys(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Funcionario");
        } else {
            dependencia.setID(PK);
        }
    }

    public void updateDependencia(Dependencia dependencia) throws Exception {
        String sql = "update Dependencias set nombre='" + dependencia.getNombre() + "', ubicacion='" + dependencia.getUbicacion() + "', administrador= '" + dependencia.getAdministrador().getID() + "'  where id='" + dependencia.getID() + "'";
//        sql = String.format(sql, dependencia.getNombre(), dependencia.getUbicacion(), dependencia.getAdministrador().getID());
        int PK = db.executeUpdateWithKeys(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Funcionario");
        }
    }

    public List<Dependencia> getDependencias() throws SQLException, Exception {
        List<Dependencia> dependencias = new ArrayList<>();
        String sql = "select * from Dependencias";
//        sql = String.format(sql, depe.getID());
        ResultSet rs = db.executeQuery(sql);
//        Solicitud solicitud = null;
        while (rs.next()) {
            dependencias.add(getDependenciaH(rs));
        }
        return dependencias;
    }

    public void eliminarDependencia(int dependencia) throws SQLException {
        String sql = "delete from Dependencias where id ='" + dependencia + "'";
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe la dependencia");
        }
    }

// Bien ------------------------------------------------------------------------------
    public Bien getBien(int id) throws SQLException, Exception {
        Bien bien = null;
        String sql = "select * from Bienes where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return this.getBienH(rs);
        } else {
            return new Bien();
        }
    }

    public List<Bien> getBienes(Solicitud solicitud) throws SQLException {
        List<Bien> bienes = new ArrayList<>();
        String sql = "select * from Bienes where solicitud = %d";
        sql = String.format(sql, solicitud.getID());
        ResultSet rs = db.executeQuery(sql);
        Bien bien = null;
        while (rs.next()) {
            bien = new Bien();
            bien.setID(rs.getInt("ID"));
            bien.setMarca(rs.getString("marca"));
            bien.setModelo(rs.getString("modelo"));
            bien.setDescripcion(rs.getString("descripcion"));
            bien.setPrecio(rs.getFloat("precio"));
            bien.setCantidad(rs.getInt("cantidad"));
            bien.setSolicitud(solicitud);
            bienes.add(bien);
        }
        return bienes;
    }

    public void addBien(Bien bien) throws Exception {
        String sql = "insert into Bienes(marca,modelo,descripcion,precio,cantidad,solicitud)"
                + " values('%s','%s','%s',%f,%d,%d)";
        sql = String.format(sql, bien.getMarca(), bien.getModelo(), bien.getDescripcion(), bien.getPrecio(), bien.getCantidad(), bien.getSolicitud().getID());
        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Bien");
        } else {
            bien.setID(PK);
        }
    }

    public void deleteBien(Bien bien) throws Exception {
        String sql = "delete from Bienes where ID=%d";
        sql = String.format(sql, bien.getID());
        int PK = db.executeUpdate(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar el Bien");
        }
    }

    // Solicitud ------------------------------------------------------------------------------
    public Solicitud getSolicitud(int id) throws SQLException, Exception {
        Solicitud solicitud = null;
        String sql = "Select * from Solicitudes where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return this.getSolicitudH(rs);
        } else {
            return new Solicitud();
        }
    }

    public void addSolicitud(Solicitud solicitud) throws Exception {
        String sql = "insert into Solicitudes(comprobante,fecha,tipo,cantidad,total,estado,dependencia)"
                + " values('%s','%s', %d,%d,%f,%d,%d) ";

        sql = String.format(sql, solicitud.getComprobante(), solicitud.getFecha().toString(), solicitud.getTipo(), solicitud.getCantidad(), solicitud.getTotal(),
                solicitud.getEstado(), solicitud.getDependencia().getID());

        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar la Solicitud");
        } else {
            solicitud.setID(PK);
        }
    }

    public void updateSolicitud(Solicitud solicitud) throws Exception {
        String sql;
        if (solicitud.getRegistrador().getID() == -1) {
            sql = "update Solicitudes"
                    + " set comprobante = '%s',fecha = '%s',tipo = %d,"
                    + "cantidad = %d,total = %f,estado = %d,dependencia = %d,registrador = null"
                    + " where ID = %d";
            sql = String.format(sql, solicitud.getComprobante(), solicitud.getFecha(), solicitud.getTipo(),
                    solicitud.getCantidad(), solicitud.getTotal(), solicitud.getEstado(), solicitud.getDependencia().getID(),
                    solicitud.getID());
        } else if (solicitud.getRegistrador().getID() != 0) {
            sql = "update Solicitudes"
                    + " set comprobante = '%s',fecha = '%s',tipo = %d,"
                    + "cantidad = %d,total = %f,estado = %d,dependencia = %d,registrador = %d"
                    + " where ID = %d";
            sql = String.format(sql, solicitud.getComprobante(), solicitud.getFecha(), solicitud.getTipo(),
                    solicitud.getCantidad(), solicitud.getTotal(), solicitud.getEstado(), solicitud.getDependencia().getID(),
                    solicitud.getRegistrador().getID(), solicitud.getID());
        } else {
            if (solicitud.getEstado() != 1) {
                if (!solicitud.getMotivoRechazo().isEmpty()) {
                    sql = "update Solicitudes"
                            + " set motivoRechazo = '%s', estado= %d"
                            + " where ID = %d";
                    sql = String.format(sql, solicitud.getMotivoRechazo(), solicitud.getEstado(), solicitud.getID());
                } else {
                    sql = "update Solicitudes"
                            + " set estado= %d"
                            + " where ID = %d";
                    sql = String.format(sql, solicitud.getEstado(), solicitud.getID());
                }
            } else {
                sql = "update Solicitudes"
                        + " set comprobante = '%s',fecha = '%s',tipo = %d,"
                        + "cantidad = %d,total = %f,estado = %d,dependencia = %d"
                        + " where ID = %d";
                sql = String.format(sql, solicitud.getComprobante(), solicitud.getFecha(), solicitud.getTipo(),
                        solicitud.getCantidad(), solicitud.getTotal(), solicitud.getEstado(), solicitud.getDependencia().getID(),
                        solicitud.getID());
            }
        }
        int PK = db.executeUpdate(sql);
        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de actualizar la Solicitud");
        }
    }

    public List<Solicitud> getSolicitudes(Dependencia depe) throws SQLException, Exception {
        List<Solicitud> solicitudes = new ArrayList<>();
        String sql = "select * from Solicitudes where dependencia = %d";
        sql = String.format(sql, depe.getID());
        ResultSet rs = db.executeQuery(sql);
        Solicitud solicitud = null;
        while (rs.next()) {
            solicitudes.add(getSolicitudH(rs));
        }
        return solicitudes;
    }

    public void eliminarSolicitud(int solicitud) throws SQLException {
        //Borra los bienes enlazados a esa solicitud
        String sql = "delete from Bienes where solicitud = %d";
        sql = String.format(sql, solicitud);
        int count = db.executeUpdate(sql);

        //Borrar la solicitud
        String sqlS = "delete from Solicitudes where ID = %d";
        sqlS = String.format(sqlS, solicitud);
        int countB = db.executeUpdate(sqlS);
        if (countB == 0) {
            throw new SQLException("No existe registro con codigo de solicitud " + solicitud);
        }
        if (count == 0) {
            throw new SQLException("No existe el bien asociado con codigo de solicitud " + solicitud);
        }
    }

    public void eliminarBien(int bien) throws SQLException {
        String sql = "delete from Bienes where ID = %d";
        sql = String.format(sql, bien);
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe el bien  " + bien);
        }
    }

    // SOLITUDES FILTROS
    public List<Solicitud> SolitudesTipo(int tipo) throws SQLException, Exception {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "select * from Solicitudes where tipo = %d";
        sql = String.format(sql, tipo);
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            lista.add(this.getSolicitudH(rs));
        }
        return lista;
    }

    public List<Solicitud> SolitudesEstado(int estado) throws SQLException, Exception {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "select * from Solicitudes where estado = %d";
        sql = String.format(sql, estado);
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            lista.add(this.getSolicitudH(rs));
        }

        return lista;
    }

    public List<Solicitud> getByComprobante(String comprobante) throws Exception {
        List<Solicitud> lista = new ArrayList<>();
        String sql = "select * from Solicitudes where comprobante LIKE '%" + comprobante + "%'";
        //sql = String.format(sql, comprobante);
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            lista.add(this.getSolicitudH(rs));
        }

        return lista;
    }

    public Puesto getPuestoFromFuncionario(int id) throws SQLException, Exception {
        Puesto puesto = null;
        String sql = "select p.ID, p.nombre, p.funcionario, p.dependencia "
                + "from Puestos p , Funcionarios f "
                + "where p.`funcionario` = f.`ID` "
                + "and p.funcionario = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            puesto = new Puesto();
            puesto.setID(rs.getInt("ID"));
            puesto.setNombre(rs.getString("nombre"));
            int f = rs.getInt("funcionario");
            puesto.setFuncionario(this.getFuncionario(f));
            int p = rs.getInt("dependencia");
            puesto.setDependencia(this.getDependencia(p));

        }

        return puesto;
    }

    public List<Puesto> getPuestosDisponibles() throws SQLException, Exception {
        List<Puesto> puestos = new ArrayList<>();
        String sql = "select * from Puestos where funcionario is null";
        ResultSet rs = db.executeQuery(sql);
        Puesto puesto = null;
        while (rs.next()) {
            puesto = new Puesto();
            puesto.setID(rs.getInt("ID"));
            puesto.setNombre(rs.getString("nombre"));
            puesto.setFuncionario(null);
            int dep = rs.getInt("dependencia");
            puesto.setDependencia(this.getDependencia(dep));
            puestos.add(puesto);
        }
        return puestos;
    }

    public List<Puesto> getPuestosDisponiblesPorDependencia(Dependencia dep) throws SQLException {
        List<Puesto> puestos = new ArrayList<>();
        String sql = "select * from Puestos where dependencia = %d and funcionario is null";
        sql = String.format(sql, dep.getID());
        ResultSet rs = db.executeQuery(sql);
        Puesto puesto = null;
        while (rs.next()) {
            puesto = new Puesto();
            puesto.setID(rs.getInt("ID"));
            puesto.setNombre(rs.getString("nombre"));
            puesto.setFuncionario(null);
            puesto.setDependencia(dep);
            puestos.add(puesto);
        }
        return puestos;
    }

    public void contratar(Funcionario f, Puesto p) throws SQLException {
        String sql = "update Puestos set funcionario = %d where ID = %d";
        sql = String.format(sql, f.getID(), p.getID());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("ERROR");
        }
    }

    public Puesto getPuesto(int p) throws SQLException, Exception {
        Puesto puesto = null;
        String sql = "select * from Puestos where ID = %d";
        sql = String.format(sql, p);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            puesto = new Puesto();
            puesto.setID(p);
            puesto.setNombre(rs.getString("nombre"));
            int fun = rs.getInt("funcionario");
            puesto.setFuncionario(this.getFuncionario(fun));
            int depe = rs.getInt("dependencia");
            puesto.setDependencia(this.getDependencia(depe));
        }

        return puesto;
    }

    public void descontratar(Funcionario f, boolean bandera) throws SQLException {

        if (bandera) {
            String sql = "update Puestos set funcionario = null where funcionario = %d";
            sql = String.format(sql, f.getID());
            int count = db.executeUpdate(sql);
        }

        String sql2 = "delete from Funcionarios where ID = %d";
        sql2 = String.format(sql2, f.getID());
        int count2 = db.executeUpdate(sql2);

        if (count2 == 0) {
            throw new SQLException("ERROR");
        }
    }

    public void updateFuncionarioNombre(Funcionario f, String nombre) throws SQLException {
        String sql = "update Funcionarios set nombre = '%s' where ID = %d";
        sql = String.format(sql, nombre, f.getID());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("ERROR");
        }
    }

    public void updateFuncionarioPuesto(Funcionario f, Puesto p_nuevo, Puesto p_viejo) throws SQLException {

        String sql;
        String sqlB;
        int countb = 0;
        if (p_nuevo != null) {
            sql = "update Puestos set funcionario = %d where ID = %d";
            sql = String.format(sql, f.getID(), p_nuevo.getID());
            int countA = db.executeUpdate(sql);

            if (p_viejo != null) {
                sqlB = "update Puestos set funcionario = null where ID = %d";
                sqlB = String.format(sqlB, p_viejo.getID());
                countb = db.executeUpdate(sqlB);
            }

        } else {

            if (p_viejo != null) {
                sql = "update Puestos set funcionario = null where ID = %d";
                sql = String.format(sql, p_viejo.getID());
                int count = db.executeUpdate(sql);
                if (count == 0) {
                    throw new SQLException("ERROR");
                }

            }
        }

    }

    public boolean isUsuario(int id) throws SQLException {
        String sql = "select u.ID from Usuarios u,Funcionarios f where u.funcionario = f.ID and f.ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        return rs.next();
    }

    // CATEGORIA ------------------------------------------------------------------------------
    public void updateCategoria(Categoria cat) throws Exception {
      String sql = "update Categoria set descripcion = '%s' where ID = %d";
        sql = String.format(sql, cat.getDescripcion(), cat.getID());
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("ERROR");
        }
    }

    public void deleteCategoria(int cat) throws Exception {
        String sql = "delete from Categoria where ID = %d";
        sql = String.format(sql, cat);
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe la categoria  ");
        }
    }

    public void addCategoria(Categoria cat) throws Exception {
        String sql = "insert into Categoria(descripcion)"
                + " values('%s')";

        sql = String.format(sql, cat.getDescripcion());

        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar la descripcion");
        } else {
            cat.setID(PK);
        }
    }

    public Categoria getCategoria(int id) throws SQLException {
        Funcionario func = null;
        String sql = "select * from Categoria where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getCategoriaH(rs);
        } else {
            return new Categoria();
        }
    }

    public List<Categoria> getCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "select * from Categoria";
        ResultSet rs = db.executeQuery(sql);
        Categoria func = null;
        while (rs.next()) {
            categorias.add(this.getCategoriaH(rs));
        }

        return categorias;
    }

    private Categoria getCategoriaH(ResultSet rs) throws SQLException {
        Categoria cat = new Categoria();
        cat.setID(rs.getInt("ID"));
        cat.setDescripcion(rs.getString("descripcion"));
        return cat;
    }
    //--------------------------------------------------------------------------
        public List<Solicitud> getSolicitud() throws SQLException, Exception {
        List<Solicitud> solicitud = new ArrayList<>();

        String sql = "select * from Solicitudes";
        ResultSet rs = db.executeQuery(sql);
        Solicitud func = null;
        while (rs.next()) {
            solicitud.add(this.getSolicitudH(rs));
        }

        return solicitud;
    }

    public List<Bien> getBienes() throws SQLException, Exception {
        List<Bien> bienes = new ArrayList<>();

        String sql = "select* from Bienes";
        ResultSet rs = db.executeQuery(sql);
        Bien func = null;
        while (rs.next()) {
            bienes.add(this.getBienH(rs));
        }

        return bienes;
    }
    public List<Puesto> getPuestos(Dependencia dep) throws SQLException, Exception {
        List<Puesto> puestos = new ArrayList<>();
        String sql = "select * from Puestos where dependencia = %d";
        sql = String.format(sql, dep.getID());
        ResultSet rs = db.executeQuery(sql);
        Puesto puesto = null;
        while (rs.next()) {
            puesto = new Puesto();
            puesto.setID(rs.getInt("ID"));
            puesto.setNombre(rs.getString("nombre"));
            int f = rs.getInt("funcionario");
            puesto.setFuncionario(this.getFuncionario(f));
            puesto.setDependencia(dep);
            puestos.add(puesto);
        }
        return puestos;
    }
    public Dependencia getDependencia_fromFuncionarioV3(int id) throws SQLException, Exception {
        Dependencia dependencia = null;
        String sql = "select d.ID, d.nombre, d.ubicacion, d.administrador "
                + "from Dependencias d, Usuarios u, Funcionarios f, Puestos p "
                + "where u.funcionario = f.ID and d.ID = p.dependencia and f.ID = %d group by d.ID;";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getDependenciaH(rs);
        } else {
            return null;
        }
    }

     public void addActivo(Activo activo) throws Exception {
        String sql = "insert into Activos(codigo,descripcion,puesto,Categoria_ID)"
                + " values('%s','%s',%d,%d)";

        sql = String.format(sql, activo.getCodigo(), activo.getDescripcion(), activo.getPuesto().getID(), activo.getCategoria().getID());

        int PK = db.executeUpdateWithKeys(sql);

        if (PK == 0) {
            throw new Exception("Ocurrio un error al tratar de agregar la descripcion");
        } else {
            activo.setID(PK);
        }
    }
      private Activo getActivoH(ResultSet rs) throws SQLException, Exception {
        Activo activo = new Activo();
        try {
            activo.setID(rs.getInt("ID"));
            activo.setCodigo(rs.getString("codigo"));
            activo.setDescripcion(rs.getString("descripcion"));
            int cat = rs.getInt("Categoria_ID");
            activo.setCategoria(this.getCategoria(cat));
            int p = rs.getInt("puesto");
            activo.setPuesto(this.getPuesto(p));
        } catch (SQLException ex) {
            return new Activo();
        }
        return activo;
    }
          public Activo getActivo(int id) throws SQLException, Exception {
        Funcionario func = null;
        String sql = "select * from Activos where ID = %d";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return getActivoH(rs);
        } else {
            return new Activo();
        }
    }
              public List<Activo> getActivosFromDependencia(int depe) throws Exception {
        List<Activo> activos = new ArrayList<>();
        String sql = "select a.ID, a.codigo, a.descripcion,a.puesto, a.Categoria_ID from Activos a, Puestos p where p.dependencia = %d group by a.ID;";
        sql = String.format(sql, depe);
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            activos.add(this.getActivoH(rs));
        }
        return activos;
    }

    public List<Activo> getListaActivosPorCategoria(int id, String categoria) throws Exception {
        List<Activo> activos = new ArrayList<>();
        String sql = "select a.ID, a.codigo, a.descripcion, a.puesto, a.Categoria_ID from Puestos p, Dependencias d, Categoria c, Activos a where c.ID = a.Categoria_ID and p.dependencia = %d and c.descripcion = '%s' group by a.ID";
        sql = String.format(sql, id, categoria);
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            activos.add(this.getActivoH(rs));
        }
        return activos;
    }

    public void eliminarActivo(int id) throws SQLException {
        String sql = "delete from Activos where id = %d";
        sql = String.format(sql, id);
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe el activo");
        }
    }

 

    public void updateDescripcionActivo(int id_activo, String descrp) throws SQLException {
        String sql = "update Activos set descripcion = '%s' where id = %d";
        sql = String.format(sql, descrp, id_activo);
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe el activo");
        }
    }

    public void updateCategoriaActivo(int id_activo, int cat_id) throws SQLException {
        String sql = "update Activos set Categoria_ID = %d where id = %d";
        sql = String.format(sql, cat_id, id_activo);
        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe el activo");
        }
    }

    public void updatePuestoActivo(int id_activo, int p) throws SQLException {
        String sql;
        sql = "update Activos set puesto = %d where id = %d";
        sql = String.format(sql, p, id_activo);

        int count = db.executeUpdate(sql);
        if (count == 0) {
            throw new SQLException("No existe el activo");
        }
    }


}
