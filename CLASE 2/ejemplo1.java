/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplo1;

/**
 *
 * @author garci
 */
class Estudiante{
    private String nombre;
    private int edad;
    private String curso;
    private static int contadorEstudiantes = 0 ; //variable compartida por todos los objetos
    
    //Constructor (metodo no estatico)
    public Estudiante(String nombre, int edad, String curso){
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
   
        contadorEstudiantes++;
    }
    //Metodo no estatico para mostrar la informacion del estudiante
    public void mostrarInfo(){
          System.out.println("Nombre: " + this.nombre + " Edad: " + this.edad + " Curso: " + this.curso);
    }
    
    //Metodo estatico para obtener la cantidad de estudiantes
    public static int obtenerCantidadEstudiantes(){
        return contadorEstudiantes;
    }
    public void setCurso(String curso){
        this.curso = curso;
    }
    
    public String getEstudiante(){
        return this.nombre;
    }
    
}
public class Ejemplo1 {
    public static void main(String[] args) {
            System.out.println("Cantidad de estudiantes antes de crear objetos");
            System.out.println("Cantidad: " + Estudiante.obtenerCantidadEstudiantes());
            Estudiante estudiante1 = new Estudiante("Juan", 24, "Lenguajes Formales");
            Estudiante estudiante2 = new Estudiante("Pedro", 26, "Compiladores 1");
            Estudiante estudiante3 = new Estudiante("Sergio", 20, "Compiladores 2");
          
            estudiante1.mostrarInfo();
            estudiante2.mostrarInfo();
            estudiante3.mostrarInfo();
            
            estudiante1.setCurso("Organizacion Computacional");
            estudiante1.mostrarInfo();
            System.out.println(estudiante1.getEstudiante());

            System.out.println("Cantidad de estudiantes despues de crear objetos");
            System.out.println("Cantidad: " + Estudiante.obtenerCantidadEstudiantes());
    }

    
    
    
}
