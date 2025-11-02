/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TodoGrafo;
import EDD.Lista;


/**
 *
 * @author Adriana
 */
public class InfoUsuario {
    public String nombre;
    // corresponde a la lista de usuarios que tienen conexion con nuestro usuario principal
    public Lista<String> conexion; 

    public InfoUsuario(String nombre) {
        this.nombre = nombre;
        this.conexion = new Lista<>();
    }
    
}
