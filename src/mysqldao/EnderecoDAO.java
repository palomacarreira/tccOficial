package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.EnderecoTO;
import banco.MysqlConnect;

public class EnderecoDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public EnderecoDAO()
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
	   
	   public boolean cadastrarUsuario(EnderecoTO enderecoTO)
	   {
		   String codigoUsuario = ultimoCodigoUsuario();
		   
	      try{	  
	         String sql = "INSERT INTO ENDERECO(LOGRADOURO, cidade, estado, numero,"
	         		+ "complemento, cep, bairro, FK_USUARIO) values (?,?,?,?,?,?,?,?)";     
	         st = conn.prepareStatement(sql);
	         st.setString(1,enderecoTO.getEndereco());
	         st.setString(2,enderecoTO.getCidade());
	         st.setString(3,enderecoTO.getEstado());
	         st.setInt(4,enderecoTO.getNumero());
	         st.setString(5,enderecoTO.getComplemento());
	         st.setString(6,enderecoTO.getCep());
	         st.setString(7,enderecoTO.getBairro());
	         st.setString(8,codigoUsuario);
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }

	   public boolean cadastrarEmpregado(EnderecoTO enderecoTO)
	   {
		   String codigoEmpregado = ultimoCodigoEmpregado();
		   
	      try{	  
	         String sql = "INSERT INTO ENDERECO(LOGRADOURO, cidade, estado, numero,"
	         		+ "complemento, cep, bairro, FK_EMPREGADO) values (?,?,?,?,?,?,?,?)";     
	         st = conn.prepareStatement(sql);
	         st.setString(1,enderecoTO.getEndereco());
	         st.setString(2,enderecoTO.getCidade());
	         st.setString(3,enderecoTO.getEstado());
	         st.setInt(4,enderecoTO.getNumero());
	         st.setString(5,enderecoTO.getComplemento());
	         st.setString(6,enderecoTO.getCep());
	         st.setString(7,enderecoTO.getBairro());
	         st.setString(8,codigoEmpregado);
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }


	   public boolean alterarUsuario(EnderecoTO enderecoTO)
	   { 
		      try{
		         String sql = " UPDATE ENDERECO SET LOGRADOURO = ?, cidade = ?, estado = ?, numero = ?, complemento = ?, cep = ?, bairro = ? WHERE FK_USUARIO = ?";
		        		 
				         st = conn.prepareStatement(sql); 
				         st.setString(1,enderecoTO.getEndereco());
				         st.setString(2,enderecoTO.getCidade());
				         st.setString(3,enderecoTO.getEstado());
				         st.setInt(4,enderecoTO.getNumero());
				         st.setString(5,enderecoTO.getComplemento());
				         st.setString(6,enderecoTO.getCep());
				         st.setString(7,enderecoTO.getBairro());
				         st.setString(8,enderecoTO.getCodigoUsuario());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   public boolean alterarEmpregado(EnderecoTO enderecoTO)
	   { 
		      try{
		         String sql = " UPDATE ENDERECO SET LOGRADOURO = ?, cidade = ?, estado = ?, numero = ?, complemento = ?, cep = ?, bairro = ? WHERE FK_EMPREGADO = ?";
		        		 
				         st = conn.prepareStatement(sql); 
				         st.setString(1,enderecoTO.getEndereco());
				         st.setString(2,enderecoTO.getCidade());
				         st.setString(3,enderecoTO.getEstado());
				         st.setInt(4,enderecoTO.getNumero());
				         st.setString(5,enderecoTO.getComplemento());
				         st.setString(6,enderecoTO.getCep());
				         st.setString(7,enderecoTO.getBairro());
				         st.setString(8,enderecoTO.getCodigoEmpregado());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }
	   
	   public EnderecoTO pesquisarUsuario(String codg)
	   {
		   	 EnderecoTO enderecoTO = null;
	         try
	         {
	            String sql = " SELECT * FROM ENDERECO WHERE FK_USUARIO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codg);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	enderecoTO = new EnderecoTO();
	            	enderecoTO.setEndereco(resultSet.getString("LOGRADOURO"));
	            	enderecoTO.setCidade(resultSet.getString("cidade"));
	            	enderecoTO.setEstado(resultSet.getString("estado"));
	            	enderecoTO.setNumero(resultSet.getInt("numero"));
	            	enderecoTO.setComplemento(resultSet.getString("complemento"));
	            	enderecoTO.setCep(resultSet.getString("cep"));
	            	enderecoTO.setBairro(resultSet.getString("bairro"));
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
	      
	         return enderecoTO;
	      
	   }

	   public EnderecoTO pesquisarEmpregado(String codg)
	   {
		   	 EnderecoTO enderecoTO = null;
	         try
	         {
	            String sql = " SELECT * FROM ENDERECO WHERE FK_EMPREGADO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codg);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	enderecoTO = new EnderecoTO();
	            	enderecoTO.setEndereco(resultSet.getString("LOGRADOURO"));
	            	enderecoTO.setCidade(resultSet.getString("cidade"));
	            	enderecoTO.setEstado(resultSet.getString("estado"));
	            	enderecoTO.setNumero(resultSet.getInt("numero"));
	            	enderecoTO.setComplemento(resultSet.getString("complemento"));
	            	enderecoTO.setCep(resultSet.getString("cep"));
	            	enderecoTO.setBairro(resultSet.getString("bairro"));
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
	      
	         return enderecoTO;
	      
	   }
}

