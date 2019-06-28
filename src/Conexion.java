import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class Conexion {
    
    /*  La idea es tener una única clase que maneje la conexión a la base de
        datos a utilizar, esto nos facilita el trabajo a la hora de cambiar la
        la persistencia de nuestros datos, solo tendriamos que modificar esta
        clase para apuntar a la memoria ram, a un archivo o a otro gestor de
        bases de datos (Microsoft SQL Server, PostgreSQL etc).
    */
    
    //Atributo para gestionar la conexión a la base de datos:
    private Connection cnn;
    
    //Constructor:
    public Conexion() {
    }
    
        //Getter:
    public Connection getCnn() {
        return cnn;
    }
    
    //Método para abrir la conexión:
    public boolean abrir(String direccion, String puerto, String base, String user, String pass){   
        
        boolean openConnection= false;

        //Url de conexión a la base de datos
        String url= "jdbc:mysql://"+ direccion+":"+ puerto+ "/"+ base+ "?serverTimezone=GMT";        
        
        //Driver de conexión a MySql:
        String driver = "com.mysql.cj.jdbc.Driver";
        
        try { 
            Class.forName(driver);
            cnn = DriverManager.getConnection(url, user, pass);
            openConnection= true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "        REVISAR URL, PUERTO, DB, USER Y PASS            \n\n"+ ex.getMessage()+"        \n\n", "ERROR EN LOS DATOS", JOptionPane.ERROR_MESSAGE);
        }
        return openConnection;
    }
    
    //Método para cerrar la conexión:
    public boolean cerrar(){
        boolean closeConnection= false;
        try {
            cnn.close();
            closeConnection= true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error:\n\n"+ ex.getMessage()+"\n\nCerrando el sistema\n\n", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return closeConnection;
    }
}
