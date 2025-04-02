import Parser.Parser;
import Parser.SimboloAFD;
import Scanner.Scanner;
import Scanner.Token;
import Utils.Error;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String input = readInput("./Entrada1.lfp");

        // Escanear tokens
        Scanner scanner = new Scanner(input);
        scanner.analizar();
        List<Token> tokens = scanner.getTokens();
        List<Error> erroresLexicos = scanner.getErrores();

        //Imprimir tokens generados
        // System.out.println("\n== TOKENS GENERADOS ==");
        // for (Token token : tokens) {
        //     System.out.printf("Token: %-20s | Lexema: %-20s | Línea: %-3d | Columna: %-3d\n",
        //             token.tipo, token.lexema, token.linea, token.columna);
        // }

        System.out.println("\n== ERRORES LÉXICOS ==");
        if (erroresLexicos.isEmpty()) {
            System.out.println("¡No hay errores léxicos!");
        } else {
            erroresLexicos.forEach(System.out::println);
        }

        // Solo continuar si no hay errores léxicos
        if (erroresLexicos.isEmpty()) {
            Parser parser = new Parser((ArrayList<Token>) tokens, (ArrayList<Error>) erroresLexicos);
            boolean sintaxisCorrecta = parser.parse();

            System.out.println("\n== ERRORES SINTÁCTICOS ==");
            if (sintaxisCorrecta) {
                System.out.println("¡La sintaxis es correcta!");
            } else {
                erroresLexicos.forEach(System.out::println);
            }

            // Imprimir la tabla de símbolos (los AFDs encontrados)
            System.out.println("\n== TABLA DE SÍMBOLOS ==");
            for (SimboloAFD afd : parser.tablaSimbolos) {
                System.out.println("Nombre: " + afd.nombre);
                System.out.println("Descripción: " + afd.descripcion);
                System.out.println("Estados: " + afd.estados);
                System.out.println("Alfabeto: " + afd.alfabeto);
                System.out.println("Estado Inicial: " + afd.estadoInicial);
                System.out.println("Estados Finales: " + afd.estadosFinales);
                System.out.println("Transiciones:");
                for (String origen : afd.transiciones.keySet()) {
                    for (SimboloAFD.Transicion t : afd.transiciones.get(origen)) {
                        System.out.println("  " + origen + " " + t);
                    }
                }
                System.out.println("-------------------------------");
            }
        }
    }

    private static String readInput(String path) {
        StringBuilder texto = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto.toString();
    }
}