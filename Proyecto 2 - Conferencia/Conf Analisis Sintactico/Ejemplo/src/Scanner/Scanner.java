package Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Utils.Error;
import Utils.TipoError;

public class Scanner {
    private int pos_char = 0;
    private int char_line = 1;
    private int char_col = 1;
    private char[] entrada;
    private char last_char;
    private char next_char;
    private String buffer = "";

    public ArrayList<Error> errores = new ArrayList<>();
    public ArrayList<Token> tokens = new ArrayList<>();

    private Map<String, TOK> reservadas = new TreeMap<>() {{
        put("world",  TOK.KW_world);
        put("place",      TOK.KW_places);
        put("at",   TOK.KW_at);
        put("to", TOK.KW_to);
        put("with", TOK.KW_with);
        put("playa", TOK.KW_playa);
        put("pueblo", TOK.KW_pueblo);
        put("isla", TOK.KW_isla);
        put("cueva", TOK.KW_cueva);
        put("connect", TOK.KW_connect);
        put("templo", TOK.KW_templo);
      
        
    }};

    public Scanner(String input) {
        this.entrada = (input + '\0').toCharArray();
        pos_char = 0;
    }

    public void analizar() {
        Token token;
        do {
            token = next_token();
            tokens.add(token);
        } while (token.tipo != TOK.EOF);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<Error> getErrores() {
        return errores;
    }

    private void iniciarBuffer(char current_char) {
        buffer = String.valueOf(current_char);
        char_col ++;
        pos_char ++;
        last_char = current_char;
    }

    private void agregarBuffer(char current_char) {
        buffer += current_char;
        char_col ++;
        pos_char ++;
        last_char = current_char;
    }

    private Token state1() {
        if(Character.isLetterOrDigit(next_char = entrada[pos_char])) {
            agregarBuffer(next_char);
            return state1();
        }
        return new Token(reservadas.getOrDefault(buffer, TOK.TK_id), char_line, char_col, buffer);
    }

    private Token state2() {
        if((next_char = entrada[pos_char]) != '"') {
            agregarBuffer(next_char);
            return state2();
        }
        return state3();
    }

    private Token state3() {
        agregarBuffer(next_char);
        return new Token(TOK.TK_cadena, char_line, char_col, buffer);
    }

    private Token state4() {
        if((next_char = entrada[pos_char]) == '>') {
            agregarBuffer(next_char);
            return state5();
        }
        errores.add(new Error(char_line, char_col, TipoError.LEXICO, "Caracter no reconocido. «" + last_char + "»"));
        return null;
    }

    private Token state5() {
        return new Token(TOK.TK_flecha, char_line, char_col, buffer);
    }

    private Token state6() {
        return new Token(TOK.TK_dospuntos, char_line, char_col, buffer);
    }

    private Token state7() {
        return new Token(TOK.TK_coma, char_line, char_col, buffer);
    }

    private Token state8() {
        return new Token(TOK.TK_igual, char_line, char_col, buffer);
    }

    private Token state9() {
        return new Token(TOK.TK_llaveIzq, char_line, char_col, buffer);
    }

    private Token state10() {
        return new Token(TOK.TK_llaveDer, char_line, char_col, buffer);
    }

    private Token state11() {
        return new Token(TOK.TK_corcheteIzq, char_line, char_col, buffer);
    }

    private Token state12() {
        return new Token(TOK.TK_corcheteDer, char_line, char_col, buffer);
    }

    private Token state13() {
        return new Token(TOK.TK_parentesisIzq, char_line, char_col, buffer);
    }

    private Token state14() {
        return new Token(TOK.TK_parentesisDer, char_line, char_col, buffer);
    }

    private Token state15() {
        while (Character.isDigit(next_char = entrada[pos_char])) {
            agregarBuffer(next_char);
        }

        return new Token(TOK.TK_numero, char_line, char_col, buffer);
    }



    public Token next_token() {
        Token token;

        while((next_char = entrada[pos_char]) != '\0') {
            if(Character.isLetter(next_char)) { // ID || Reservadas
                iniciarBuffer(next_char);
                return state1();
            }

            if(next_char == '"') { // Cadenas
                iniciarBuffer(next_char);
                return state2();
            }

            if(next_char == '-') { // Flecha
                iniciarBuffer(next_char);
                if((token = state4()) != null) {
                    return token;
                }
                continue;
            }

            if(next_char == ':') { // Dos puntos
                iniciarBuffer(next_char);
                return state6();
            }

            if(next_char == ',') { // Coma
                iniciarBuffer(next_char);
                return state7();
            }

            if(next_char == '=') { // Igual
                iniciarBuffer(next_char);
                return state8();
            }

            if(next_char == '{') { // Llave Izquierda
                iniciarBuffer(next_char);
                return state9();
            }

            if(next_char == '}') { // Llave Derecha
                iniciarBuffer(next_char);
                return state10();
            }

            if(next_char == '[') { // Corchete Izquierdo
                iniciarBuffer(next_char);
                return state11();
            }

            if(next_char == ']') { // Corchete Derecho
                iniciarBuffer(next_char);
                return state12();
            }

            if(next_char == '(') { // Parentesis Izquierdo
                iniciarBuffer(next_char);
                return state13();
            }

            if(next_char == ')') { // Parentesis Derecho
                iniciarBuffer(next_char);
                return state14();
            }

            if (Character.isDigit(next_char)) { // Número
                iniciarBuffer(next_char);
                return state15();
            }

            // Caracteres Ignorados
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

            pos_char ++;
        }

        return new Token(TOK.EOF);
    }
}