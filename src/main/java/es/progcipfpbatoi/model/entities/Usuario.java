package es.progcipfpbatoi.model.entities;

/**
 *
 * Clase que representa a un usuario de la aplicación
 */
public class Usuario {

    public Usuario(
            String username,
            String password) {
    }
    private String nombre;
    private String contrasenya;
    
    public String getNombre(){
        return nombre;
    }
}
