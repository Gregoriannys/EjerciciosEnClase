package EjerciciosEnClase.EjerciciosDia25;

import java.util.HashMap;
import java.util.Map;

public class ObjetoCompartido {
    

    private Map<String, Integer> resultados = new HashMap<>();
    private int totalPalabras = 0;

    public synchronized void agregarResultado(String nombreArchivo, int cantidad) {
        resultados.put(nombreArchivo, cantidad);
        totalPalabras += cantidad;
    }

    public Map<String, Integer> getResultados() {
        return resultados;
    }

    public int getTotalPalabras() {
        return totalPalabras;
    }
}
    

