/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.busqueda;

import Controlador.Lista.ListaEnlazada;
import Controlador.Ordenacion.MergeSort;
import Modelo.Comparator;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;


public class BusquedaBinaria<E> {

    private final Comparator<E> comparator;

    public BusquedaBinaria(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int search(ListaEnlazada<E> lista, E key) throws VacioException, PosicionException {
        // Ordenar la lista utilizando MergeSort
        MergeSort<E> mergeSort = new MergeSort<>(comparator);
        mergeSort.sort(lista);

        return binarySearch(lista, 0, lista.getSize() - 1, key);
    }

    private int binarySearch(ListaEnlazada<E> lista, int low, int high, E key) throws VacioException, PosicionException {
        if (high >= low) {
            int mid = low + (high - low) / 2;

            if (comparator.compare(lista.get(mid), key) == 0) {
                return mid;
            }

            if (comparator.compare(lista.get(mid), key) > 0) {
                return binarySearch(lista, low, mid - 1, key);
            }

            return binarySearch(lista, mid + 1, high, key);
        }

        return -1;
    }

    
}
