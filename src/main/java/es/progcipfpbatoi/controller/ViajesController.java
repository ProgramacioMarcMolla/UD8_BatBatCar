package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.Viaje;
import es.progcipfpbatoi.model.managers.ReservasManager;
import es.progcipfpbatoi.model.managers.UsuariosManager;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.views.GestorIO;
import es.progcipfpbatoi.views.ListadoReservasView;
import es.progcipfpbatoi.views.ListadoViajesView;
import java.util.ArrayList;
import java.util.List;

public class ViajesController {

    private Usuario usuario;
    private ViajesManager viajesManager;
    private UsuariosManager usuariosManager;
    private ReservasManager reservaManager;

    public ViajesController() {
        this.viajesManager = new ViajesManager();
        this.usuariosManager = new UsuariosManager();
        this.reservaManager = new ReservasManager();

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

        if (this.usuario == null) {
            GestorIO.print("No se ha iniciado sesión");
            return;
        }

        int tipo = GestorIO.getInt("1- Viaje Estándar\n2- Viaje Cancelable\n3- Viaje Exclusivo\n4- Viaje Flexible\nSeleccioneel tipo de viaje");

        String ruta = GestorIO.getString("Introduzca la ruta a realizar");
        ruta = ruta.replaceAll("//s", "");

        int duracion = GestorIO.getInt("Introduzca la duración del viaje en minutos");

        float precio = GestorIO.getFloat("Introduzca el precio de cada plaza");

        int plazasDisponibles = GestorIO.getInt("Introduzca el número de plazas disponibles");

        GestorIO.print(this.usuario.getNombre());

        Viaje viaje = new Viaje(usuario, ruta, duracion, plazasDisponibles, precio);
        this.viajesManager.add(viaje);
        GestorIO.print(viaje + "añadido con éxito");
    }

    public void logearUsuario() {
        int intentos = 0;
        while (intentos < 3) {
            String nombre = GestorIO.getString("Username");
            if (!(this.usuariosManager.usernameInUsuarios(nombre))) {
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

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void cancelarViaje() {

        if (this.usuario == null) {
            GestorIO.print("No se ha iniciado sesión");
            return;
        }

        List<Viaje> viajesNoCancelados = new ArrayList<>(this.viajesManager.findAll());
        

        for (int i = viajesNoCancelados.size() - 1; i >= 0; i--) {
            if (!viajesNoCancelados.get(i).getUsuario().equals(this.usuario)) {
                viajesNoCancelados.remove(i);
            }
            
        }
        
        for(int i = viajesNoCancelados.size() - 1; i >= 0; i--){
            if(  viajesNoCancelados.get(i).getIsCancelado()){
                viajesNoCancelados.remove(i);
            }
        }

        (new ListadoViajesView(viajesNoCancelados)).visualizar();

        if (viajesNoCancelados.isEmpty()) {
            GestorIO.print("No existen viajes cancelables");
            return;
        }

        boolean isCodigoValido = false;
        int indiceEliminar = -1; 
        Viaje viaje = null;

        while (indiceEliminar == -1) {
            int codigoEliminar = GestorIO.getInt("Introduzca el código del viaje a eliminar");

            for (int i = 0; i < viajesNoCancelados.size(); i++) {
                viaje = viajesNoCancelados.get(i);
                if (viaje.getCodigo() == codigoEliminar) {
                    indiceEliminar = i; 
                    break; 
                }
            }

            if (indiceEliminar == -1) {
                GestorIO.print("El código ingresado no corresponde a un viaje existente. Por favor, inténtelo de nuevo.");
            }
        }

        this.viajesManager.setViajes(this.viajesManager.cancel(viaje));
        GestorIO.print("El viaje se ha cancelado correctamente");

    }
    
    public void realizarReserva(){
         if (this.usuario == null) {
            GestorIO.print("No se ha iniciado sesión");
            return;
        }

        List<Viaje> viajesReservables = new ArrayList<>(this.viajesManager.findAll());
        
        
        for (int i = viajesReservables.size() - 1; i >= 0; i--) {
            if (viajesReservables.get(i).getUsuario().equals(this.usuario)) {
                viajesReservables.remove(i);
            }
            
        }
        
        for(int i = viajesReservables.size() - 1; i >= 0; i--){
            if(  viajesReservables.get(i).getIsCancelado()){
                viajesReservables.remove(i);
            }
        }
        
        for(int i = viajesReservables.size() - 1; i >= 0; i--){
            if(  viajesReservables.get(i).getIsCerrado()){
                viajesReservables.remove(i);
            }
        }
        
        (new ListadoViajesView(viajesReservables)).visualizar();
        
        if (viajesReservables.isEmpty()) {
            GestorIO.print("No existen viajes reservables");
            return;
        }
        
        boolean isCodigoValido = false;
        int indiceViaje = -1; 
        Viaje viaje = null;

        while (indiceViaje == -1) {
            int codigoReservar = GestorIO.getInt("Introduzca el código del viaje a reservar");

            for (int i = 0; i < viajesReservables.size(); i++) {
                viaje = viajesReservables.get(i);
                if (viaje.getCodigo() == codigoReservar) {
                    indiceViaje = i; 
                    break; 
                }
            }

            if (indiceViaje == -1) {
                GestorIO.print("El código ingresado no corresponde a un viaje existente. Por favor, inténtelo de nuevo.");
            }
        }
        
        int numReservas = GestorIO.getInt("Introduzca el número de plazas a reservar", 1, viajesReservables.get(indiceViaje).getPlazasDisponibles());
        
        Reserva reserva = this.reservaManager.crearReserva(usuario, numReservas);
        
        GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");
        this.viajesManager.anyadirReserva(viaje, reserva);
        
        (new ListadoReservasView(reserva)).visualizar();
    }

}
