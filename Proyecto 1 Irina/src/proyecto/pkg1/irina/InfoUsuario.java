/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

/**
 *Clase que contiene la informacion necesaria de un usuario para ejecutar el grafo
 * 
 */
public class InfoUsuario {
    public String nombre;
    /**corresponde a la lista de usuarios que tienen conexion con nuestro usuario principal
     * 
     */
    public Lista<String> conexion;
    /** se utiliza para marcar la asistencia de un usuario y evitar bucle infinito
     * 
     */
    public boolean visitado; 
     /** pintarlo para saber a que componente esta asociado
     * 
     * @param nombre 
     */
    public int cfcID;

    /**Constructor
     * 
     * @param nombre 
     */
    public InfoUsuario(String nombre) {
        this.nombre = nombre;
        this.conexion = new Lista<>();
        this.visitado = false;
        this.cfcID = 0;
    } 
}
