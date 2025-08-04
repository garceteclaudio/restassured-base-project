package org.practica;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        //Almacena pares clave-valor (como un diccionario)
        // claves unicas
        // Elementos pueden repetirse
        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 25);   // Clave: "Ana", Valor: 25
        edades.put("Luis", 30);
        edades.put("Ana", 26);    // Actualiza el valor de "Ana"
        edades.put("Claudio", 99);
        edades.put("jOSEFO", 19);
        edades.put("Tronchatoro", 55);
        edades.put("Tokyo", 4);

        System.out.println("Imprimir todas las clave-valor\n");
        System.out.println(edades);

        // Eliminar por clave
        System.out.println("Eliminar la clave 'Tronchatoro'\n");
        edades.remove("Tronchatoro"); // Elimina la entrada con clave "Luis"
        System.out.println(edades);


        System.out.println("Obtener el valor a partir de la clave 'Luis'");
        System.out.println(edades.get("Luis")+"\n"); // 30 (obtener valor por clave)

        System.out.println("Buscar si existe el valor 19 - Booleano");
        boolean existeEdad25 = edades.containsValue(25); // true
        System.out.println(existeEdad25+"\n");
    }

}
