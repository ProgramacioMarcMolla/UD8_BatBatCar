package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.Viaje;
import es.progcipfpbatoi.model.managers.UsuariosManager;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.views.GestorIO;
import es.progcipfpbatoi.views.ListadoViajesView;
import java.util.ArrayList;
import java.util.List;

public class ViajesController {

    private Usuario usuario;
    private ViajesManager viajesManager;
    private UsuariosManager usuariosManager;

    public ViajesController() {
        this.viajesManager = new ViajesManager();
        this.usuariosManager = new UsuariosManager();

        /* Por defecto, no hay establecido ningún usuario. Se deberá establecer a posteriori
         Si no quieres realizar el caso de uso 1 hasta el final puedes establecer un usuario
         por defecto. Por ejemplo: this.usuario = new Usuario("roberto1979", "12345"); */
        this.usuario = null;
    }

    /**
     * Lista todos los viajes del sistema.
     */
    public void listarViajes() {
        List<Viaje> viajes = viajesManager.findAll();
        (new ListadoViajesView(viajes)).visualizar();
    }

    /**
     * Añade un viaje al sistema, preguntando previamente por toda la información necesaria para crearlo.
     */
    public void anyadirViaje() {
        
        if(this.usuario == null){
            GestorIO.print("No se ha iniciado sesión");
            return;
        }
        
        int tipo = GestorIO.getInt("1- Viaje Estándar\n2- Viaje Cancelable\n3- Viaje Exclusivo\n4- Viaje Flexible\nSeleccioneel tipo de viaje");
        
        String ruta = GestorIO.getString("Introduzca la ruta a realizar");
        ruta = ruta.replaceAll("//s", "");
        
        int duracion = GestorIO.getInt("Introduzca la duración del viaje en minutos");
        
        float precio = GestorIO.getFloat("Introduzca el precio de cada plaza");
        
        int plazasDisponibles = GestorIO.getInt("Introduzca el número de plazas disponibles");
        
        Viaje viaje = new Viaje(usuario, ruta, duracion, plazasDisponibles, precio);
        this.viajesManager.add(viaje);
        GestorIO.print(viaje+"añadido con éxito");
    }

    public void logearUsuario() {
        int intentos = 0;
        while (intentos < 3) {
            String nombre = GestorIO.getString("Username");
            if(!(this.usuariosManager.usernameInUsuarios(nombre))){
                GestorIO.print("Error, el usuario introducido no existe");
                intentos++;
                continue;
            }
            String password = GestorIO.getString("Password");
            this.usuario = this.usuariosManager.logearUsuario(nombre, password);

            if (this.usuario != null) {
                break;
            } else {
                intentos++;
            }
        }

        if (this.usuario == null) {
            System.out.println("Se ha alcanzado el número máximo de intentos. Adiós");
        }
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void cancelarViatge() {
        
        if(this.usuario == null){
            GestorIO.print("No se ha iniciado sesión");
            return;
        }
        
        List<Viaje> viajesNoCancelados = this.viajesManager.findAll();
        
        for(Viaje viaje : viajesNoCancelados){
           if(viaje.getUsuario() != this.usuario %% !(viaje.getIsCancelado())){
               
           }
        }
        
    }
}
