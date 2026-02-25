package EjerciciosEnClase.EjerciciosDia25;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        long inicio = System.currentTimeMillis();

        ObjetoCompartido resultado = new ObjetoCompartido();

        ProcesadorArchivo hilo1 = new ProcesadorArchivo("texto1.txt", resultado);
        ProcesadorArchivo hilo2 = new ProcesadorArchivo("texto2.txt", resultado);
        ProcesadorArchivo hilo3 = new ProcesadorArchivo("texto3.txt", resultado);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long fin = System.currentTimeMillis();
        long tiempoTotal = fin - inicio;

        generarReporte(resultado, tiempoTotal);

        System.out.println("Procesamiento terminado. Revisar archivo estadisticas.txt");
    }

    public static void generarReporte(ObjetoCompartido resultado, long tiempo) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("estadisticas.txt"))) {

            bw.write("========================================\n");
            bw.write("REPORTE DE PROCESAMIENTO DE ARCHIVOS\n");
            bw.write("========================================\n\n");

            for (Map.Entry<String, Integer> entry : resultado.getResultados().entrySet()) {

                bw.write("Archivo: " + entry.getKey() + "\n");
                bw.write("Palabras encontradas: " + entry.getValue() + "\n\n");
            }

            bw.write("----------------------------------------\n");
            bw.write("Total de palabras procesadas: " + resultado.getTotalPalabras() + "\n");
            bw.write("Tiempo de procesamiento: " + tiempo + " ms\n");
            bw.write("========================================\n");

        } catch (IOException e) {
            System.out.println("Error al generar el reporte.");
        }
    }
}
