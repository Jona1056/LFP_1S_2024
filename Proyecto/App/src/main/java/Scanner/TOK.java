/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scanner;

/**
 *
 * @author garci
 */
public enum TOK {
        KW_descripcion ("KW_descripcion"),
        KW_estados ("KW_estados"),
        KW_alfabeto ("KW_alfabero"),
        TK_id ("TK_id"),
        TK_cadena ("TK_cadena"),
        TK_llaveIzq ("TK_llaveIzq"),
        TK_llaveDer("TK_llaveDer"),
        TK_dospuntos ("TK_dospuntos"),
        EOF ("EOF");
        
        private String nombre;
        private TOK(String nombre){
            this.nombre = nombre;
        }
        
        public String getNombre(){
            return nombre;
        }
        
}
