/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Dao;

import Controlador.Lista.ListaEnlazada;
import Controlador.busqueda.BusquedaBinaria;
import Controlador.busqueda.BusquedaLinealBinaria;
import Modelo.Comparator;
import Modelo.Cuenta;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Stali
 */
public class CuentaDao implements interfazDao<Cuenta> {

    private final Connection connection;

    public CuentaDao() {
        connection = Conexion.getConnection();
    }

    @Override
    public ListaEnlazada<Cuenta> listar() {
        ListaEnlazada<Cuenta> cuentas = new ListaEnlazada<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from cuenta");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(resultSet.getInt("idCuenta"));
                cuenta.setUsuario(resultSet.getString("usuario"));
                cuenta.setCorreo(resultSet.getString("correo"));
                cuenta.setClave(resultSet.getString("clave"));
                cuenta.setIdRol(resultSet.getInt("idRol"));
                cuentas.insertarNodo(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    @Override
    public Cuenta objeto(int id) {
        Cuenta cuenta = new Cuenta();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from cuenta where idCuenta=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cuenta.setIdCuenta(resultSet.getInt("idCuenta"));
                cuenta.setUsuario(resultSet.getString("usuario"));
                cuenta.setCorreo(resultSet.getString("correo"));
                cuenta.setClave(resultSet.getString("clave"));
                cuenta.setIdRol(resultSet.getInt("idRol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuenta;
    }

    @Override
    public boolean insertObject(Cuenta objeto) {
        boolean estado = false;
        Cuenta cuenta = objeto;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into cuenta(usuario,correo,clave,idRol) values (?,?,?,?)");
            preparedStatement.setString(1, cuenta.getUsuario());
            preparedStatement.setString(2, cuenta.getCorreo());
            preparedStatement.setString(3, cuenta.getClave());
            preparedStatement.setInt(4, cuenta.getIdRol());
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        }
        return estado;
    }

    @Override
    public boolean updateObject(Cuenta objeto) {
        boolean estado = false;
        Cuenta cuenta = objeto;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update cuenta set correo=? where idCuenta=?");
            preparedStatement.setString(1, cuenta.getUsuario());
            preparedStatement.setString(2, cuenta.getCorreo());
            preparedStatement.setString(3, cuenta.getClave());
            preparedStatement.setInt(4, cuenta.getIdCuenta());
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        }
        return estado;
    }

    @Override
    public boolean deleteObject(int id) {
        boolean estado = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from cuenta where idCuenta=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        }
        return estado;
    }

    public ListaEnlazada<Cuenta> BusquedaPorUsuario(String dato, int metodoBusqueda) throws VacioException, PosicionException {
        ListaEnlazada<Cuenta> resultados = new ListaEnlazada<>();
        ListaEnlazada<Cuenta> lista = listar();

        Comparator<Cuenta> generoComparator = new Comparator<Cuenta>() {
            @Override
            public int compare(Cuenta a, Cuenta b) {
                return a.getUsuario().compareToIgnoreCase(b.getUsuario());
            }
        };

        switch (metodoBusqueda) {
            case 0:
                BusquedaBinaria<Cuenta> busquedaBinaria = new BusquedaBinaria<>(generoComparator);
                Cuenta buscado = new Cuenta();
                buscado.setUsuario(dato);
                int index = busquedaBinaria.search(lista, buscado);

                if (index != -1) {
                    resultados.insertarNodo(lista.get(index));
                }
                break;
            case 1:
                BusquedaLinealBinaria<Cuenta> busquedaLinealBinaria = new BusquedaLinealBinaria<>(generoComparator);
                ListaEnlazada<Cuenta> keys = new ListaEnlazada<>();
                Cuenta compositorKey = new Cuenta();
                compositorKey.setUsuario(dato);
                keys.insertarNodo(compositorKey);
                resultados = busquedaLinealBinaria.search(lista, keys);
                break;
            default:
                throw new IllegalArgumentException("Método de búsqueda no válido.");
        }

        return resultados;
    }

    public Cuenta BuscarporID(String Usuario) throws VacioException, PosicionException {
        ListaEnlazada<Cuenta> lista = listar();
        Comparator<Cuenta> generoComparator = new Comparator<Cuenta>() {
            @Override
            public int compare(Cuenta a, Cuenta b) {
                return a.getUsuario().compareToIgnoreCase(b.getUsuario());
            }
        };
        BusquedaBinaria<Cuenta> busquedaBinaria = new BusquedaBinaria<>(generoComparator);
        Cuenta buscado = new Cuenta();
        buscado.setUsuario(Usuario);
        int index = busquedaBinaria.search(lista, buscado);
        return lista.get(index);
    }
}
