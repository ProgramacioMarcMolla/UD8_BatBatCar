/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.menu.Opcion;
import es.progcipfpbatoi.views.GestorIO;

/**
 *
 * @author mark
 */
public class OpcionSalir extends Opcion {
    public OpcionSalir(
            String titulo,
            ViajesController viajesController) {
        super(titulo, viajesController);
    }

    @Override
    public void ejecutar() {
        GestorIO.print("Hasta la pronto.");
        setFinalizar(true);
    }
}
