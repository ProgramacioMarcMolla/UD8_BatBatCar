package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Reserva;
import es.progcipfpbatoi.model.entities.Usuario;
import java.util.HashSet;

/*
 * Clase que representa a un viaje estándar
*/

public class Viaje {
    private int codigo;
    private Usuario propietario;
    private String ruta;
    private int duracion;
    protected int plazasOfertadas;
    protected int plazasReservadas;
    protected double precio;
    protected boolean isCerrado;
    protected boolean isCancelado;
    protected HashSet<Reserva> reservas;
    
    private final String TIPO = "Estándar";
    
    public Viaje(Usuario propietario, String ruta, int duracion, int plazasOfertadas, double precio) {
        this.propietario = propietario;
        this.ruta = ruta;
        this.duracion = duracion;
        this.plazasOfertadas = plazasOfertadas;
        this.plazasReservadas = 0;
        this.precio = precio;
        this.isCerrado = false;
        this.isCancelado = false;
        this.reservas = new HashSet<>();
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
    
    
    
}

