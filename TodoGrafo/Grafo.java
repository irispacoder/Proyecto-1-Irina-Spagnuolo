/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TodoGrafo;
import EDD.Nodo;

/**
 *
 * Clase de creacion del grafo
 */
public class Grafo {
    private InfoUsuario[] users;
    private int cantidad;
    
    
    //se utiliza para pasar la contidad de usuarios
    public Grafo(int MaxUsers){
        users = new InfoUsuario[MaxUsers];
        cantidad = 0;
    }
    
    //busca el usuario del que se quiere saber
    public InfoUsuario searchUser(String nombre) {
        for (int i = 0; i < getCantidad(); i++) {
            if (getUsers()[i].nombre.equals(nombre)) return getUsers()[i];
        }
        return null;
    }
    
    //agrega usuarios
    public void addUser(String nombre) {
        if (searchUser(nombre) == null) {
            getUsers()[cantidad++] = new InfoUsuario(nombre);
        }
    }
    
    //aqui se agrega la relacion que tiene un usuario con otro
    public void addConexion(String persona, String relacionado) {
        InfoUsuario user = searchUser(persona);
        if (user != null) {
            user.conexion.InsertarFinal(relacionado);
        }
    }
    

    //getters and setters
    public InfoUsuario[] getUsers() {
        return users;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public void printGrafo() {
        for (int i = 0; i < cantidad; i++) {
            InfoUsuario u = users[i];
            System.out.println(u.nombre + " -> ");
            Nodo<String> aux = u.conexion.pfirst;
            while (aux != null) {
                System.out.print(aux.getDato() + " ");
                aux = aux.getPnext();
            }
            System.out.println();
        }
    }
    
}
