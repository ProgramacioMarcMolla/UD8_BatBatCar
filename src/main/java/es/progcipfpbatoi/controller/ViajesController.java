package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.exceptions.CredencialesInvalidasExcepcion;
import es.progcipfpbatoi.exceptions.FechaPasadaException;
import es.progcipfpbatoi.exceptions.MaximoIntentosAlcanzadosExcepcion;
import es.progcipfpbatoi.exceptions.ReservaNoValidaException;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;
import es.progcipfpbatoi.exceptions.ViajeNoValidoException;
import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.Viaje;
import es.progcipfpbatoi.model.entities.types.ViajeCancelable;
import es.progcipfpbatoi.model.entities.types.ViajeExclusivo;
import es.progcipfpbatoi.model.entities.types.ViajeFlexible;
import es.progcipfpbatoi.model.managers.ReservasManager;
import es.progcipfpbatoi.model.managers.UsuariosManager;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.views.GestorIO;
import es.progcipfpbatoi.views.ListadoReservasView;
import es.progcipfpbatoi.views.ListadoViajesView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
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
     * @throws es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException
     */
    public void anyadirViaje() throws UsuarioSinEstablecerException, FechaPasadaException{

        if (this.usuario == null) {
            throw new UsuarioSinEstablecerException();
        }

        int tipo = GestorIO.getInt("1- Viaje Estándar\n2- Viaje Cancelable\n3- Viaje Exclusivo\n4- Viaje Flexible\nSeleccioneel tipo de viaje");

        String ruta = GestorIO.getStringRuta("Introduzca la ruta a realizar");
        ruta = ruta.replaceAll("//s", "");

        int duracion = GestorIO.getInt("Introduzca la duración del viaje en minutos");

        float precio = GestorIO.getFloat("Introduzca el precio de cada plaza");

        int plazasDisponibles = GestorIO.getInt("Introduzca el número de plazas disponibles");
        
        String fecha = pedirYValidarFechaHora();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime prueba = LocalDateTime.parse(fecha, formatter); 
        
        if(LocalDateTime.now().isAfter(prueba)){
            throw new FechaPasadaException();
        }

        switch (tipo) {
            case 1 ->
                this.viajesManager.add(new Viaje(usuario, ruta, duracion, plazasDisponibles, precio,fecha));
            case 2 ->
                this.viajesManager.add(new ViajeCancelable(usuario, ruta, duracion, plazasDisponibles, precio, fecha));
            case 3 ->
                this.viajesManager.add(new ViajeExclusivo(usuario, ruta, duracion, plazasDisponibles, precio,fecha));
            case 4 ->
                this.viajesManager.add(new ViajeFlexible(usuario, ruta, duracion, plazasDisponibles, precio,fecha));
        }

        Viaje ultimoViaje = viajesManager.findAll().get(viajesManager.findAll().size() - 1);

        if (ultimoViaje instanceof ViajeFlexible) {
            ViajeFlexible viajeFlexible = (ViajeFlexible) ultimoViaje;
            GestorIO.print(viajeFlexible.toString() + " añadido con éxito");
        } else if (ultimoViaje instanceof ViajeCancelable) {
            ViajeCancelable viajeCancelable = (ViajeCancelable) ultimoViaje;
            GestorIO.print(viajeCancelable.toString() + " añadido con éxito");
        } else if (ultimoViaje instanceof ViajeExclusivo) {
            ViajeExclusivo viajeExclusivo = (ViajeExclusivo) ultimoViaje;
            GestorIO.print(viajeExclusivo.toString() + " añadido con éxito");
        } else {
            // Si ninguna de las subclases coincide, se puede manejar como un Viaje genérico
            GestorIO.print(ultimoViaje.toString() + " añadido con éxito");
        }
    }

    public void logearUsuario() throws MaximoIntentosAlcanzadosExcepcion{
        int intentos = 0;
        while (intentos < 3) {
            String nombre = GestorIO.getString("Username").trim();
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
            throw new MaximoIntentosAlcanzadosExcepcion();
        }
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void cancelarViaje() throws UsuarioSinEstablecerException, ViajeNoValidoException{

        if (this.usuario == null) {
            throw new UsuarioSinEstablecerException();
        }

        List<Viaje> viajesNoCancelados = new ArrayList<>(this.viajesManager.findAll());

        for (int i = viajesNoCancelados.size() - 1; i >= 0; i--) {
            if (!viajesNoCancelados.get(i).getUsuario().equals(this.usuario)) {
                viajesNoCancelados.remove(i);
            }

        }

        for (int i = viajesNoCancelados.size() - 1; i >= 0; i--) {
            if (viajesNoCancelados.get(i).getIsCancelado()) {
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
                throw new ViajeNoValidoException();
            }
        }

        this.viajesManager.setViajes(this.viajesManager.cancel(viaje));
        GestorIO.print("El viaje se ha cancelado correctamente");

    }

    public void realizarReserva() throws UsuarioSinEstablecerException, ViajeNoValidoException{
        if (this.usuario == null) {
            throw new UsuarioSinEstablecerException();
        }

        List<Viaje> viajesReservables = new ArrayList<>(this.viajesManager.findAll());

        for (int i = viajesReservables.size() - 1; i >= 0; i--) {
            if (viajesReservables.get(i).getUsuario().equals(this.usuario)) {
                viajesReservables.remove(i);
            }

        }

        for (int i = viajesReservables.size() - 1; i >= 0; i--) {
            if (viajesReservables.get(i).getIsCancelado()) {
                viajesReservables.remove(i);
            }
        }

        for (int i = viajesReservables.size() - 1; i >= 0; i--) {
            if (viajesReservables.get(i).getIsCerrado()) {
                viajesReservables.remove(i);
            }
        }
        
        for (int i = viajesReservables.size() - 1; i >= 0; i--) {
            if ((viajesReservables.get(i).getIsCerrado())) {
                viajesReservables.remove(i);
            }

        }

        (new ListadoViajesView(viajesReservables)).visualizar();

        if (viajesReservables.isEmpty()) {
            GestorIO.print("No existen viajes reservables");
            return;
        }

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
                throw new ViajeNoValidoException();
            }
        }

        int numReservas = GestorIO.getInt("Introduzca el número de plazas a reservar", 1, viajesReservables.get(indiceViaje).getPlazasDisponibles());
        
        if(LocalDateTime.now().isAfter(viaje.getFecha())){
            GestorIO.print("El viaje ya ha pasado");
            return;
        }

        Reserva reserva = this.reservaManager.crearReserva(usuario, numReservas);

        GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");
        this.viajesManager.anyadirReserva(viaje, reserva);

        (new ListadoReservasView(reserva)).visualizarReserva();
    }

    public void modificarReserva() throws UsuarioSinEstablecerException, ReservaNoValidaException{
        if (this.usuario == null) {
            throw new UsuarioSinEstablecerException();
        }

        List<Viaje> viajesDelUsuario = new ArrayList<>(this.viajesManager.findAll());

        for (int i = viajesDelUsuario.size() - 1; i >= 0; i--) {
            if (viajesDelUsuario.get(i).getUsuario().equals(this.usuario)) {
                viajesDelUsuario.remove(i);
            }

        }
        for (int i = viajesDelUsuario.size() - 1; i >= 0; i--) {
            if (!(viajesDelUsuario.get(i) instanceof ViajeFlexible)) {
                viajesDelUsuario.remove(i);
            }

        }

        for (int i = viajesDelUsuario.size() - 1; i >= 0; i--) {
            if ((viajesDelUsuario.get(i).getIsCancelado())) {
                viajesDelUsuario.remove(i);
            }

        }

        (new ListadoViajesView(viajesDelUsuario)).visualizarReservasViajes();

        if (viajesDelUsuario.isEmpty()) {
            GestorIO.print("No existen reservas modificables");
            return;
        }

        boolean isCodigoValido = false;
        int indiceViaje = -1;
        Viaje viaje = null;
        int codigoReservaValido = -1;
        int codigoViajeValido = -1;
        int nuevasPlazasReservar = 0;

        while (indiceViaje == -1) {
            int codigoReservaModificar = GestorIO.getInt("Introduzca el código de la reserva a modificar");

            for (int i = 0; i < viajesDelUsuario.size(); i++) {
                viaje = viajesDelUsuario.get(i);
                for (int j = 0; j < viaje.getReservas().size(); j++) {
                    if (viaje.getReservas().get(j).getCodigo() == codigoReservaModificar) {
                        codigoViajeValido = viaje.getCodigo();
                        codigoReservaValido = codigoReservaModificar;
                        indiceViaje = i;
                        nuevasPlazasReservar = GestorIO.getInt("Introduzca el número de plazas a reservar", 1, viajesDelUsuario.get(indiceViaje).getPlazasDisponibles() + viaje.getReservas().get(j).getPlazasSolicitadas());
                        isCodigoValido = true;
                        break;
                    }
                }
                if (isCodigoValido) {
                    break;
                }

            }

            if (indiceViaje == -1) {
                throw new ReservaNoValidaException();
            }
        }

        this.viajesManager.modificarReserva(codigoViajeValido, codigoReservaValido, nuevasPlazasReservar);
        GestorIO.print("Reserva modificada con éxito");

    }

    public void cancelarReserva() throws UsuarioSinEstablecerException, ReservaNoValidaException{
        if (this.usuario == null) {
            throw new UsuarioSinEstablecerException();
        }

        List<Viaje> viajesDelUsuario = new ArrayList<>(this.viajesManager.findAll());

        for (int i = viajesDelUsuario.size() - 1; i >= 0; i--) {
            if (viajesDelUsuario.get(i).getUsuario().equals(this.usuario)) {
                viajesDelUsuario.remove(i);
            }

        }

        for (int i = viajesDelUsuario.size() - 1; i >= 0; i--) {
            if (!((viajesDelUsuario.get(i) instanceof ViajeFlexible) || (viajesDelUsuario.get(i) instanceof ViajeCancelable))) {
                viajesDelUsuario.remove(i);
            }
        }

        for (int i = viajesDelUsuario.size() - 1; i >= 0; i--) {
            if ((viajesDelUsuario.get(i).getIsCancelado())) {
                viajesDelUsuario.remove(i);
            }

        }

        (new ListadoViajesView(viajesDelUsuario)).visualizarReservasViajes();

        if (viajesDelUsuario.isEmpty()) {
            GestorIO.print("No existen reservas modificables");
            return;
        }

        boolean isCodigoValido = false;
        int indiceViaje = -1;
        Viaje viaje = null;
        int codigoReservaValido = -1;
        int codigoViajeValido = -1;

        while (indiceViaje == -1) {
            int codigoReservaEliminar = GestorIO.getInt("Introduzca el código de la reserva a eliminar");

            for (int i = 0; i < viajesDelUsuario.size(); i++) {
                viaje = viajesDelUsuario.get(i);
                for (int j = 0; j < viaje.getReservas().size(); j++) {
                    if (viaje.getReservas().get(j).getCodigo() == codigoReservaEliminar) {
                        codigoViajeValido = viaje.getCodigo();
                        codigoReservaValido = codigoReservaEliminar;

                        isCodigoValido = true;
                        break;
                    }
                }
                if (isCodigoValido) {
                    break;
                }

            }

            if (isCodigoValido) {
                break;
            }
            throw new ReservaNoValidaException();
        }

        this.viajesManager.eliminarReserva(codigoViajeValido, codigoReservaValido);
        GestorIO.print("Reserva cancelada con éxito");

    }

    public void buscarViajeYRealizarReserva() throws UsuarioSinEstablecerException, ViajeNoValidoException{
        if (this.usuario == null) {
            throw new UsuarioSinEstablecerException();
        }

        String ciudad = GestorIO.getString("Introduzca la ciudad a la que dese viajar");

        List<Viaje> viajesConCiudadABuscar = new ArrayList<>(this.viajesManager.findAll());

        for (int i = viajesConCiudadABuscar.size() - 1; i >= 0; i--) {
            if (viajesConCiudadABuscar.get(i).getUsuario().equals(this.usuario)) {
                viajesConCiudadABuscar.remove(i);
            }

        }

        for (int i = viajesConCiudadABuscar.size() - 1; i >= 0; i--) {
            String rutaSinOrigen = eliminarPrimeraPalabra(viajesConCiudadABuscar.get(i).getRuta());
            if (!rutaSinOrigen.contains(ciudad)) {
                viajesConCiudadABuscar.remove(i);
            }
        }
        
        for (int i = viajesConCiudadABuscar.size() - 1; i >= 0; i--) {
            if ((viajesConCiudadABuscar.get(i).getIsCancelado())) {
                viajesConCiudadABuscar.remove(i);
            }

        }
        
        for (int i = viajesConCiudadABuscar.size() - 1; i >= 0; i--) {
            if ((viajesConCiudadABuscar.get(i).getIsCerrado())) {
                viajesConCiudadABuscar.remove(i);
            }

        }
        
        (new ListadoViajesView(viajesConCiudadABuscar)).visualizar();
        
        if (viajesConCiudadABuscar.isEmpty()) {
            GestorIO.print("No existen coincidencias");
            return;
        }
        
        if(!GestorIO.confirmar("¿Quiere realizar una reserva?")){
            return;
        }
        
        
        int indiceViaje = -1;
        Viaje viaje = null;

        while (indiceViaje == -1) {
            int codigoReservar = GestorIO.getInt("Introduzca el código del viaje a reservar");

            for (int i = 0; i < viajesConCiudadABuscar.size(); i++) {
                viaje = viajesConCiudadABuscar.get(i);
                if (viaje.getCodigo() == codigoReservar) {
                    indiceViaje = i;
                    break;
                }
            }

            if (indiceViaje == -1) {
                throw new ViajeNoValidoException();
            }
        }

        int numReservas = GestorIO.getInt("Introduzca el número de plazas a reservar", 1, viajesConCiudadABuscar.get(indiceViaje).getPlazasDisponibles());
        
        if(LocalDateTime.now().isAfter(viaje.getFecha())){
            GestorIO.print("El viaje ya ha pasado");
            return;
        }

        Reserva reserva = this.reservaManager.crearReserva(usuario, numReservas);

        GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");
        this.viajesManager.anyadirReserva(viaje, reserva);

        (new ListadoReservasView(reserva)).visualizarReserva();
        
        
    }

    private static String eliminarPrimeraPalabra(String cadena) {
        int indiceGuion = cadena.indexOf('-');
        if (indiceGuion != -1) { 
            return cadena.substring(indiceGuion + 1);
        } else { 
            return cadena;
        }
    }
    
    private static String pedirYValidarFechaHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaHora = null;

        while (fechaHora == null) {
            String fechaStr = GestorIO.getString("Introduzca la fecha (Ej: 13-01-2024)");
            String horaStr = GestorIO.getString("Introduzca la hora (Ej: 23:42)");
            String fechaHoraStr = fechaStr + " " + horaStr;
            try {
                fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
            } catch (DateTimeParseException e) {
                GestorIO.print("Formato de fecha y hora incorrecto. Debe ser dd-MM-AAAA HH:mm");
            }
        }

        return fechaHora.format(formatter);
    }

}
