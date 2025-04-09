import Parser.Parser;
import Parser.Mundo;
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
        String input = readInput("../Entrada1.lfp");

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

            // Imprimir la tabla de símbolos (los mundos encontrados)
                for (Mundo mundo : parser.tablaSimbolos) {
            System.out.println("Mundo: " + mundo.nombre);
            String dot = generarDot(mundo);
            System.out.println(dot);
            System.out.println("\n----------------------------\n");
        }
        }
    }

        public static String generarDot(Mundo mundo) {
            StringBuilder sb = new StringBuilder();

            sb.append("graph G {\n");
            sb.append("    layout=neato;\n");
            sb.append("    node [style=filled];\n\n");

            // Diccionario simple tipo lista
            List<String[]> estiloTipos = List.of(
                new String[]{"playa", "ellipse", "lightblue"},
                new String[]{"cueva", "box", "gray"},
                new String[]{"templo", "octagon", "gold"},
                new String[]{"jungla", "parallelogram", "forestgreen"},
                new String[]{"montaña", "triangle", "sienna"},
                new String[]{"pueblo", "house", "burlywood"},
                new String[]{"isla", "invtriangle", "lightgoldenrod"},
                new String[]{"río", "hexagon", "deepskyblue"},
                new String[]{"volcán", "doublecircle", "orangered"},
                new String[]{"pantano", "trapezium", "darkseagreen"}
            );

            // Agregar lugares con estilos
            for (Mundo.Lugar lugar : mundo.lugares) {
                String tipo = lugar.tipo.toLowerCase();
                String shape = "ellipse";
                String color = "white";

                for (String[] estilo : estiloTipos) {
                    if (estilo[0].equals(tipo)) {
                        shape = estilo[1];
                        color = estilo[2];
                        break;
                    }
                }

                sb.append(String.format(
                    "    %s [pos=\"%d,%d!\", shape=%s, fillcolor=%s];\n",
                    lugar.nombre, lugar.x, lugar.y, shape, color
                ));
            }

            sb.append("\n");

            // Conexiones
            for (Mundo.Conexion conexion : mundo.conexiones) {
                sb.append(String.format(
                    "    %s -- %s [label=\"%s\"];\n",
                    conexion.origen, conexion.destino, conexion.medio
                ));
            }

            sb.append("}\n");
            return sb.toString();
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