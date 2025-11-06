/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

public class NodoPila {
    private int dato;
    private NodoPila next;

    public NodoPila(int dato) {
        this.dato = dato;
        this.next = null;
    }

    /**
     * @return the dato
     */
    public int getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(int dato) {
        this.dato = dato;
    }

    /**
     * @return the next
     */
    public NodoPila getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(NodoPila next) {
        this.next = next;
    }
    
    

    
    
    
    
}
