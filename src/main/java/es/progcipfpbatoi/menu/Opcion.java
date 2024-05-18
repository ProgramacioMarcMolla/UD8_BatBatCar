/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.ViajesController;

/**
 *
 * @author mark
 */
public abstract class Opcion {

    private String titulo;

    private boolean finalizar;
    
    protected ViajesController viajesController;

    public Opcion(String titulo, ViajesController viajesController) {
        this.titulo = titulo;
        this.finalizar = false;
        this.viajesController = viajesController;
    }

    public void mostrar(int numOpcion) {
        System.out.print("\n" + numOpcion + ") " + titulo);
    }

    public abstract void ejecutar();

    public boolean finalizar() {
        return finalizar;
    }

    protected void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }


    
}
