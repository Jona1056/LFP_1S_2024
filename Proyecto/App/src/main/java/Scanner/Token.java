/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scanner;

/**
 *
 * @author garci
 */
public class Token {
        public TOK tipo;
        public int linea;
        public int columna;
        public String lexema;
        
        public Token(TOK tipo){
            this.tipo = tipo;
            this.linea = -1;
            this.columna = 1;
        }
        
        public Token(TOK tipo, int linea, int columna, String Lexema){
            this.tipo = tipo;
            this.linea = linea;
            this.columna = columna;
            this.lexema = lexema;
        }
}
