/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.exceptions;

/**
 *
 * @author mark
 */
public class UsuarioSinEstablecerException extends Exception {
    public UsuarioSinEstablecerException(){
        super("No se ha iniciado sesión");
    }
}
