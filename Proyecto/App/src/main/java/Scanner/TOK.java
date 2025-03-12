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
        TK_cadena ("TK_cadena"),
        TK_llaveIzq ("TK_llaveIzq"),
        TK_llaveDer("TK_llaveDer"),
        EOF ("EOF");
        
        private String nombre;
        private TOK(String nombre){
            this.nombre = nombre;
        }
        
        public String getNombre(){
            return nombre;
        }
        
}
