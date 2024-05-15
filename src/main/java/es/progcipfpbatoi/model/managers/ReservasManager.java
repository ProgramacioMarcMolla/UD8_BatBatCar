/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.Usuario;

/**
 *
 * @author mark
 */
public class ReservasManager {
    private int numReserva;
    
    public ReservasManager(){
        this.numReserva = 1;
    }
    
    public Reserva crearReserva(Usuario usuario, int plazasSolicitadas){
        Reserva reserva = new Reserva(this.numReserva,usuario,plazasSolicitadas);
        this.numReserva++;
        return reserva;
    }
    
}
