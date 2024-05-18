package es.progcipfpbatoi;

/**
 * Clase principal.
 * 
 */

import es.progcipfpbatoi.menu.Menu;
import es.progcipfpbatoi.menu.MenuPolimorfico;
import es.progcipfpbatoi.menu.types.OpcionAnyadirViajes;
import es.progcipfpbatoi.menu.types.OpcionBuscarViaje;
import es.progcipfpbatoi.menu.types.OpcionCancelarReserva;
import es.progcipfpbatoi.menu.types.OpcionCancelarViaje;
import es.progcipfpbatoi.menu.types.OpcionListarViajes;
import es.progcipfpbatoi.menu.types.OpcionLogearUser;
import es.progcipfpbatoi.menu.types.OpcionModificarReserva;
import es.progcipfpbatoi.menu.types.OpcionRealizarReserva;
import es.progcipfpbatoi.menu.types.OpcionSalir;

public class BatBatCar {

    public static void main(String[] args) {
        /*
        Menu menu = new Menu();
        menu.iniciar();
        */
        
        MenuPolimorfico menuPolimorfico = new MenuPolimorfico("BatBatCar");
        
        menuPolimorfico.anyadir(new OpcionLogearUser("Establecer usuario (login)",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionListarViajes("Listar todos los viajes",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionAnyadirViajes("Añadir viaje",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionCancelarViaje("Cancelar viaje",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionRealizarReserva("Realizar reserva",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionModificarReserva("Modificar reserva",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionCancelarReserva("Cancelar reserva",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionBuscarViaje("Buscar viaje y realizar reserva",menuPolimorfico.getViajesController()));
        menuPolimorfico.anyadir(new OpcionSalir("Salir",menuPolimorfico.getViajesController()));
        
        menuPolimorfico.ejecutar();
                
        
    }
}
