package Controlador.Ordenacion;

import Controlador.Lista.ListaEnlazada;
import Modelo.Comparator;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;


public class MergeSort<E> {

    private final Comparator<E> comparator;

    public MergeSort(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void sort(ListaEnlazada<E> lista) throws VacioException, PosicionException {
        if (lista.getSize() < 2) {
            return;
        }

        int midIndex = lista.getSize() / 2;

        ListaEnlazada<E> leftList = new ListaEnlazada<>();
        for (int i = 0; i < midIndex; i++) {
            leftList.insertarNodo(lista.get(i));
        }

        ListaEnlazada<E> rightList = new ListaEnlazada<>();
        for (int i = midIndex; i < lista.getSize(); i++) {
            rightList.insertarNodo(lista.get(i));
        }

        sort(leftList);
        sort(rightList);

        merge(leftList, rightList, lista);
    }

    private void merge(ListaEnlazada<E> left, ListaEnlazada<E> right, ListaEnlazada<E> lista) throws VacioException, PosicionException {
        int leftIndex = 0, rightIndex = 0, listIndex = 0;

        while (leftIndex < left.getSize() && rightIndex < right.getSize()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                lista.set(listIndex++, left.get(leftIndex++));
            } else {
                lista.set(listIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.getSize()) {
            lista.set(listIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.getSize()) {
            lista.set(listIndex++, right.get(rightIndex++));
        }
    }

}
