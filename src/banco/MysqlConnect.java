package banco;

	import java.sql.Connection;
	import java.sql.DriverManager;

	public class MysqlConnect {
	   // atributo
	   public Connection conn = null; 
	   // construtor
	   public MysqlConnect() {
		    String url      = "jdbc:mysql://localhost/";      
	        String dbName   = "projetoTCC";                      
	        String driver   = "com.mysql.jdbc.Driver";       
	        String userName = "root";                         
	        String password = "administrador";                  
	        try {
	            Class.forName(driver); 
	            conn = DriverManager.getConnection(url+dbName, userName, password); 
	        } 
	        catch (Exception e) {
	            System.out.println("Erro:\n" );
	            e.printStackTrace();
	        }
	    }
	    public void closeConnection(){
	        try {
	            conn.close();
		     }catch(Exception e){
	            e.printStackTrace();
	        }
	    } 
	}

