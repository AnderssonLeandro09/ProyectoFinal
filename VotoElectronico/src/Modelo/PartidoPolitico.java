/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Blob;
import java.sql.Date;
/**
 *
 * @author Stali
 */
public class PartidoPolitico {
    private int idPartidoPolitico;
    private String nombre;
    private Date fundacion;
    private Blob logo;

    public PartidoPolitico() {
    }

    public int getIdPartidoPolitico() {
        return idPartidoPolitico;
    }

    public void setIdPartidoPolitico(int idPartidoPolitico) {
        this.idPartidoPolitico = idPartidoPolitico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFundacion() {
        return fundacion;
    }

    public void setFundacion(Date fundacion) {
        this.fundacion = fundacion;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }  
}
