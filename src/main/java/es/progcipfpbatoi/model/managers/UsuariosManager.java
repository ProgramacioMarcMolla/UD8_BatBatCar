/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;
import java.util.HashMap;

/**
 *
 * @author mark
 */
public class UsuariosManager {
    private HashMap<String,String> usuarios;
    
    public UsuariosManager(){
        usuarios = new HashMap<>();
        //El programa no contempla la creación de nuevos usuarios.
        usuarios.put("marc", "1234");
        usuarios.put("ana", "1234");
        usuarios.put("manel", "1234");
    }
    
    public Usuario logearUsuario(String username, String password){
        
        
        
        if (usuarios.get(username) == null ? password == null : usuarios.get(username).equals(password)){
            return new Usuario(username,password);
        }
        
        GestorIO.print("Error, la contraseña introducida es érronea");
        return null;
        
    }
    
    public boolean usernameInUsuarios(String username){
        return usuarios.containsKey(username);
    }
    
    
    
}
