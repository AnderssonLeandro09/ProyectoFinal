/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Lista;

/**
 *
 * @author Stali
 */
public class Nodo<E>{
    private E info;
    private Nodo sig;

    public Nodo() {
        this.info = null;
        this.sig = null;
    }

    public Nodo(E info, Nodo sig) {
        this.info = info;
        this.sig = sig;
    }

    /**
     * @return the info
     */
    public E getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(E info) {
        this.info = info;
    }

    /**
     * @return the sig
     */
    public Nodo getSig() {
        return sig;
    }

    /**
     * @param sig the sig to set
     */
    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}
