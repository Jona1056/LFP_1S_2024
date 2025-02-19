/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplo1;
import java.io.*;
import java.util.*;
/**
 *
 * @author garci
 */
class Personaje {
    private String nombre;
    private int salud;
    private int ataque;
    private int defensa; 
    private int vida;
    
    private static List<Personaje> personajes = new ArrayList<>();
    
    public Personaje(String nombre, int salud, int ataque, int defensa){
         this.nombre = nombre;
         this.salud = salud;
         this.ataque = ataque;
         this.defensa = defensa;
         this.vida = salud * 10; 
    }
    
    
    @Override
    public String toString(){
        return "Nombre: " + nombre + ", Vida: " + vida + ", Salud: " + salud + ", Ataque: " + ataque + ", Defensa:  "  + defensa;
    }
    
    public static void CargarPersonajes(String archivo){
        personajes.clear();
        File file = new File(archivo);
        if (!file.exists()){
            System.out.println("El archivo no existe. Intente de nuevo");
            return;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String linea;
               int contador = 0;
                while ((linea = br.readLine()) != null){
                   if (contador ==0 ){
                       contador++;
                        continue;
                    }
                    String [] datos = linea.split(";");
                    if(datos.length == 4){
                         String nombre = datos[0].trim();
                         int salud = Integer.parseInt(datos[1].trim());
                         int ataque = Integer.parseInt(datos[2].trim());
                         int defensa = Integer.parseInt(datos[3].trim());
                         personajes.add(new Personaje(nombre,salud,ataque,defensa));
                    }
                 
                }
                System.out.println("Personajes Cargados Correctamente");
        }catch(IOException | NumberFormatException e){
            System.out.println("Error al leer el archivo:  "+ e.getMessage());
        }
        
    }
    public static void mostrarPersonajes(){
        if(personajes.isEmpty()){
            System.out.println("No hay personajes Cargados.");
        }else{
            System.out.println("\nLista de personajes");
            for(Personaje p : personajes){
                System.out.println(p);
            }
        }
    }
    
}


public class Ejemplo1 {
    public static void main(String[] args) {
           try(Scanner scanner = new Scanner(System.in)){
               int opcion;
               do{
                   System.out.println("\nMenu");
                   System.out.println("1) Cargar Personajes");
                   System.out.println("2) Jugar");
                   System.out.println("3) Ver Reporte 1");
                   System.out.println("4) Ver Reporte 2");
                   opcion = scanner.nextInt();
                   scanner.nextLine();
                   
                   switch(opcion){
                       case 1 -> {
                             System.out.println("Ingrese la ruta del archivo: ");
                             String ruta = scanner.nextLine();
                             Personaje.CargarPersonajes(ruta);
                       }
                       case 2 -> Personaje.mostrarPersonajes();
     
                       case 3 -> {
                                System.out.println("opcion 3");
                       }
                       default -> System.out.println("Opcion no valida");
                       
                   }
                   
               }while (opcion !=4 );
               
            
        }
        
    }

    
    
    
}
