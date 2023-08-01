package Controlador.Ordenacion;

import Controlador.Lista.ListaEnlazada;
import Modelo.Comparator;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;


public class QuickSort<E> {

    private final Comparator<E> comparator;

    public QuickSort(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void sort(ListaEnlazada<E> lista) throws VacioException, PosicionException {
        quickSort(lista, 0, lista.getSize() - 1);
    }

    private void quickSort(ListaEnlazada<E> lista, int low, int high) throws VacioException, PosicionException {
        if (low < high) {
            int pivot = partition(lista, low, high);
            quickSort(lista, low, pivot - 1);
            quickSort(lista, pivot + 1, high);
        }
    }

    private int partition(ListaEnlazada<E> lista, int low, int high) throws VacioException, PosicionException {
        E pivot = lista.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(lista.get(j), pivot) <= 0) {
                i++;

                E temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        E temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(high));
        lista.set(high, temp);

        return i + 1;
    }
}
