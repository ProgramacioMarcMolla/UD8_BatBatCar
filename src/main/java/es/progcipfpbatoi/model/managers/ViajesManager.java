package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Gestor de viajes. Manejará la lista de los viajes tanto para almancenar nueva 
 * información sobre ella como para recuperar la totalidad o parte de la información
 * como también información relacionada con dicha lista.
 * @author batoi
 */

public class ViajesManager {

    private List<Viaje> viajes;
    private int codigoSiguiente;

    public ViajesManager() {
        this.viajes = new ArrayList<>();
        this.codigoSiguiente =1;
        init();
    }

    /**
     * Añade un nuevo viaje al repositorio
     * @param viaje
     */
    public void add(Viaje viaje) {
        viaje.setCodigo(codigoSiguiente);
        viajes.add(viaje);
        this.codigoSiguiente++;
    }
    
    /**
     * Cancela un viaje
     * @param viaje
     */
    public List<Viaje> cancel(Viaje viaje){
        int index = 0;
        
        for (int i = 0; i < this.viajes.size(); i++){
            if(viaje.equals(this.viajes.get(i))) index = i;
        }

        if (index != -1) {
            
            viaje.setIsCancelado(true);

            viajes.set(index, viaje);
        }
        return viajes;
    }

    /**
     * Obtiene el número de viajes actualmente registrados
     * @return
     */
    public int getNumViajes() {
        throw new UnsupportedOperationException("Por implementar");
    }
    
    /**
     * Obtiene todos los viajes
     * @return
     */
    public List<Viaje> findAll() {
        return viajes;
    }

    private void init() {
        // añade a la colección "viajes" todos los viajes que creas necesario tener de inicio en tu sistema
        // this.add(new Viaje(....));
    }
    
    public void setViajes(List<Viaje> viajes){
        this.viajes = viajes;
    }
    
    public void anyadirReserva(Viaje viaje,Reserva reserva){
        viaje.anyadirReserva(reserva);
    }
}
