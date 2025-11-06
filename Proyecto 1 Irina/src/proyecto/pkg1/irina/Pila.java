/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

/**
 *
 * Se crea una pila que va a utilizar como estructura subyacente una 
 * lista simple
 * @param <T>
 */
public class Pila<T> {
    private Lista<T> lista;
    private int dato;
    private NodoPila siguiente;


    public Pila() {
        this.lista = new Lista<>();
    }
    
    /** Retorna si esta vacia
     * @return  */
    public boolean es_vacio() {
        return lista.es_vacio();
    }
    
    /** Se agrega un elemento a la cima de la pila
     * @param dato */
    public void Agregar(T dato){
        lista.InsertarInicio(dato);
    }
    
    /** Se saca el elemento que esta en la cima y lo devuelve
     * @return  */
    public T Quitar(){
        if (this.es_vacio()){
            throw new RuntimeException("Lista vacía");
        }
    return lista.EliminarIndice(0);
    } 
    
    /** Devuelve el elemento que esta en la cima de la pila
     * @return  */
    public T Cima(){
        if (this.es_vacio()){
            throw new RuntimeException("Lista vacía");
        }
    return lista.ObtenerIndice(0);
    } 

    
}