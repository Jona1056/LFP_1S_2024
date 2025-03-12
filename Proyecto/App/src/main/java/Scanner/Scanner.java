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
    
    private Token state1(){
        return new Token(TOK.TK_llaveIzq, char_line, char_col,buffer);
    }
    
    
    
    public Token nextToken(){
        Token token;
        int ascciCode = (int) next_char;
        while((next_char = entrada[pos_char]) != '\0'){
              
            if(next_char == '{'){
                IniciarBuffer(next_char);
                return state1();
            }else{
                char_col ++;
                errores.add(new Error(char_line, char_col, TipoError.LEXICO, "Caracter no reconocido. «" + next_char + "»"));
            }
        pos_char++;
        }
  
        return new Token(TOK.EOF);
    };
    
    
}
