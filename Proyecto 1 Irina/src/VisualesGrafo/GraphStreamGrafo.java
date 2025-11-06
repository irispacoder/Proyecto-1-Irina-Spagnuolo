/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VisualesGrafo;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
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
            InfoUsuario usuario = actual.getDato();
            String nombre = usuario.nombre;
            int cfcID = usuario.cfcID;

            Node nodo = graph.addNode(nombre);
            nodo.setAttribute("ui.label", "@" + nombre);
            String tipo = grafo.tipoRelacion(nombre);
            String color = switch(tipo){
                case "mutuo" -> "green";
                case "unico" -> "orange";
                case "solo" -> "gray";
                default -> "black";
            };
            nodo.setAttribute("ui.style", "fill-color: " + color + "; size: 30px;");

            actual = actual.getPnext();
        }

    
        actual = grafo.getUsers().pfirst;
        while (actual != null) {
            String enlace1 = actual.getDato().nombre;
            Nodo<String> conexion = actual.getDato().conexion.pfirst;
            while (conexion != null) {
                String enlace2 = conexion.getDato();
                String idArista = enlace1 + "->" + enlace2;
                if (graph.getEdge(idArista) == null && graph.getNode(enlace2) != null) {
                    graph.addEdge(idArista, enlace1, enlace2, true);
                }
                conexion = conexion.getPnext();
            }
            actual = actual.getPnext();
        }
        
         
        
        
        graph.setAttribute("ui.stylesheet", 
            "node {" +        
            "   size: 30px;" +                  
            "   text-size: 18px;" +             
            "   text-color: black;" +           
            "   text-style: bold;" +            
            "   stroke-mode: plain;" +
            "   stroke-color: black;" +
            "}" +
            "edge {" +
            "   fill-color: black;" +            
            "   arrow-shape: arrow;" +          
            "   arrow-size: 16px, 5px;" +       
            "}"
        );

        graph.display();
    
    }
    
    
}
