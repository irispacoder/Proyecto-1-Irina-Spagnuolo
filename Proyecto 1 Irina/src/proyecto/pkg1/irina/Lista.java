/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

/**
 *
 * Se implementa una lista enlazada simple para poder manejar los usuarios
 * @param <T> tipo de dato que almacena la lista
 */
public class Lista<T> {
    public Nodo<T> pfirst;
    public int size;

    /**Constructor que inicializa la lista vacia
     * 
     */
    public Lista() {
        this.pfirst = null;
        this.size = 0;
    }
    
    /** Nos dice si la lista esta vacia
     * 
     * @return 
     */
    public boolean es_vacio(){
        return pfirst == null;
    }
    
    /**Tamaño de la lista
     * 
     * @return 
     */
    public int Size(){
        return this.size;
    }
    
    /** Añade un elemento al final de la lista
     * 
     * @param dato 
     */
    public void InsertarFinal(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if (this.es_vacio()){
            this.pfirst = nuevo;
        }else{
            Nodo<T> aux = this.pfirst;
            while (aux.getPnext() != null){
                aux = aux.getPnext();
            }
            aux.setPnext(nuevo);
        }
    this.size++;
    }
    
    /** Añade un elemento al inicio de la lista
     * 
     * @param dato 
     */
    public void InsertarInicio (T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setPnext(this.pfirst);
        this.pfirst = nuevo;
        this.size++;
    }
    
    /** Se obtiene un elemento en el numero que se le pase
     * 
     * @param indice
     * @return 
     */
    public T ObtenerIndice(int indice){
        if (indice < 0 || indice >= this.size){
            return null;
        }
        Nodo<T> aux = this.pfirst;
        for (int i = 0; i < indice; i++) {
            aux = aux.getPnext();
            }
        return aux.getDato();
        }
    
    /** Se elimina un elemento en el indice que se indica
     * 
     * @param indice
     * @return 
     */
    public T EliminarIndice(int indice){
        if (indice < 0 || indice >= this.size){
        return null;
        }
        T dato;
        if (indice == 0){
            dato = this.pfirst.getDato();
            this.pfirst = this.pfirst.getPnext();
        }else{
            Nodo<T> aux = this.pfirst;
            for (int i = 0; i < indice - 1; i++) {
                aux = aux.getPnext();
            }
        Nodo<T> nodoaeliminar = aux.getPnext();
         dato = nodoaeliminar.getDato();
        aux.setPnext(nodoaeliminar.getPnext());
        }
    this.size--;
    return dato;
    }
    
    /** Busca un dato de la lista y devuelve su indice, en caso de no encontrar devuelve -1
     * 
     * @param dato
     * @return 
     */
    public int indice(T dato){
        Nodo<T> aux = this.pfirst;
        for (int i = 0; i < this.size; i++) {
            if (aux.getDato().equals(dato)){
                return i;
            }
        aux = aux.getPnext();
        }
    return -1;
    }    
}
