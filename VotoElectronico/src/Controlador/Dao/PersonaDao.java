/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Dao;

import Controlador.Lista.ListaEnlazada;
import Controlador.busqueda.BusquedaBinaria;
import Modelo.Comparator;
import Modelo.Enum.Genero;
import Modelo.Persona;
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
public class PersonaDao implements interfazDao<Persona>{
    private final Connection connection;
    public PersonaDao() {
        connection = Conexion.getConnection();
    }
    
    @Override
    public ListaEnlazada<Persona> listar() {
        ListaEnlazada<Persona> personas = new ListaEnlazada<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Persona");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(resultSet.getInt("idPersona"));
                persona.setCedula(resultSet.getString("cedula"));
                persona.setNombre(resultSet.getString("nombre"));
                persona.setApellido(resultSet.getString("apellido"));
                persona.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                persona.setCiudad(resultSet.getString("ciudad"));
                persona.setCanton(resultSet.getString("canton"));
                String enumValue = resultSet.getString("genero");
                Genero genero = Genero.valueOf(enumValue);
                persona.setGenero(genero);
                persona.setEstadovoto(resultSet.getByte("estadoVoto"));
                persona.setIdCuenta(resultSet.getInt("idCuenta"));
                personas.insertarNodo(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public Persona objeto(int id) {
        Persona persona = new Persona();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from persona where idPersona=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                persona.setIdPersona(resultSet.getInt("idPersona"));
                persona.setCedula(resultSet.getString("cedula"));
                persona.setNombre(resultSet.getString("nombre"));
                persona.setApellido(resultSet.getString("apellido"));
                persona.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                persona.setCiudad(resultSet.getString("ciudad"));
                persona.setCanton(resultSet.getString("canton"));
                String enumValue = resultSet.getString("genero");
                Genero genero = Genero.valueOf(enumValue);
                persona.setGenero(genero);
                persona.setEstadovoto(resultSet.getByte("estadoVoto"));
                persona.setIdCuenta(resultSet.getInt("idCuenta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    @Override
    public boolean insertObject(Persona objeto) {
        Persona persona = objeto;
        boolean estado = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into persona(cedula,nombre,apellido,fechaNacimiento,ciudad,canton,genero,estadoVoto"
                    + ",idCuenta) values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, persona.getCedula());
            preparedStatement.setString(2, persona.getNombre());
            preparedStatement.setString(3, persona.getApellido());
            preparedStatement.setDate(4, persona.getFechaNacimiento());
            preparedStatement.setString(5, persona.getCiudad());
            preparedStatement.setString(6, persona.getCanton());
            preparedStatement.setString(7, persona.getGenero().name());
            preparedStatement.setByte(8, persona.getEstadovoto());
            preparedStatement.setInt(9, persona.getIdCuenta());
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }

    @Override
    public boolean updateObject(Persona objeto) {
        boolean estado = false;
        Persona persona = objeto;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update persona set nombre=?,apellido=?"
                            + ",fechaNacimiento=?,ciudad=?,canton=?"
                            + "where idPersona=?");
            preparedStatement.setString(1, persona.getNombre());
            preparedStatement.setString(2, persona.getApellido());
            preparedStatement.setDate(3, persona.getFechaNacimiento());
            preparedStatement.setString(4, persona.getCiudad());
            preparedStatement.setString(5, persona.getCanton());
            preparedStatement.setInt(6, persona.getIdPersona());
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
                    .prepareStatement("delete from persona where idPersona=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            estado = true;
        } catch (SQLException e) {
            e.printStackTrace();
            estado=false;
        }
        return estado;
    }
    public Persona BuscarporCedula(String cedula) throws VacioException, PosicionException {
        ListaEnlazada<Persona> lista = listar();
        Comparator<Persona> generoComparator = new Comparator<Persona>() {
            @Override
            public int compare(Persona a, Persona b) {
                return a.getCedula().compareToIgnoreCase(b.getCedula());
            }
        };
        BusquedaBinaria<Persona> busquedaBinaria = new BusquedaBinaria<>(generoComparator);
        Persona buscado = new Persona();
        buscado.setCedula(cedula);
        int index = busquedaBinaria.search(lista, buscado);
        return lista.get(index);
    }
}
