/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlajson_json_simple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xcheko51x
 */
public class XMLaJSON_json_simple {

    String nomArchivo = "usuarios";
    static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        leerXML(listaUsuarios);
        convertirListaJSONconJSONSimple(listaUsuarios);
        
    }
    
    public static void convertirListaJSONconJSONSimple(List<Usuario> listaUsuarios) {
        
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        
        for(int i = 0 ; i < listaUsuarios.size() ; i++) {
            JSONObject obj1 = new JSONObject();
            obj1.put("id", listaUsuarios.get(i).getId());
            obj1.put("nombre", listaUsuarios.get(i).getNombre());
            obj1.put("telefono", listaUsuarios.get(i).getTelefono());
            array.add(obj1);
        }
        
        obj.put("Usuarios", array);
        
        System.out.println(obj);
    }
    
    public static void leerXML(List<Usuario> listaUsuarios) {
        
        try {
            
            File archivo = new File("usuarios.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            
            NodeList listaNodo = document.getElementsByTagName("USUARIO");
            
            for(int i = 0 ; i < listaNodo.getLength() ; i++) {
                
                Node nodo = listaNodo.item(i);
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element element = (Element) nodo;
                    
                    listaUsuarios.add(
                            new Usuario(
                                    element.getElementsByTagName("ID").item(0).getTextContent(),
                                    element.getElementsByTagName("NOMBRE").item(0).getTextContent(),
                                    element.getElementsByTagName("TELEFONO").item(0).getTextContent()
                            )
                    );
                    
                }
                
            }
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
