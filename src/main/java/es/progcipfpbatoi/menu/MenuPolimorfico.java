/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.views.GestorIO;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class MenuPolimorfico {
    
    private static final int OPCION_SALIR = 9;

    private ViajesController viajesController;
    
    private ArrayList<Opcion> opciones;

    private String tituloMenu;
    
    public MenuPolimorfico(String tituloMenu){
        this.viajesController = new ViajesController();
        this.tituloMenu = tituloMenu;
        this.opciones = new ArrayList<>();
    }   
    
    public ViajesController getViajesController(){
        return this.viajesController;
    }
    
    public void ejecutar() {

        Opcion opcion;
        do {
            mostrar();
            opcion = getOpcion();
            opcion.ejecutar();
        } while (!opcion.finalizar());

        

    }

    

    

    private void mostrar() {
        GestorIO.print("\n" + tituloMenu + "\n===================");
        for (int i = 0; i < opciones.size() ; i++) {
            opciones.get(i).mostrar(i + 1);
        }

    }

    private Opcion getOpcion() {
        
       int opcion = GestorIO.getInt("\nSeleccione una opcion [1-" + opciones.size()+ "]", 1, this.opciones.size());
       return this.opciones.get(opcion-1);

    }
    
    public void anyadir(Opcion opcion) {
        this.opciones.add(opcion);
    }

    
}
