package Parser;

import Scanner.Token;
import Scanner.TOK;
import Utils.Error;
import Utils.TipoError;

import java.util.ArrayList;

public class Parser {
    private ArrayList<Token> tokens;
    private ArrayList<Error> errores;
    private int index = 0;
    private Token currentToken;
    
    // Tabla de símbolos: cada AFD se almacena aquí
    public ArrayList<SimboloAFD> tablaSimbolos = new ArrayList<>();
    private SimboloAFD afdActual;  // AFD que se está procesando

    public Parser(ArrayList<Token> tokens, ArrayList<Error> errores) {
        this.tokens = tokens;
        this.errores = errores;
        if (!tokens.isEmpty()) {
            currentToken = tokens.get(index);
        }
    }

    // Parse global: espera un bloque que contenga todos los AFD
    public boolean parse() {
        // Se espera que el archivo inicie con '{'
        match(TOK.TK_llaveIzq);
        // Mientras no se encuentre la llave de cierre, parsea definiciones de AFD
        while (currentToken.tipo != TOK.TK_llaveDer && currentToken.tipo != TOK.EOF) {
            parseAFD();
            // Si hay una coma entre AFDs, consúmela
            if (currentToken.tipo == TOK.TK_coma) {
                match(TOK.TK_coma);
            }
        }
        match(TOK.TK_llaveDer);
        return errores.isEmpty();
    }

    // Parse de una definición de AFD
    private void parseAFD() {
        if (currentToken.tipo == TOK.TK_id) {
            // El token actual es el nombre del AFD
            afdActual = new SimboloAFD(currentToken.lexema);
            tablaSimbolos.add(afdActual);
            match(TOK.TK_id); // Consume el nombre (por ejemplo, AFD1)
            match(TOK.TK_dospuntos);
            match(TOK.TK_llaveIzq);
            
            parseDescripcion();
            parseEstados();
            parseAlfabeto();
            parseInicial();
            parseFinales();
            parseTransiciones();
            
            match(TOK.TK_llaveDer);
        } else {
            reportError("Se esperaba el nombre de un AFD");
            advance();
        }
    }

    // Sección descripción: descripcion: "Texto"
    private void parseDescripcion() {
        match(TOK.KW_descripcion);
        match(TOK.TK_dospuntos);
        if (currentToken.tipo == TOK.TK_cadena) {
            afdActual.descripcion = currentToken.lexema;
            match(TOK.TK_cadena);
        } else {
            reportError("Se esperaba una cadena para la descripción");
            advance();
        }
        match(TOK.TK_coma);
    }

    // Sección estados: estados: [S0, S1, ...]
    private void parseEstados() {
        match(TOK.KW_estados);
        match(TOK.TK_dospuntos);
        match(TOK.TK_corcheteIzq);
        if (currentToken.tipo == TOK.TK_id) {
            afdActual.estados.add(currentToken.lexema);
            match(TOK.TK_id);
            while (currentToken.tipo == TOK.TK_coma) {
                match(TOK.TK_coma);
                afdActual.estados.add(currentToken.lexema);
                match(TOK.TK_id);
            }
        }
        match(TOK.TK_corcheteDer);
        match(TOK.TK_coma);
    }

    // Sección alfabeto: alfabeto: ["1", "2", "3"]
    private void parseAlfabeto() {
        match(TOK.KW_alfabeto);
        match(TOK.TK_dospuntos);
        match(TOK.TK_corcheteIzq);
        if (currentToken.tipo == TOK.TK_cadena) {
            afdActual.alfabeto.add(currentToken.lexema.replace("\"", ""));
            match(TOK.TK_cadena);
            while (currentToken.tipo == TOK.TK_coma) {
                match(TOK.TK_coma);
                afdActual.alfabeto.add(currentToken.lexema.replace("\"", ""));
                match(TOK.TK_cadena);
            }
        }
        match(TOK.TK_corcheteDer);
        match(TOK.TK_coma);
    }

    // Sección estado inicial: inicial: S0
    private void parseInicial() {
        match(TOK.KW_inicial);
        match(TOK.TK_dospuntos);
        if (currentToken.tipo == TOK.TK_id) {
            afdActual.estadoInicial = currentToken.lexema;
            match(TOK.TK_id);
        } else {
            reportError("Se esperaba un estado inicial");
            advance();
        }
        match(TOK.TK_coma);
    }

    // Sección estados finales: finales: [S0, S1, ...]
    private void parseFinales() {
        match(TOK.KW_finales);
        match(TOK.TK_dospuntos);
        match(TOK.TK_corcheteIzq);
        if (currentToken.tipo == TOK.TK_id) {
            afdActual.estadosFinales.add(currentToken.lexema);
            match(TOK.TK_id);
            while (currentToken.tipo == TOK.TK_coma) {
                match(TOK.TK_coma);
                afdActual.estadosFinales.add(currentToken.lexema);
                match(TOK.TK_id);
            }
        }
        match(TOK.TK_corcheteDer);
        match(TOK.TK_coma);
    }

    // Sección transiciones: transiciones: { ... }
    private void parseTransiciones() {
        match(TOK.KW_transiciones);
        match(TOK.TK_dospuntos);
        match(TOK.TK_llaveIzq);
        // Mientras haya una regla de transición (inicia con un TK_id)
        while (currentToken.tipo == TOK.TK_id) {
            parseReglaTransicion();
            if (currentToken.tipo == TOK.TK_coma) {
                match(TOK.TK_coma);
            }
        }
        match(TOK.TK_llaveDer);
    }

    // Regla de transición: S0 = ("1" -> S1, "2" -> S2, ...)
    private void parseReglaTransicion() {
        String origen = currentToken.lexema;
        match(TOK.TK_id);
        match(TOK.TK_igual);
        match(TOK.TK_parentesisIzq);
        ArrayList<SimboloAFD.Transicion> lista = new ArrayList<>();
        if (currentToken.tipo == TOK.TK_cadena) {
            String simbolo = currentToken.lexema.replace("\"", "");
            match(TOK.TK_cadena);
            match(TOK.TK_flecha);
            String destino = currentToken.lexema;
            match(TOK.TK_id);
            lista.add(new SimboloAFD.Transicion(simbolo, destino));
            while (currentToken.tipo == TOK.TK_coma) {
                match(TOK.TK_coma);
                simbolo = currentToken.lexema.replace("\"", "");
                match(TOK.TK_cadena);
                match(TOK.TK_flecha);
                destino = currentToken.lexema;
                match(TOK.TK_id);
                lista.add(new SimboloAFD.Transicion(simbolo, destino));
            }
        }
        match(TOK.TK_parentesisDer);
        afdActual.transiciones.put(origen, lista);
    }

    // match: espera que el token actual sea del tipo esperado, de lo contrario reporta error y avanza
    private void match(TOK expected) {
        if (currentToken.tipo == expected) {
            advance();
        } else {
            reportError("Se esperaba " + expected + " pero se encontró " + currentToken.tipo);
            advance();
        }
    }

    // advance: avanza al siguiente token; si llega al final, se marca con EOF
    private void advance() {
        if (index < tokens.size() - 1) {
            index++;
            currentToken = tokens.get(index);
        } else {
            currentToken = new Token(TOK.EOF, currentToken.linea, currentToken.columna, "EOF");
        }
    }

    // Reporta un error sintáctico en la posición del token actual
    private void reportError(String message) {
        errores.add(new Error(currentToken.linea, currentToken.columna, TipoError.SINTACTICO, message));
        advance();
    }
}