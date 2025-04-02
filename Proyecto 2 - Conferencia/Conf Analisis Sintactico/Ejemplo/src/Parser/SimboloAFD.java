package Parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SimboloAFD {
    public String nombre;
    public String descripcion;
    public ArrayList<String> estados = new ArrayList<>();
    public ArrayList<String> alfabeto = new ArrayList<>();
    public String estadoInicial;
    public ArrayList<String> estadosFinales = new ArrayList<>();

    public LinkedHashMap<String, ArrayList<Transicion>> transiciones = new LinkedHashMap<>();

    public SimboloAFD(String nombre) {
        this.nombre = nombre;
    }

    public static class Transicion {
        public String simbolo;
        public String destino;

        public Transicion(String simbolo, String destino) {
            this.simbolo = simbolo;
            this.destino = destino;
        }
        
        @Override
        public String toString() {
            return "--" + simbolo + "--> " + destino;
        }
    }
}