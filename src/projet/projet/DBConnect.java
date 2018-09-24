package projet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
    
    private static Connection connec;
    
    public static Connection getConnection(){
        
    
    
        try{    
        
        connec = DriverManager.getConnection("jdbc:mysql://localhost/testk?useSSL=false&useUnicode=true&usecharacterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&", "root", "");
        if (connec != null){
            System.out.println("Lecture DB");
        }
        }
        catch (SQLException err){
            System.out.println(err.getMessage());
        }
    return connec;    
    }   
    
    
    
}
