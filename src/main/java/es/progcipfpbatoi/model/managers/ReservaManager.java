/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.Reserva;
import java.util.HashSet;

/**
 *
 * @author mark
 */
public class ReservaManager {
    private int numReserva;
    private HashSet<Reserva> reservas; 
    
    public ReservaManager(){
        this.reservas = new HashSet<>();
        this.numReserva = 0;
    }
    
    
}
