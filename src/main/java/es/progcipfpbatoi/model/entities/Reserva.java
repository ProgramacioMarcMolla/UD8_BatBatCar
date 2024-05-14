/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.entities;

import java.util.Objects;

/**
 *
 * @author mark
 */
public class Reserva {
    private int codigo;
    private Usuario usuario;
    private int numeroPlazasSolicitadas;
    
    
    
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
    
    
    
}
