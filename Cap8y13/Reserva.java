

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {

    private String nombreCliente;
    private Date fechaReserva;
    private int cantidadPersonas;


    
    public Reserva(String nombreCliente, String fechaTexto, int cantidadPersonas)
            throws ReservaInvalidaException {

        
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            throw new ReservaInvalidaException("  Nombre de cliente invalido.");
        }
        this.nombreCliente = nombreCliente.trim();

       
        if (cantidadPersonas <= 0) {
            throw new ReservaInvalidaException(" Cantidad de personas debe ser mayor que cero.");
        }
        this.cantidadPersonas = cantidadPersonas;

       
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false); //  para evitar fechas incorrectas 
            this.fechaReserva = formato.parse(fechaTexto);
        } catch (ParseException e) {
            throw new ReservaInvalidaException(" El Formato de fecha incorrecto. Use dd/MM/yyyy.");
        }
    }

   
    public String getNombreCliente() {
        return nombreCliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return "Cliente: " + nombreCliente + "Fecha: " + formato.format(fechaReserva) + "Personas: " + cantidadPersonas;
       
    }




    

    
}


    