/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg1.irina;

/**Clase que repreesenta a un grafo compuesto de usuarios y sus relacios entre ellos
 * 
 */
public class Grafo {
    private Lista<InfoUsuario> users;
    private int cantidad;
    
    /** COnstructor que inicializa el grafo vacio
     * 
     */
    public Grafo(){
        this.users = new Lista<>();
        this.cantidad = 0;
    }
    
    /** Metodo que busca el usuario
     * 
     * @param nombre
     * @return 
     */
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
    
    /**Metodo que agrega los usuarios al grafo
     * 
     * @param nombre
     * @return 
     */
    public boolean addUsers(String nombre){
        if (searchUser(nombre)== null) {
            InfoUsuario nuevoUsuario = new InfoUsuario(nombre);
            this.getUsers().InsertarFinal(nuevoUsuario);
            this.cantidad++;
            return true;
        }
        return false;
    }
    
    /**Metodo que agrega la relación que tiene un usuario con otro
     * 
     * @param persona
     * @param relacionado 
     */
    public void addConexion(String persona, String relacionado){
        InfoUsuario user = searchUser(persona);
        if (user != null) {
            InfoUsuario userRelacion = searchUser(relacionado);
            if (userRelacion != null) {
                user.conexion.InsertarFinal(relacionado);
            }
        }
    }
    
    /**Metodo que elimina usuarios ademas tambien elimina su conexion a otros usuarios
     * 
     * @param nombre 
     */
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
    }
    
    /**Algoritmo Kosaraju
     * 
     */
    public void Kosaraju(){
        Pila<String> pila = new Pila<>();
        Nodo<InfoUsuario> actual = users.pfirst;
        while (actual != null) {
            actual.getDato().visitado = false;
            actual = actual.getPnext();
        }

    actual = users.pfirst;
    while (actual != null) {
        InfoUsuario u = actual.getDato();
        if (!u.visitado) {
            DFS1(u, pila);
        }
        actual = actual.getPnext();
    }

    Grafo transpuesto = this.transponer();

    Nodo<InfoUsuario> actualTrans = transpuesto.users.pfirst;
    while (actualTrans != null) {
        actualTrans.getDato().visitado = false;
        actualTrans = actualTrans.getPnext();
    }

    int cfcID=1;
    while (!pila.es_vacio()) {
        String nombre = pila.Quitar();
        InfoUsuario u = transpuesto.searchUser(nombre);
        if (u != null && !u.visitado) {
            transpuesto.DFStrans(u, cfcID);
            cfcID++;
        }
    }

    actual = users.pfirst;
    while (actual != null) {
        InfoUsuario u = actual.getDato();
        InfoUsuario userTrans = transpuesto.searchUser(u.nombre);
        if (userTrans != null) {
            u.cfcID = userTrans.cfcID;
        }
        actual = actual.getPnext();
    }

}
    
    /**Primera DFS, orden de finalizacion
     * 
     * @param u
     * @param pila 
     */
    public void DFS1(InfoUsuario u, Pila<String> pila){
        u.visitado = true;
        Nodo<String> vecino = u.conexion.pfirst;
        while (vecino!=null) {
            InfoUsuario siguiente = searchUser(vecino.getDato());
            if (siguiente != null && !siguiente.visitado) {
                DFS1(siguiente, pila);
            }
            vecino = vecino.getPnext();
        }
        pila.Agregar(u.nombre);
    }
    
    /**Metodo que invierte o transpone el grafo
     * 
     * @return 
     */
    public Grafo transponer(){
        Grafo transpuesto = new Grafo();
        Nodo<InfoUsuario> actual = users.pfirst;
        
        while (actual != null) {
            transpuesto.addUsers(actual.getDato().nombre);
            actual = actual.getPnext();
        }
        actual = users.pfirst;
        while (actual!=null) {
            String enlace1 = actual.getDato().nombre;
            Nodo<String> conexiones = actual.getDato().conexion.pfirst;
            while (conexiones!=null) {
                String enlace2 = conexiones.getDato();
                transpuesto.addConexion(enlace2, enlace1);
                conexiones = conexiones.getPnext();
            }
        actual = actual.getPnext();
        }
        return transpuesto;
    }
    
    /**Segunda DFS que utiliza ya el grafo transpuesto
     * 
     * @param u
     * @param cfcID 
     */
    public void DFStrans(InfoUsuario u, int cfcID){
        u.visitado = true;
        u.cfcID = cfcID;
        Nodo<String> vecino = u.conexion.pfirst;
        while (vecino != null) {
            InfoUsuario siguiente = searchUser(vecino.getDato());
            if (siguiente != null && !siguiente.visitado) {
                DFStrans(siguiente, cfcID);
            }
            vecino = vecino.getPnext();
        }
        
    }
    
    /**Metodo para poder clasificar el tipo de relacion ente los usuarios
     * 
     * @param nombre
     * @return 
     */
    public String tipoRelacion(String nombre) {
        InfoUsuario u = searchUser(nombre);
        if (u == null) return "solo";

        boolean sigueAAlguien = u.conexion.size > 0;
        boolean esSeguido = false;

        Nodo<InfoUsuario> actual = users.pfirst;
        while (actual != null) {
            InfoUsuario otro = actual.getDato();
            if (!otro.nombre.equals(nombre) && otro.conexion.indice(nombre) != -1) {
                esSeguido = true;
                break;
            }
        actual = actual.getPnext();
    }

        if (sigueAAlguien && esSeguido) return "mutuo";
        if (sigueAAlguien || esSeguido) return "unico";
        return "solo";
    }
    
    
    
    
    
    

    

    


    
    
    /** getters */

    
    /**
     * @return the users
     */
    public Lista<InfoUsuario> getUsers() {
        return this.users;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return this.cantidad;
    }
}

