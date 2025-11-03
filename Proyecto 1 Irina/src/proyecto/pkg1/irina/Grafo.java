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
        Nodo<InfoUsuario> aux = this.getUsers().pfirst;
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
            this.getUsers().InsertarFinal(nuevoUsuario);
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
    
    /**Metodo que elimina usuarios ademas tambien elimina su conexion a otros usuarios*/
    
    public void deleteUser(String nombre) {
    Nodo<InfoUsuario> actual = getUsers().pfirst;
    Nodo<InfoUsuario> previo = null;

    while (actual != null) {
        if (actual.getDato().nombre.equals(nombre)) {
            if (previo == null) {
                users.pfirst = actual.getPnext();
            } else {
                previo.setPnext(actual.getPnext());
            }
            cantidad--;
            break;
        }
        previo = actual;
        actual = actual.getPnext();
    }

    Nodo<InfoUsuario> ref = getUsers().pfirst;
    while (ref != null) {
        Lista<String> conexiones = ref.getDato().conexion;
        Nodo<String> aux = conexiones.pfirst;
        Nodo<String> prev = null;

        while (aux != null) {
            if (aux.getDato().equals(nombre)) {
                if (prev == null) {
                    conexiones.pfirst = aux.getPnext();
                } else {
                    prev.setPnext(aux.getPnext());
                }
                conexiones.size--;
                break;
            }
            prev = aux;
            aux = aux.getPnext();
        }

        ref = ref.getPnext();
    }
    

    
    
    /** getters */

    }
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

