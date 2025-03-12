/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author garci
 */
public enum TipoError {
    LEXICO ("LEXICO");
    
    private String value;
    private TipoError(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    
}
