/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;
import es.progcipfpbatoi.exceptions.ViajeNoValidoException;
import es.progcipfpbatoi.menu.Opcion;
import es.progcipfpbatoi.views.GestorIO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mark
 */
public class OpcionCancelarViaje extends Opcion {
    public OpcionCancelarViaje(
            String titulo,
            ViajesController viajesController) {
        super(titulo, viajesController);
    }

    @Override
    public void ejecutar() {
        try {
            this.viajesController.cancelarViaje();
        } catch (UsuarioSinEstablecerException ex) {
            GestorIO.print(ex.getMessage());
        } catch (ViajeNoValidoException ex) {
            GestorIO.print(ex.getMessage());
        }
    }
}
