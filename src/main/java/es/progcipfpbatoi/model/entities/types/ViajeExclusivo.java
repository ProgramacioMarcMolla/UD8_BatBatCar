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
public class ViajeExclusivo extends Viaje {
        private final String TIPO = "Exclusivo";

    public ViajeExclusivo(Usuario propietario, String ruta, int duracion, int plazasOfertadas, double precio) {
        super(propietario, ruta, duracion, plazasOfertadas, precio);
    }

}
