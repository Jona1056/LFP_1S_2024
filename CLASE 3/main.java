public class EjemploArreglo {
    public static void main(String[] args) {
        // Declaración de un arreglo de enteros
        int[] numeros = {10, 20, 30, 40, 50};

        // Acceder e imprimir elementos del arreglo
        System.out.println("Primer número: " + numeros[0]);
        System.out.println("Último número: " + numeros[numeros.length - 1]);

        // Recorrer el arreglo con un bucle
        System.out.print("Todos los números: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
    }
}

import java.util.HashMap;

public class EjemploHashMap {
    public static void main(String[] args) {
        // Crear un HashMap con claves String y valores Integer
        HashMap<String, Integer> edades = new HashMap<>();

        // Agregar valores
        edades.put("Juan", 25);
        edades.put("Maria", 30);
        edades.put("Carlos", 22);

        // Obtener e imprimir valores
        System.out.println("Edad de Juan: " + edades.get("Juan"));

        // Recorrer e imprimir todos los valores
        for (String nombre : edades.keySet()) {
            System.out.println(nombre + " tiene " + edades.get(nombre) + " años.");
        }
    }
}
public class EjemploBucles {
    public static void main(String[] args) {
        // Bucle FOR: Repetir de 1 a 5
        System.out.println("Bucle FOR:");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }

        // Bucle WHILE: Contar hasta 3
        System.out.println("\n\nBucle WHILE:");
        int j = 1;
        while (j <= 3) {
            System.out.println("Iteración " + j);
            j++;
        }
    }
}

import java.io.*;

public class EscribirArchivo {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("archivo.txt"))) {
            writer.write("¡Hola, este es un archivo de texto en Java!");
            System.out.println("✅ Archivo escrito correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;

public class LeerArchivo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("archivo.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("📖 Contenido: " + linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}