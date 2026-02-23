package EjerciciosEnClase.Cap12y14.ejercicio2;

import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaRegistro sistema = new SistemaRegistro();

        int opcion;

        do {
            System.out.println("\n----Sistema de registro-----");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Buscar estudiante");
            System.out.println("3. Listar estudiantes");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();

                    System.out.print("Promedio: ");
                    double promedio = sc.nextDouble();

                    Estudiante nuevo = new Estudiante(nombre, matricula, promedio);

                    sistema.agregarEstudiante(nuevo);
                    break;

                case 2:
                    System.out.print("Ingresa la matracula a buscar: ");
                    String mat = sc.nextLine();
                    sistema.buscarPorMatricula(mat);
                    break;

                case 3:
                    sistema.listarEstudiantes();
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}


    

