

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Reserva> reservas = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\nMenu: ");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Mostrar reservas");
            System.out.println("0. Salir");
            
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Nombre del cliente: ");
                        String nombre = sc.nextLine();

                        System.out.print("Fecha de reserva (dd/MM/yyyy): ");
                        String fecha = sc.nextLine();

                        System.out.print("Cantidad de personas: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();

                        Reserva reserva = new Reserva(nombre, fecha, cantidad);
                        reservas.add(reserva);

                        System.out.println("Reserva registrada correctamente.");

                    } catch (ReservaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    if (reservas.isEmpty()) {
                        System.out.println(" No hay reservas registradas.");
                    } else {
                        System.out.println("\n Lista de reservas:");
                        for (Reserva r : reservas) {
                            System.out.println(r);
                        }
                    }
                    break;

                case 0:
                    System.out.println(" finalizado!.");
                    break;

                default:
                    System.out.println(" Opción invalida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
