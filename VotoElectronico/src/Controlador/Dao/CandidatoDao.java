/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Dao;

import Controlador.Lista.ListaEnlazada;
import Modelo.Candidato;
import Modelo.Enum.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Stali
 */
public class CandidatoDao implements interfazDao<Candidato>{
    private final Connection connection;

    public CandidatoDao() {
        connection = Conexion.getConnection();
    }
    

    @Override
    public ListaEnlazada<Candidato> listar() {
         ListaEnlazada<Candidato> personas = new ListaEnlazada<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Candidato");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Candidato partidoP = new Candidato();
                partidoP.setIdPartidoPolitico(resultSet.getInt("idCandidato"));
                String enumValue = resultSet.getString("cargo");
                Cargo cargoc = Cargo.valueOf(enumValue);                
                partidoP.setCargo(cargoc);
                partidoP.setEstado(resultSet.getByte("estado"));
                partidoP.setImagen(resultSet.getBlob("imagen"));
                partidoP.setIdPersona(resultSet.getInt("idPersona"));
                partidoP.setIdPartidoPolitico(resultSet.getInt("idPartidoPolitico"));
                personas.insertarNodo(partidoP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public Candidato objeto(int id) {
        Candidato persona = new Candidato();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Candidato where idCandidato=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                persona.setIdCandidato(resultSet.getInt("idCandidato"));
                String enumValue = resultSet.getString("cargo");
                Cargo cargoc = Cargo.valueOf(enumValue);                 
                persona.setCargo(cargoc);
                persona.setEstado(resultSet.getByte("estado"));
                persona.setImagen(resultSet.getBlob("imagen"));
                persona.setIdPersona(resultSet.getInt("idPersona"));
                persona.setIdPartidoPolitico(resultSet.getInt("idPartidoPolitico"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    @Override
    public boolean insertObject(Candidato objeto) {
        Candidato persona = objeto;
        boolean estado = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into candidato(cargo,estado,imagen"
                    + ",idPersona,idPartidoPolitico) values (?,?,?,?,?)");
            preparedStatement.setString(1, persona.getCargo().name());
            preparedStatement.setByte(2, persona.getEstado());
            preparedStatement.setBlob(3, persona.getImagen());
            preparedStatement.setInt(4, persona.getIdPersona());
            preparedStatement.setInt(5, persona.getIdPartidoPolitico());
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        }
        return estado;
    }

    @Override
    public boolean updateObject(Candidato objeto) {
        boolean estado = false;
        Candidato persona = objeto;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update Candidato set cargo=?,estado=?"
                            + ",imagen=?"
                            + "where idCandidato=?");
            preparedStatement.setString(1, persona.getCargo().name());
            preparedStatement.setByte(2, persona.getEstado());
            preparedStatement.setBlob(3, persona.getImagen());
            preparedStatement.setInt(4, persona.getIdCandidato());
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
                    .prepareStatement("delete from Candidato where idCandidato=?");
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
