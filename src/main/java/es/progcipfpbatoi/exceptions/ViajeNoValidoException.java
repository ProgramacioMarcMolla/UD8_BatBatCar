/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.exceptions;

/**
 *
 * @author mark
 */
public class ViajeNoValidoException extends Exception {
    public ViajeNoValidoException(){
    super("Este viaje no pertenece a un tipo válido y/o el código proporcionado no es válido");
    }
        
}
