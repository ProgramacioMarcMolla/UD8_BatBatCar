/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.exceptions.CredencialesInvalidasExcepcion;
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
        usuarios.put("andrea", "medaigual");
    }
    
    public Usuario logearUsuario(String username, String password){
        
        try{
            if (usuarios.get(username) == null ? password == null : usuarios.get(username).equals(password)){
            return new Usuario(username,password);
        }
           
        
        throw new CredencialesInvalidasExcepcion();
        }catch (CredencialesInvalidasExcepcion e){
            GestorIO.print(e.getMessage());
            return null;
        }
        
        
        
    }
    
    public boolean usernameInUsuarios(String username){
        return usuarios.containsKey(username);
    }
    
    
    
}
