/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

/**
 *
 */
public class InfoUsuario {
    public String nombre;
    /**corresponde a la lista de usuarios que tienen conexion con 
    nuestro usuario principal*/
    public Lista<String> conexion;
    public boolean visitado; /** se utiliza para marcar la asistencia de un usuario y evitar bucle infinito*/
    public int cfcID; /** pintarlo para saber a que componente esta asociado */

    public InfoUsuario(String nombre) {
        this.nombre = nombre;
        this.conexion = new Lista<>();
        this.visitado = false;
        this.cfcID = 0;
    } 
}
