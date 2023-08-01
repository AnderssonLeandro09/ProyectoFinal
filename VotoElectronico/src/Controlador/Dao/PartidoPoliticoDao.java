/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Dao;

import Controlador.Lista.ListaEnlazada;
import Modelo.Enum.Genero;
import Modelo.PartidoPolitico;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Stali
 */
public class PartidoPoliticoDao implements interfazDao<PartidoPolitico>{
    private final Connection connection;
    public PartidoPoliticoDao() {
        connection = Conexion.getConnection();
    }
    

    @Override
    public ListaEnlazada<PartidoPolitico> listar() {
         ListaEnlazada<PartidoPolitico> personas = new ListaEnlazada<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from PartidoPolitico");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PartidoPolitico partidoP = new PartidoPolitico();
                partidoP.setIdPartidoPolitico(resultSet.getInt("idPartidoPolitico"));
                partidoP.setNombre(resultSet.getString("nombre"));
                partidoP.setFundacion(resultSet.getDate("fundacion"));
                partidoP.setLogo(resultSet.getBlob("logo"));
                personas.insertarNodo(partidoP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public PartidoPolitico objeto(int id) {
        PartidoPolitico persona = new PartidoPolitico();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from PartidoPolitico where idPartidoPolitico=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                persona.setIdPartidoPolitico(resultSet.getInt("idPartidoPolitico"));
                persona.setNombre(resultSet.getString("nombre"));
                persona.setFundacion(resultSet.getDate("fundacion"));
                persona.setLogo(resultSet.getBlob("logo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    @Override
    public boolean insertObject(PartidoPolitico objeto) {
        PartidoPolitico persona = objeto;
        boolean estado = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into PartidoPolitico(nombre,fundacion"
                    + ",logo) values (?,?,?)");
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setDate(2, persona.getFundacion());
            preparedStatement.setBlob(3, persona.getLogo());
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    @Override
    public boolean updateObject(PartidoPolitico objeto) {
        boolean estado = false;
        PartidoPolitico persona = objeto;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update PartidoPolitico set nombre=?,fundacion=?"
                            + ",logo=?"
                            + "where idPartidoPolitico=?");
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setDate(2, persona.getFundacion());
            preparedStatement.setBlob(3, persona.getLogo());
            preparedStatement.setInt(4, persona.getIdPartidoPolitico());
            preparedStatement.executeUpdate();
            estado=true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado=false;
        }
        return estado;
    }

    @Override
    public boolean deleteObject(int id) {
        boolean estado =false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from PartidoPolitico where idPartidoPolitico=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado=false;
        }
        return estado;
    }  
}
