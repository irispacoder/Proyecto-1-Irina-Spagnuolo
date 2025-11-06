/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VisualesGrafo;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import proyecto.pkg1.irina.Grafo;
import proyecto.pkg1.irina.InfoUsuario;
import proyecto.pkg1.irina.Nodo;

public class GraphStreamGrafo {
    public static void Show(Grafo grafo){
        if (grafo == null || grafo.getUsers().pfirst == null) {
            System.out.println("No hay grafo cargado para visualizar.");
            return;
        }
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Usuarios");
        
        Nodo<InfoUsuario> actual = grafo.getUsers().pfirst;
        while (actual != null) {
            String nombre = actual.getDato().nombre;
            Node nodo = graph.addNode(nombre);
            nodo.setAttribute("ui.label", "@" + nombre);
            actual = actual.getPnext();
        }
        
        actual = grafo.getUsers().pfirst;
        while (actual != null) {
            String origen = actual.getDato().nombre;
            Nodo<String> conexion = actual.getDato().conexion.pfirst;
            while (conexion != null) {
                String destino = conexion.getDato();
                String idArista = origen + "->" + destino;
                if (graph.getEdge(idArista) == null) {
                    graph.addEdge(idArista, origen, destino, true);
                }
                conexion = conexion.getPnext();
            }
            actual = actual.getPnext();
        }

        graph.display();
    
    }
    
    
}
