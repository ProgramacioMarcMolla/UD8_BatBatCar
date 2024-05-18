/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.ReservaNoValidaException;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;
import es.progcipfpbatoi.menu.Opcion;
import es.progcipfpbatoi.views.GestorIO;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            this.viajesController.modificarReserva();
        } catch (UsuarioSinEstablecerException ex) {
            GestorIO.print(ex.getMessage());
        } catch (ReservaNoValidaException ex) {
            GestorIO.print(ex.getMessage());
        }
    }

}
