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
public class ViajeCancelable extends Viaje{
    
        private final String TIPO = "Cancelable";

    
    public ViajeCancelable(Usuario propietario, String ruta, int duracion, int plazasOfertadas, double precio, String fecha) {
        super(propietario, ruta, duracion, plazasOfertadas, precio, fecha);
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
