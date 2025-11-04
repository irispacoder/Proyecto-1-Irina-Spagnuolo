/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;
import proyecto.pkg1.irina.Grafo;
import proyecto.pkg1.irina.InfoUsuario;
import proyecto.pkg1.irina.Lista;
import proyecto.pkg1.irina.Nodo;
import proyecto.pkg1.irina.Pila;

/**Imports permitidos para usar en el proyecto*/

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LectorArchivo {
    private boolean datosmod;

    public LectorArchivo() {
        this.datosmod = false;
    }
    
    public Grafo CargarGrafodesdeArchivo(){
        if (this.datosmod){
            int respuesta = JOptionPane.showConfirmDialog(null, "No se han guardado los datos, se perderan los datos", "Advertencia",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (respuesta != JOptionPane.YES_NO_OPTION){
            return null;
        }
    }
    
        
        /**Elige el archivo correcto, en este caso solo permite archivos txt*/
    JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione un archivo .txt");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int resultado = fileChooser.showOpenDialog(null);
        
    
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                /** Crear un nuevo grafo y leer el archivo */
                Grafo grafo = new Grafo();
                LeerArchivoTXT(archivo, grafo);
                
                this.datosmod = true;  /** Señala que hay datos nuevos */ 
                JOptionPane.showMessageDialog(null, "Archivo cargado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return grafo; /** Va a devolver el grafo ya construido */
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al procesar el archivo: " + e.getMessage(), "Error", 
                JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;

    }
    
    
        
    /** Metodo que lee el archivo txt */
    private void LeerArchivoTXT(File archivo, Grafo grafo) throws IOException, Exception {
        String estado = "inicio"; /** para saber que se esta leyendo si usuarios o relaciones */ 

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {
            
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                /** Detecta si es un usario o es relacion*/
                if (linea.equalsIgnoreCase("usuarios")) {
                    estado = "usuarios";
                    continue;
                } else if (linea.equalsIgnoreCase("relaciones entre usuarios")) {
                    estado = "relaciones";
                    continue;
                }

                /** ve cada linea de acuerdo a la seccion */
                if (estado.equals("usuarios")) {
                    if (linea.startsWith("@")) {
                        grafo.addUsers(linea.substring(1)); /** quita el @ */
                    }
                } 
                else if (estado.equals("relaciones")) {
                    /** separa el origen y destino con una coma */
                    int coma = -1;
                    for (int i = 0; i < linea.length(); i++) {
                        if (linea.charAt(i) == ',') {
                            coma = i;
                            break;
                        }
                    }

                    if (coma != -1) {
                        String origen = linea.substring(0, coma).trim().substring(1); /** quita el @ */
                        String destino = linea.substring(coma + 1).trim().substring(1); /** quita el @ */
                        /** se agregan los usuarios en caso de no existir */
                        grafo.addUsers(origen); 
                        grafo.addUsers(destino);
                        /** se agrega la conexion */
                        grafo.addConexion(origen, destino);
                    }
                }
            }
        }
    }
    
     /** Método para cuando el usuario guarde */
    public void Guardado() {
        this.datosmod = false;
    }
    
    
}
