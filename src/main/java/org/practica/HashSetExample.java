package org.practica;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        // Elementos no ordenado
        // No permite duplicados
        // Busqueda rapida (usa hashCode() p verificar existencia)
        // contains() para verificar si un elemento existe
        Set<String> nombres = new HashSet<>();
        nombres.add("Ana");
        nombres.add("Zocatoa");
        nombres.add("Ulquiorra Cifer");
        nombres.add("Josefo");
        nombres.add("Ana"); // No se agrega (duplicado)
        System.out.println(nombres+"\n");

        // Eliminar por valor Zocatoa
        System.out.println("Eliminar por valor - Zocatoa");
        nombres.remove("Zocatoa");
        System.out.println(nombres+"\n");

        System.out.println("Buscar por valor=Josefo utilizando contains");
        boolean contieneAna = nombres.contains("Josefo"); // true
        System.out.println(contieneAna+"\n");

    }
}
