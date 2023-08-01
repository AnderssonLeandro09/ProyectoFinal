/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controlador.Dao;

import Controlador.Lista.ListaEnlazada;

/**
 *
 * @author Stali
 */
public interface interfazDao<T> {
    ListaEnlazada<T> listar();
    T objeto(int id);
    boolean insertObject(T objeto);
    boolean updateObject(T objeto);
    boolean deleteObject(int id);
}
