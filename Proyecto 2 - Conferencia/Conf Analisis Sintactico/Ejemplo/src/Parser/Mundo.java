package Parser;

import java.util.ArrayList;
import java.util.List;

public class Mundo {
    public String nombre;

    public List<Lugar> lugares = new ArrayList<>();
    public List<Conexion> conexiones = new ArrayList<>();
    public List<Objeto> objetos = new ArrayList<>();

    public Mundo(String nombre) {
        this.nombre = nombre;
   
    }

    public static class Lugar {
        public String nombre;
        public String tipo;
        public int x, y;

        public Lugar(String nombre, String tipo, int x, int y) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.x = x;
            this.y = y;
        }
    }

    public static class Conexion {
        public String origen;
        public String destino;
        public String medio;

        public Conexion(String origen, String destino, String medio) {
            this.origen = origen;
            this.destino = destino;
            this.medio = medio;
        }
    }

    public static class Objeto {
        public String nombre;
        public String descripcion;
        public String ubicacion; // puede ser nombre del lugar o "(x,y)"

        public Objeto(String nombre, String descripcion, String ubicacion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.ubicacion = ubicacion;
        }
    }
}
