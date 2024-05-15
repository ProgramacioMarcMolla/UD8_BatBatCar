package es.progcipfpbatoi.model.entities;

/**
 *
 * Clase que representa a un usuario de la aplicación
 */
public class Usuario {

    public Usuario(String username,String password) {
        this.nombre = username;
        this.contrasenya = password;
    }
    private String nombre;
    private String contrasenya;
    
    public String getNombre(){
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) obj;
        return nombre.equals(usuario.nombre) && contrasenya.equals(usuario.contrasenya);
    }
}
