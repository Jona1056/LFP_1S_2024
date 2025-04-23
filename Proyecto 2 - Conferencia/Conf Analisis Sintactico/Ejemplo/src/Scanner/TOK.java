package Scanner;

public enum TOK {
    KW_world   ("KW_world"),
    KW_places  ("KW_places"),
    KW_connect  ("KW_connect"),
    KW_object   ("KW_object"),
    KW_playa    ("KW_playa"),
    KW_cueva    ("KW_cueva"),
    KW_templo   ("KW_templo"),
    KW_isla     ("KW_isla"),
    KW_pueblo   ("KW_pueblo"),
    TK_id            ("TK_id"),
    TK_cadena        ("TK_cadena"),
    TK_flecha        ("TK_flecha"),
    KW_at            ("KW_at"),
    KW_to            ("KW_to"),
    KW_with          ("KW_with"),
    TK_dospuntos     ("TK_dospuntos"),
    TK_coma          ("TK_coma"),
    TK_igual         ("TK_igual"),
    TK_llaveIzq      ("TK_llaveIzq"),
    TK_llaveDer      ("TK_llaveDer"),
    TK_corcheteIzq   ("TK_corcheteIzq"),
    TK_corcheteDer   ("TK_corcheteDer"),
    TK_parentesisIzq ("TK_parentesisIzq"),
    TK_parentesisDer ("TK_parentesisDer"),
    TK_numero        ("TK_numero"),
    EOF              ("EOF");

    private String nombre;
    
    private TOK(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}