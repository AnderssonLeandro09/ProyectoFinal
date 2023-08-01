/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Enum.Cargo;
import java.sql.Blob;
/**
 *
 * @author Stali
 */
public class Candidato {
    private int idCandidato;
    private Cargo cargo;
    private byte estado;
    private Blob imagen;
    private int idPartidoPolitico;
    private int idPersona;

    public Candidato() {
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public int getIdPartidoPolitico() {
        return idPartidoPolitico;
    }

    public void setIdPartidoPolitico(int idPartidoPolitico) {
        this.idPartidoPolitico = idPartidoPolitico;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
}
