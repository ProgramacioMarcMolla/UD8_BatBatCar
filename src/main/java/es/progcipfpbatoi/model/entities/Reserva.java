/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author mark
 */
public class Reserva {
    private int codigo;
    private Usuario usuario;
    private int numeroPlazasSolicitadas;
    LocalDateTime fecha;

    public Reserva(int numReserva, Usuario usuario,int plazasSolicitadas) {
        this.codigo = numReserva;
        this.usuario = usuario;
        this.numeroPlazasSolicitadas = plazasSolicitadas;
        this.fecha = LocalDateTime.now();
    }
    public String getFechaToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm");
        return this.fecha.format(formatter);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return codigo == reserva.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public String getNombreUsuario(){
        return this.usuario.getNombre();
    }
    
    public int getPlazasSolicitadas(){
        return this.numeroPlazasSolicitadas;
    }
    
    public void setPlazasSolicitadas(int numPlazasSolicitadas){
        this.numeroPlazasSolicitadas = numPlazasSolicitadas;
    }
    
    
}
