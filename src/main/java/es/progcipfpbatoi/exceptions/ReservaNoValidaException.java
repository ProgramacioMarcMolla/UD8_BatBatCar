/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.exceptions;

/**
 *
 * @author mark
 */
public class ReservaNoValidaException extends Exception {
    public ReservaNoValidaException(){
        super("El código ingresado no corresponde a una reserva. Por favor, inténtelo de nuevo.");
    }
}
