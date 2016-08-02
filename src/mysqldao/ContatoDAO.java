package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ContatoTO;
import banco.MysqlConnect;

public class ContatoDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public ContatoDAO()
	   {
	      super();
	   }
	   public String ultimoCodigoUsuario()
	   {
		   	String codigo = null;
		   	
	         try
	         {
	            String sql = "SELECT MAX(CD_USUARIO) FROM USUARIO";
	            st= conn.prepareStatement(sql);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	codigo = resultSet.getString("MAX(CD_USUARIO)");
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
			return codigo;
	   
	   }
	   
	   public String ultimoCodigoEmpregado()
	   {
		   	String codigo = null;
		   	
	         try
	         {
	            String sql = "SELECT MAX(CD_EMPREGADO) FROM EMPREGADO";
	            st= conn.prepareStatement(sql);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	codigo = resultSet.getString("MAX(CD_EMPREGADO)");
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
			return codigo;
	   
	   }
	   public boolean cadastrarUsuario(ContatoTO contatoTO)
	   {
	      try{
	    	
	    	String codigoUsuario = ultimoCodigoUsuario();
	    	 
	         String sql = "INSERT INTO CONTATO(TIPO_CONTATO, NUMERO,"
	         		+ "FK_USUARIO) VALUES (?,?,?)";     
	         
	         st = conn.prepareStatement(sql);
	         st.setString(1,contatoTO.getTipoContato());
	         st.setString(2,contatoTO.getNumero());
	         st.setString(3,codigoUsuario);
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }

	   //  public boolean cadastrarEmpregado(EmpregadoTO empregadoTO)

	   public boolean alterarUsuario(ContatoTO contatoTO)
	   { 
		      try{
		         String sql = "UPDATE CONTATO SET TIPO_CONTATO = ?, NUMERO = ? WHERE FK_USUARIO = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1,contatoTO.getTipoContato());
				         st.setString(2,contatoTO.getNumero());
				         st.setString(3,contatoTO.getCodigoUsuario());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   
	   public ContatoTO pesquisarUsuario(String codg)
	   {
		   	 ContatoTO contatoTO = null;
	         try
	         {
	            String sql = " SELECT * FROM CONTATO WHERE FK_USUARIO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codg);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	contatoTO = new ContatoTO();
	            	contatoTO.setTipoContato(resultSet.getString("TIPO_CONTATO"));
	            	contatoTO.setNumero(resultSet.getString("NUMERO"));
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
	      
	         return contatoTO;
	      
	   }
	   
	public Boolean cadastrarEmrpegado(ContatoTO contatoTO) {
		try{
	    	
	    	String codigoEmpregado = ultimoCodigoEmpregado();
	    	 
	         String sql = "INSERT INTO CONTATO(TIPO_CONTATO, NUMERO,"
	         		+ "FK_EMPREGADO) VALUES (?,?,?)";     
	         
	         st = conn.prepareStatement(sql);
	         st.setString(1,contatoTO.getTipoContato());
	         st.setString(2,contatoTO.getNumero());
	         st.setString(3,codigoEmpregado);
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	}
	
	public ContatoTO pesquisarEmpregado(String codg) {
		
		ContatoTO contatoTO = null;
        try
        {
           String sql = " SELECT * FROM CONTATO WHERE FK_EMPREGADO = ?";
           st= conn.prepareStatement(sql);
           st.setString(1,codg);
           ResultSet resultSet = st.executeQuery();
           if(resultSet.next())
           {
           	contatoTO = new ContatoTO();
           	contatoTO.setTipoContato(resultSet.getString("TIPO_CONTATO"));
           	contatoTO.setNumero(resultSet.getString("NUMERO"));
           }	
           st.close();
        }
           catch(Exception e)
           {
              e.printStackTrace();
              return null;
           }
     
        return contatoTO;
	}
	
	 public boolean alterarEmpregado(ContatoTO contatoTO)
	   { 
		      try{
		         String sql = "UPDATE CONTATO SET TIPO_CONTATO = ?, NUMERO = ? WHERE FK_EMPREGADO = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1,contatoTO.getTipoContato());
				         st.setString(2,contatoTO.getNumero());
				         st.setString(3,contatoTO.getCodigoEmpregado());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }
	
}
