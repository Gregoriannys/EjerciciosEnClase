package EjerciciosEnClase.Cap12y14.ejercicio2;
import java.io.*;
import java.util.ArrayList;

public class SistemaRegistro {

 private ArrayList<Estudiante> lista = new ArrayList<>();
    private final String archivo = "estudiantes.dat";

    public SistemaRegistro() {
        cargarArchivo();
    }

    
    @SuppressWarnings("unchecked")
    private void cargarArchivo() {
        try {
            File file = new File(archivo);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Archivo creado: estudiantes.dat");
                return;
            }

            if (file.length() == 0) return;

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(file));

            lista = (ArrayList<Estudiante>) ois.readObject();
            ois.close();

            System.out.println("Datos cargados correctamente.");

        } catch (Exception e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }

    
    private void guardarArchivo() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(archivo));

            oos.writeObject(lista);
            oos.close();

        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public void agregarEstudiante(Estudiante e) {
        lista.add(e);
        guardarArchivo();
        System.out.println("Estudiante agregado correctamente.");
    }

    public void listarEstudiantes() {
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }

    
    public void buscarPorMatricula(String matricula) {

        try {
            RandomAccessFile raf = new RandomAccessFile(archivo, "r");

            if (raf.length() == 0) {
                System.out.println("Archivo vacio.");
                raf.close();
                return;
            }

            raf.close();

            // Buscamos en la lista cargada
            for (Estudiante e : lista) {
                if (e.getMatricula().equals(matricula)) {
                    System.out.println("Estudiante encontrado:");
                    System.out.println(e);
                    return;
                }
            }

            System.out.println("No se encontro la matricula.");

        } catch (IOException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
    }
}




    

