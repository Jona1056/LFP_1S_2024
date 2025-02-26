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
     public static void generarReporteHTML() {
        String rutaCSS = "./styles.css";
        String rutaHTML = "./reporte.html";
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaHTML))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Reporte de Personajes</title>");
            writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + rutaCSS + "\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Lista de Personajes</h1>");
            writer.println("<table>");
            writer.println("<tr><th>Nombre</th><th>Vida</th><th>Salud</th><th>Ataque</th><th>Defensa</th></tr>");
            for (Personaje p : personajes) {
                writer.println("<tr><td>" + p.nombre + "</td><td>" + p.vida + "</td><td>" + p.salud + "</td><td>" + p.ataque + "</td><td>" + p.defensa + "</td></tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
            System.out.println("Reporte HTML generado correctamente en " + rutaHTML);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte HTML: " + e.getMessage());
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
     
                       case 3 -> Personaje.generarReporteHTML();
                       default -> System.out.println("Opcion no valida");
                       
                   }
                   
               }while (opcion !=4 );
               
            
        }
        
    }

    
    
    
}
