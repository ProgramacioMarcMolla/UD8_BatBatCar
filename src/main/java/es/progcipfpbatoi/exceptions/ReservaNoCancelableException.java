/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.exceptions;

/**
 *
 * @author mark
 */
public class ReservaNoCancelableException extends Exception {
    public ReservaNoCancelableException(){
        super("La reserva no es de tipo cancelable");
    }
}
