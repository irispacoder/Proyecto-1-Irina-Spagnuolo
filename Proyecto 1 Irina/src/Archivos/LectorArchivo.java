/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;
import proyecto.pkg1.irina.Grafo;
import proyecto.pkg1.irina.InfoUsuario;
import proyecto.pkg1.irina.Nodo;

/**Imports permitidos para usar en el proyecto
 * 
 */
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**Esta es la clase que se encarga de la lectura y escritura de archvos txt
 * que contienen los usuarios y sus relaciones
 * 
 */
public class LectorArchivo {
    private boolean datosmod;
    private File archivoOriginal;

    /**Constructor
     * 
     */
    public LectorArchivo() {
        this.datosmod = false;
    }
    
    /**Carga un archivo txt seleccionado por el usuarios e inicializa el grafo con los nombres de usuarios y sus conexiones
     * 
     * @return Grafo construido con el archivo o null si hubo un error
     */
    public Grafo CargarGrafodesdeArchivo(){
        if (this.datosmod){
            int respuesta = JOptionPane.showConfirmDialog(null, "No se han guardado los datos, se perderan los datos", "Advertencia",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (respuesta != JOptionPane.YES_NO_OPTION){
            return null;
        }
    }
    
        
    /**Elige el archivo correcto, en este caso solo permite archivos txt
     * 
     */
    JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione un archivo .txt");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int resultado = fileChooser.showOpenDialog(null);
        
    
        
        /**Aqui se revisa que si el archivo esta vacio se le agregen los titulos "usuaro" y "relaciones entre usuarios" para que pueda ser utilizado
         * 
         */
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            this.archivoOriginal = archivo;
            
            if (archivo.length() == 0) {
                try (PrintWriter write = new PrintWriter(new FileWriter(archivo))) {
                    write.println("usuarios");
                    write.println();
                    write.println("relaciones");
                    write.println();
                    System.out.println("Usted cargo n archivo vacio, automaticamente se fomrateo para poder ser utilizado"); 
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al inicializar el archivo");
                    return null;
                }
                
            }
            try {
                /** Crear un nuevo grafo y leer el archivo 
                 * 
                 */
                Grafo grafo = new Grafo();
                LeerArchivoTXT(archivo, grafo);
                
                this.datosmod = true;  /** Señala que hay datos nuevos */ 
                JOptionPane.showMessageDialog(null, "Archivo cargado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return grafo; /** Va a devolver el grafo ya construido */
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al procesar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;

    }
    
    
        
    /** Metodo que lee el archivo txt
     * 
     * @param archivo
     * @param grafo
     * @throws IOException
     * @throws Exception 
     */
    private void LeerArchivoTXT(File archivo, Grafo grafo) throws IOException, Exception {
        String estado = "inicio"; 

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {
            
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                /** Detecta si es un usario o es relacion
                 * 
                 */
                if (linea.equalsIgnoreCase("usuarios")) {
                    estado = "usuarios";
                    continue;
                } else if (linea.equalsIgnoreCase("relaciones")) {
                    estado = "relaciones";
                    continue;
                }

                /** ve cada linea de acuerdo a la seccion
                 * 
                 */
                if (estado.equals("usuarios")) {
                    if (linea.startsWith("@")) {
                        grafo.addUsers(linea.substring(1));
                    }
                } 
                else if (estado.equals("relaciones")) {
                    int coma = -1;
                    for (int i = 0; i < linea.length(); i++) {
                        if (linea.charAt(i) == ',') {
                            coma = i;
                            break;
                        }
                    }

                    if (coma != -1) {
                        String enlace1 = linea.substring(0, coma).trim().substring(1); 
                        String enlace2 = linea.substring(coma + 1).trim().substring(1); 
                        grafo.addUsers(enlace1); 
                        grafo.addUsers(enlace2);
                        grafo.addConexion(enlace1, enlace2);
                    }
                }
            }
        }
    }
    
     /** Método para cuando el usuario guarde
      * 
      */
    public void Guardado() {
        this.datosmod = false;
    }
    
    /**Metodo para Actuualizar el repositorio
     * 
     * @param grafo 
     */
    
    public void actualizarRepo(Grafo grafo){
        if (archivoOriginal == null) {
            JOptionPane.showMessageDialog(null, "No hay archivo para actualizar");
            return;
        }
        
        try (PrintWriter pw = new PrintWriter(archivoOriginal) ){
            pw.println("usuarios");
            Nodo<InfoUsuario> actual = grafo.getUsers().pfirst;
            while (actual != null) {
              pw.println("@" + actual.getDato().nombre);
              actual = actual.getPnext();    
            }
            
            pw.println();
            pw.println("relaciones");
            actual = grafo.getUsers().pfirst;
            while (actual != null) {
                String enlace1 = actual.getDato().nombre;
                Nodo<String> conexion = actual.getDato().conexion.pfirst;
                while (conexion != null) {
                    String enlace2 = conexion.getDato();
                    pw.println("@" + enlace1 + "," + "@" + enlace2);
                    conexion = conexion.getPnext();
                }
                actual = actual.getPnext();
                
            }
            JOptionPane.showMessageDialog(null, "Su repositorio esta actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el repositorio");
        }
    }
    
    
}
