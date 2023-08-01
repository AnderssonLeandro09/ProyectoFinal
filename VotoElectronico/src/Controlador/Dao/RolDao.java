/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Dao;

import Controlador.Lista.ListaEnlazada;
import Modelo.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Stali
 */
public class RolDao implements interfazDao<Rol>{
    private final Connection connection;
    public RolDao() {
        connection = Conexion.getConnection();
    }
    

    @Override
    public ListaEnlazada<Rol> listar() {
        ListaEnlazada<Rol> cargos = new ListaEnlazada<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Rol");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rol cargo = new Rol();
                cargo.setIdRol(resultSet.getInt("idRol"));
                cargo.setNombre(resultSet.getString("nombre"));
                cargos.insertarNodo(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos;
    }

    @Override
    public Rol objeto(int id) {
        Rol cargo = new Rol();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Rol where idRol=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cargo.setIdRol(resultSet.getInt("idRol"));
                cargo.setNombre(resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargo;
    }

    @Override
    public boolean insertObject(Rol objeto) {
        boolean estado=false;
        Rol cargo = objeto;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into Rol(nombre) values (?)");
            preparedStatement.setString(1, cargo.getNombre());
            preparedStatement.executeUpdate();
            estado=true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado=false;
        }
        return estado;
    }

    @Override
    public boolean updateObject(Rol objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteObject(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
