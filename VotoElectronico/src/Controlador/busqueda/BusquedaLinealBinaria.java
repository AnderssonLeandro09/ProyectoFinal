package Controlador.busqueda;


import Controlador.Lista.ListaEnlazada;
import Controlador.Lista.Nodo;
import Controlador.Ordenacion.MergeSort;
import Modelo.Comparator;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;


public class BusquedaLinealBinaria<E> {

    private final Comparator<E> comparator;

    public BusquedaLinealBinaria(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public ListaEnlazada<E> search(ListaEnlazada<E> lista, ListaEnlazada<E> keys) throws VacioException, PosicionException {
        // Ordenar la lista utilizando MergeSort
        MergeSort<E> mergeSort = new MergeSort<>(comparator);
        mergeSort.sort(lista);

        ListaEnlazada<E> results = new ListaEnlazada<>();

        Nodo<E> currentKey = keys.getCabecera();
        while (currentKey != null) {
            E key = currentKey.getInfo();

            int binaryIndex = binarySearch(lista, key);
            if (binaryIndex != -1) {
                // Utilizar búsqueda lineal desde el índice encontrado
                ListaEnlazada<E> linearResults = linearSearchFromIndex(lista, key, binaryIndex);
                Nodo<E> currentNode = linearResults.getCabecera();
                while (currentNode != null) {
                    results.insertarNodo(currentNode.getInfo());
                    currentNode = currentNode.getSig();
                }
            }

            currentKey = currentKey.getSig();
        }

        return results;
    }

    private int binarySearch(ListaEnlazada<E> lista, E key) throws VacioException, PosicionException {
        int low = 0;
        int high = lista.getSize() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            E element = lista.get(mid);

            if (comparator.compare(element, key) == 0) {
                return mid;
            } else if (comparator.compare(element, key) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private ListaEnlazada<E> linearSearchFromIndex(ListaEnlazada<E> lista, E key, int startIndex) throws VacioException, PosicionException {
        ListaEnlazada<E> results = new ListaEnlazada<>();

        for (int i = startIndex; i < lista.getSize(); i++) {
            if (comparator.compare(lista.get(i), key) == 0) {
                results.insertarNodo(lista.get(i));
            } else {
                break;
            }
        }

        return results;
    }

}
