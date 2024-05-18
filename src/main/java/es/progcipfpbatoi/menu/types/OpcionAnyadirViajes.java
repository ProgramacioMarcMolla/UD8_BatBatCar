/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.FechaPasadaException;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;
import es.progcipfpbatoi.menu.Opcion;
import es.progcipfpbatoi.views.GestorIO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mark
 */
public class OpcionAnyadirViajes extends Opcion {
    public OpcionAnyadirViajes(
            String titulo,
            ViajesController viajesController) {
        super(titulo, viajesController);
    }

    @Override
    public void ejecutar() {
        try {
            this.viajesController.anyadirViaje();
        } catch (UsuarioSinEstablecerException ex) {
            GestorIO.print(ex.getMessage());
        } catch (FechaPasadaException ex) {
            GestorIO.print(ex.getMessage());
        }
    }
}
