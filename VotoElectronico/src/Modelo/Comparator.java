/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author Stali
 */
public interface Comparator<E> {
    int compare(E a, E b);

    public class NombreComparator implements Comparator<Persona> {

        @Override
        public int compare(Persona a, Persona b) {
            String nombreA = a.getNombre();
            String nombreB = b.getNombre();

            if (nombreA == null && nombreB == null) {
                return 0;
            } else if (nombreA == null) {
                return -1;
            } else if (nombreB == null) {
                return 1;
            } else {
                return nombreA.compareTo(nombreB);
            }
        }
    }
}
