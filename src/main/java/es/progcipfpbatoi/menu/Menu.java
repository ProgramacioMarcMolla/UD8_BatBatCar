package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.views.GestorIO;

/**
 * Clase que gestiona el menú de opciones. A partir de esta clase se ejecutan las diferentes opciones del menú (casos de uso).
 *
 * @author batoi
 */
public class Menu {

    private static final int OPCION_SALIR = 9;

    private ViajesController viajesController;

    public Menu() {
        this.viajesController = new ViajesController();
    }

    public void iniciar() {

        GestorIO.print("BatBatCar");
        GestorIO.print("=========");
        
        int opcionSeleccionada;

        // Ampliar método para que se soliciten las opciones hasta que se indique la opción salir 
        do{
            mostrarOpciones();
            opcionSeleccionada = solicitarOpcion();
            ejecutarOpcion(opcionSeleccionada);
            if(viajesController.getUsuario()== null && opcionSeleccionada == 1){
                opcionSeleccionada = OPCION_SALIR;
            }
        }while(opcionSeleccionada != OPCION_SALIR);
        

    }

    private void mostrarOpciones() {
        GestorIO.print("\n1) Establecer usuario (login) \n"
                + "2) Listar todos los viajes\n"
                + "3) Añadir viaje\n"
                + "4) Cancelar viaje\n"
                + "5) Realizar reserva\n"
                + "6) Modificar reserva\n"
                + "7) Cancelar reserva\n"
                + "8) Buscar viaje y realizar reserva\n"
                + "9) Salir\n");
    }

    private int solicitarOpcion() {
        return GestorIO.getInt("Introduce la opción", 1, OPCION_SALIR);
    }

    private void ejecutarOpcion(int opcionSeleccionada) {

        // Implementar método para ejecutar la opción recibida
        
        
        switch (opcionSeleccionada) {
            case 1 ->viajesController.logearUsuario();
            case 2 -> viajesController.listarViajes();
            case 3 -> viajesController.anyadirViaje();
            case 4 -> viajesController.cancelarViaje();
            case 5 -> viajesController.realizarReserva();
            case 6 -> viajesController.modificarReserva();
            case 7 -> viajesController.listarViajes();
            case 8 -> viajesController.listarViajes();
            case OPCION_SALIR -> GestorIO.print("Hasta la pronto.");
        }
    }

}
