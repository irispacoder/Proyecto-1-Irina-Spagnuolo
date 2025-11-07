/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

/**
 *Se crea un nodo genérico para poder ser reutilizado e implementado 
 * en todas las demás clases.
 * @param <T> tipo de dato que almacena nuestrom nodo
 *
 */
public class Nodo<T> {
    private Nodo<T> pnext;
    private T dato;

    
    /**Constructor que inicializa el nodo con un dato
     * 
     * @param dato 
     */
    public Nodo(T dato) {
        this.pnext = null;
        this.dato = dato;
    }

    /**
     * @return the pnext
     */
    public Nodo<T> getPnext() {
        return pnext;
    }

    /**
     * @param pnext the pnext to set
     */
    public void setPnext(Nodo<T> pnext) {
        this.pnext = pnext;
    }

    /**
     * @return the dato
     */
    public T getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(T dato) {
        this.dato = dato;
    }
    
    
}