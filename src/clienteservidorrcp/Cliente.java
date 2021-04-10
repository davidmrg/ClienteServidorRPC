/*
Lógica del cliente: 
*/
package clienteservidorrcp;

import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.XmlRpcClient;


public class Cliente {

   
    public static void main(String[] args) {
        // Inicializamos variables vacias que definimos en Suma.java
        String n1="", n2="";
        
        // object que tendrá el resultado que retornará Suma.java
        Object resultado;
        
        try {
           // crear conexión IP para cliente, configurar IP del server apuntando al port
           XmlRpcClient client = new XmlRpcClient("http://172.20.10.4:8080");
           // Estructura de tipo Vector para manejar los parametros a enviar al server y de ahí hacia la clase Suma
           Vector<String> parametros = new Vector<String>();
           
           JOptionPane.showMessageDialog(null, "Cliente se ha conectado");
           
           while(true){
               String menu = JOptionPane.showInputDialog(null, "Suma de 2 numeros\n" + "1. Sumar\n" + "2. Salir\n", "Cliente Serviro RPC", JOptionPane.DEFAULT_OPTION);
               
               switch(menu){
                   case "1":
                       n1 = JOptionPane.showInputDialog(null, "Primer numero", "Suma", JOptionPane.QUESTION_MESSAGE);
                       n2 = JOptionPane.showInputDialog(null, "Segundo numero", "Suma", JOptionPane.QUESTION_MESSAGE);
                       
                       // agregar vars al Vector parametros:
                       parametros.addElement(n1);
                       parametros.addElement(n2);
                       
                       // enviar parms al método suma que está en miServidorRPC
                       resultado = client.execute("miServidorRPC.suma", parametros);
                       
                       // mostramos la suma:
                       JOptionPane.showMessageDialog(null, "la suma es: " + resultado);
                       
                       // limpiar el Vector:
                       parametros.clear();
                       break;
                    
                   case "2":
                       JOptionPane.showMessageDialog(null, "Saliendo", null, JOptionPane.WARNING_MESSAGE);
                       System.exit(0);
                       break;
                       
               }
           }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        } 
               
        
    }
    
    
}
