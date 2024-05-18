package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

/*
 * Clase que representa a un viaje estándar
*/

public class Viaje {
    protected int codigo;
    protected Usuario propietario;
    protected String ruta;
    protected int duracion;
    protected int plazasOfertadas;
    protected int plazasReservadas;
    protected double precio;
    protected boolean isCerrado;
    protected boolean isCancelado;
    protected ArrayList<Reserva> reservas;
    protected LocalDateTime fechaSalida;
    
    protected final String TIPO = "Estándar";
    
    public Viaje(Usuario propietario, String ruta, int duracion, int plazasOfertadas, double precio, String fecha) {
        this.propietario = propietario;
        this.ruta = ruta;
        this.duracion = duracion;
        this.plazasOfertadas = plazasOfertadas;
        this.plazasReservadas = 0;
        this.precio = precio;
        this.isCerrado = false;
        this.isCancelado = false;
        this.reservas = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.fechaSalida = LocalDateTime.parse(fecha, formatter); 
    }
    
    public boolean getIsCancelado(){
        return this.isCancelado;
    }
    
    @Override
    public String toString(){
        return "Viaje de tipo " + TIPO + "del propietario " + this.propietario.getNombre() + "con código" + this.codigo + " y ruta " + ruta + " con " + plazasOfertadas + " plazas ofertadas ";
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public Usuario getUsuario(){
        return this.propietario;
    }

    public String getUsernamePropietario() {
        return propietario.getNombre();
    }

    public String getRuta() {
        return ruta;
    }

    public double getPrecio() {
        return precio;
    }

   

    public String getTIPO() {
        return "Viaje "+TIPO;
    }
    
    public int getPlazasDisponibles(){
        return this.plazasOfertadas-this.plazasReservadas;
    }
    
    public void setIsCancelado(boolean isCancelado){
        this.isCancelado = isCancelado;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Viaje viaje = (Viaje) obj;
        return codigo == viaje.codigo;
    }
    
    public boolean getIsCerrado(){
        return this.isCerrado;
    }

    public void anyadirReserva(Reserva reserva) {
        
        for(int i = 0; i < this.reservas.size(); i++){
            if(reserva.getNombreUsuario().equals(this.reservas.get(i).getNombreUsuario())){
                GestorIO.print("Un usuario no puede hacer más de una reserva.");
                return;
            }
        }
        
        this.reservas.add(reserva);
        
        this.plazasReservadas += reserva.getPlazasSolicitadas();
        
        if(this.plazasReservadas == this.plazasOfertadas){
            this.isCerrado = true;
        }
        
    }
    
   
    
    public ArrayList<Reserva> getReservas(){
        return this.reservas;
    }
    
    /**
     * Si el nuevo numero de plazas es un numero negativo, ejemplo, de 3 plazas reservadas pasamos a 1, el parametro sera negativo. -2
     * @param plazasReservadasAAnyadir 
     */
    public void anyadirPlazasReservadas(int plazasReservadasAAnyadir){
        this.plazasReservadas += plazasReservadasAAnyadir;
        
        if(this.plazasReservadas == this.plazasOfertadas){
            this.isCerrado = true;
        }
        
    }
    
    public LocalDateTime getFecha(){
        return this.fechaSalida;
    }
    
    public String getFechaToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm");
        return this.fechaSalida.format(formatter);
    }

    
    
}

