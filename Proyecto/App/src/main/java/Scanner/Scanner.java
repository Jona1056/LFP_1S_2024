/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scanner;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Utils.Error;
import Utils.TipoError;
/**
 *
 * @author garci
 */
public class Scanner {
    private int pos_char = 0;
    private int char_line = 1;
    private int char_col = 1;
    private char[] entrada;
    private char last_char;
    private char next_char;
    private String buffer = "";
    
    
    public ArrayList<Error> errores = new ArrayList<>();
    
    private Map<String, TOK> reservadas = new TreeMap<>(){{
        put("descripcion", TOK.KW_descripcion);
        put("estados", TOK.KW_estados);
        put("alfabeto", TOK.KW_alfabeto);
        //aqui agregar las demas palabras reservadas
        //estados, alfabetok, inicial, finales, transiciones
        
    }
    };
    
    public Scanner(String input){
        this.entrada = (input + '\0').toCharArray();
        pos_char = 0;
    }
    
    private void IniciarBuffer(char current_char){
        buffer = String.valueOf(current_char);
        char_col ++;
        pos_char ++;
        last_char = current_char;
    }
    
    private void agregarBuffer(char current_char){
        buffer += current_char;
        char_col ++;
        pos_char ++;
        last_char = current_char;
    }
    
    private boolean esLetraODigito(char ch){
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }
    
    private Token state1(){
        return new Token(TOK.TK_llaveIzq, char_line, char_col,buffer);
    }
    
    private Token state2(){
            if(esLetraODigito(next_char = entrada[pos_char])){
                agregarBuffer(next_char);
                return state2();
            }
            return new Token(reservadas.getOrDefault(buffer, TOK.TK_id),char_line, char_col,buffer);

            }
    private Token state3(){
        return new Token(TOK.TK_dospuntos,char_line,char_col,buffer);
    }
    
    
    
    
    
    
    public Token nextToken(){
        Token token;

        while((next_char = entrada[pos_char]) != '\0'){
       // Caracteres Ignorados
                    if (next_char == '{'){
                        IniciarBuffer(next_char);
                        return state1();
                    }
                    
                    if((next_char >= 'A' && next_char <= 'Z')  || (next_char >= 'a' && next_char <= 'z')) { // ID | RESERVADAS
                        IniciarBuffer(next_char);
                        return state2();
                    }
                    
                    if (next_char == ':'){
                        IniciarBuffer(next_char);
                        return state3();
                    }
                    if(next_char == ' ') {
                        char_col ++;
                    } else if(next_char == '\t') {
                        char_col += String.valueOf(next_char).length();
                    } else if(next_char == '\n') {
                        char_col = 1;
                        char_line ++;
                    } else { // Errores Léxicos
                        char_col ++;
                        errores.add(new Error(char_line, char_col, TipoError.LEXICO, "Caracter no reconocido. «" + next_char + "»"));
                    }
                pos_char++;
        }
  
        return new Token(TOK.EOF);
    };
    
    
}
