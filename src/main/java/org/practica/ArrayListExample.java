package org.practica;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        // Mantiene el orden de insercion
        // Permite elementos duplicados
        // Acceso por indice, util para busqueda por posicion
        List<String> frutas = new ArrayList<>();
        frutas.add("Manzana");
        frutas.add("Banana");
        frutas.add("Mandarina");
        frutas.add("Pomelo");
        frutas.add("Uva");
        frutas.add("Manzana");   // Se permite duplicado


        //Recorrer
        for(String elemento:frutas){
            System.out.println("Elemento: "+elemento);
        }
        System.out.println(frutas+"\n");

        // Eliminar
        System.out.println("----- Elimino Banana - Elimina por valor (primera ocurrencia) 'Banana'-----");
        frutas.remove("Banana"); // Elimina "Banana"
        System.out.println(frutas+"\n");

        System.out.println("----- Elimino por indice = 0 Manzana-----");
        frutas.remove(0);
        System.out.println(frutas+"\n");

        //Buscar
        System.out.println("----- Busco por indice = 0 -----");
        System.out.println(frutas.get(0)+"\n");

        System.out.println("----- Busco por valor = uva devuelve bool-----");
        boolean contieneUva = frutas.contains("Uva"); // true
        System.out.println(contieneUva+"\n");
        System.out.println("----- Busco por valor = uva devuelve el indice-----");
        int indiceUva = frutas.indexOf("Uva"); // Devuelve 0 (índice) o -1 si no existe
        System.out.println(indiceUva+"\n");
    }
}

