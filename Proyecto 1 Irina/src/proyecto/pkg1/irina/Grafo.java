/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

public class Grafo {
    private Lista<InfoUsuario> users;
    private int cantidad;
    
    /** se utiliza para pasar la cantidad de usuarios */
    public Grafo(){
        this.users = new Lista<>();
        this.cantidad = 0;
    }
    
    /** busca el usuario del que se quiere saber */
    public InfoUsuario searchUser(String nombre){
        Nodo<InfoUsuario> aux = this.users.pfirst;
        while (aux != null){
            if (aux.getDato().nombre.equals(nombre)){
            return aux.getDato();
            }
        aux = aux.getPnext();
        }
    return null;
    }
    
    /**Metodo que agrega los usuarios*/
    
    public void addUsers(String nombre){
        if (searchUser(nombre)== null) {
            InfoUsuario nuevoUsuario = new InfoUsuario(nombre);
            this.users.InsertarFinal(nuevoUsuario);
            this.cantidad++;
        }
    }
    
    /**Metodo que agrega la relación que tiene un usuario con otro*/
    public void addConexion(String persona, String relacionado){
        InfoUsuario user = searchUser(persona);
        if (user != null) {
            InfoUsuario userRelacion = searchUser(relacionado);
            if (userRelacion != null) {
                user.conexion.InsertarFinal(relacionado);
            }
        }
    }
    
    
    /** getters */

    /**
     * @return the users
     */
    public Lista<InfoUsuario> getUsers() {
        return users;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }
    
    
    
    
}

