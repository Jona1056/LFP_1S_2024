/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.app;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/**
 *
 * @author garci
 */
import Scanner.Scanner;
import Scanner.Token;
import Utils.Error;
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String input = readInput("C:\\Users\\garci\\OneDrive\\Desktop\\AUXILIATURA\\2DO SEMESTRE\\LFP_1S_2024\\Proyecto\\App\\src\\main\\java\\com\\mycompany\\app\\Entrada1.lfp");
        Scanner sc = new Scanner(input);
        Token token;
        System.out.println("\nTOKENS");
        System.out.printf("%-25s%-6s%-8s%-10s\n", "LEXEMA", "LINEA", "COLUMNA", "TIPO");
        do {
            token = sc.nextToken();
            System.out.printf("%-25s%-6s%-8s%-10s\n", token.lexema, token.linea, token.columna, token.tipo.getNombre());
        }while (token.lexema != null);
        
        System.out.println("\nErrores Lexicos");
        if(sc.errores.size() >0){
            for(Error e: sc.errores){
                System.out.println(e);
            }
        }else{
            System.out.println("No hay errores");
        }
        
    }
    
    
    public static String readInput(String path){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
             String texto = "";
             String linea;
             while((linea = br.readLine()) != null){
                 texto +=linea + "\n";
             }
             return texto;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
