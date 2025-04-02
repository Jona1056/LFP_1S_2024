package Scanner;

public enum TOK {
    KW_descripcion   ("KW_descripcion"),
    KW_estados       ("KW_estados"),
    KW_alfabeto      ("KW_alfabeto"),
    KW_inicial       ("KW_inicial"),
    KW_finales       ("KW_finales"),
    KW_transiciones  ("KW_transiciones"),
    TK_id            ("TK_id"),
    TK_cadena        ("TK_cadena"),
    TK_flecha        ("TK_flecha"),
    TK_dospuntos     ("TK_dospuntos"),
    TK_coma          ("TK_coma"),
    TK_igual         ("TK_igual"),
    TK_llaveIzq      ("TK_llaveIzq"),
    TK_llaveDer      ("TK_llaveDer"),
    TK_corcheteIzq   ("TK_corcheteIzq"),
    TK_corcheteDer   ("TK_corcheteDer"),
    TK_parentesisIzq ("TK_parentesisIzq"),
    TK_parentesisDer ("TK_parentesisDer"),
    EOF              ("EOF");

    private String nombre;
    
    private TOK(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}