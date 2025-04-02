package Scanner;

public class Token {
    public TOK tipo;
    public int linea;
    public int columna;
    public String lexema;

    public Token(TOK tipo) {
        this.tipo = tipo;
        this.linea = -1;
        this.columna = -1;
    }

    public Token(TOK tipo, int linea, int columna, String lexema) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
        this.lexema = lexema;
    }
    
}