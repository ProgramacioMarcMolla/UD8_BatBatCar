/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.menu.Opcion;

/**
 *
 * @author mark
 */
public class OpcionModificarReserva extends Opcion {
    public OpcionModificarReserva(
            String titulo,
            ViajesController viajesController) {
        super(titulo, viajesController);
    }

    @Override
    public void ejecutar() {
        this.viajesController.modificarReserva();
    }
}
