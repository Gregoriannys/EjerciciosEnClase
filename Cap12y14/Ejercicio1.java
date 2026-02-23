package EjerciciosEnClase.Cap12y14;

import java.util.Random;

class Boveda{
    
    private double saldo = 50000;

    public synchronized boolean retirar(double monto){
        if(saldo >= monto){
            saldo -= monto;
            return true;
        }else{
            return false;
        }
    }
    public synchronized double getSaldo(){
        return saldo;
    }

}

class Cajero extends Thread{
    
    private String nombre;
    private Boveda boveda;
    private int transacciones = 0;
    private Random random = new Random();

    public Cajero(String nombre, Boveda boveda){
        this.nombre = nombre;
        this.boveda = boveda;
    }

    public int getTransacciones(){
        return transacciones;
    }

    @Override
    public void run(){
        int clientes = 3 + random.nextInt(3);

        for(int i = 0; i < clientes; i++){
            try{
                int tiempo = 1000 + random.nextInt(2000);
                Thread.sleep(tiempo);

                double monto = 500 + random.nextInt(1501);

                boolean exito = boveda.retirar(monto);
                
                if(exito){
                    transacciones++;
                    System.out.println(nombre + "Realizo retiro de $" + monto);
                }else {
                    System.out.println(nombre + "Trato de retirar $" + monto + "Pero el saldo no es suficiente.");

                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println(nombre + "Finalizo.");
    }
}

class MonitorBoveda extends Thread{

    private Boveda boveda;

    public MonitorBoveda(Boveda boveda){
        this.boveda = boveda;
        setDaemon(true); 
    }

    @Override
    public void run(){
        try{
            while (true){
                Thread.sleep(2000);
                System.out.println("--- Saldo actual de la boveda: $" + boveda.getSaldo());

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


public class Ejercicio1{

    public static void main(String[] args){

        Boveda boveda = new Boveda();

        Cajero cajero1 = new Cajero("Cajero 1", boveda);
        Cajero cajero2 = new Cajero("Cajero 2", boveda);
        Cajero cajero3 = new Cajero("Cajero 3", boveda);

        MonitorBoveda monitor = new MonitorBoveda(boveda);

        monitor.start();//Hilo demonio
        cajero1.start();
        cajero2.start();
        cajero3.start();

        try {
            cajero1.join();
            cajero2.join();
            cajero3.join();


        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("----Resumen final----");
        System.out.println("Cajero 1 proceso: " + cajero1.getTransacciones() + "Transacciones.");
        System.out.println("Cajero 2 proceso: " + cajero2.getTransacciones() + "Transacciones.");
        System.out.println("Cajero 3 proceso: " + cajero3.getTransacciones() + "Transacciones.");
        System.out.println("Saldo final en la boveda: $" + boveda.getSaldo());


    }
}