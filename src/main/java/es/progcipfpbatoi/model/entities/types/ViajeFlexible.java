/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;

/**
 *
 * @author mark
 */
public class ViajeFlexible extends Viaje {
        private final String TIPO = "Flexible";

    public ViajeFlexible(Usuario propietario, String ruta, int duracion, int plazasOfertadas, double precio) {
        super(propietario, ruta, duracion, plazasOfertadas, precio);
    }
    
    @Override
    public String toString(){
        return "Viaje de tipo " + TIPO + "del propietario " + this.propietario.getNombre() + "con código" + this.codigo + " y ruta " + ruta + " con " + plazasOfertadas + " plazas ofertadas ";
    }
    
    @Override
    public String getTIPO() {
        return "Viaje "+TIPO;
    }
}
