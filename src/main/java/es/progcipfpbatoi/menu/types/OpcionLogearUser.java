/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.MaximoIntentosAlcanzadosExcepcion;
import es.progcipfpbatoi.menu.Opcion;
import es.progcipfpbatoi.views.GestorIO;

/**
 *
 * @author mark
 */
public class OpcionLogearUser extends Opcion {

    public OpcionLogearUser(
            String titulo,
            ViajesController viajesController) {
        super(titulo, viajesController);
    }

    @Override
    public void ejecutar() {
        try{
            this.viajesController.logearUsuario();
        }catch(MaximoIntentosAlcanzadosExcepcion e){
            GestorIO.print(e.getMessage());
        }
    }

}
