/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

/**
 *
 * @author mark
 */
public class ViajeExclusivo extends Viaje {
        private final String TIPO = "Exclusivo";

    public ViajeExclusivo(Usuario propietario, String ruta, int duracion, int plazasOfertadas, double precio,String fecha) {
        super(propietario, ruta, duracion, plazasOfertadas, precio,fecha);
    }
    
    @Override
    public String toString(){
        return "Viaje de tipo " + TIPO + "del propietario " + this.propietario.getNombre() + "con código" + this.codigo + " y ruta " + ruta + " con " + plazasOfertadas + " plazas ofertadas ";
    }
    
    @Override
    public String getTIPO() {
        return "Viaje "+TIPO;
    }
    
    @Override
    public void anyadirReserva(Reserva reserva) {
        
        for(int i = 0; i < this.reservas.size(); i++){
            if(reserva.getNombreUsuario().equals(this.reservas.get(i).getNombreUsuario())){
                GestorIO.print("Un usuario no puede hacer más de una reserva.");
                return;
            }
        }
        
        this.reservas.add(reserva);
        
        this.plazasReservadas += reserva.getPlazasSolicitadas();
        
       
        this.isCerrado = true;

    }

}
