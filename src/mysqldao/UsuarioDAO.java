package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import banco.MysqlConnect;
import model.UsuarioTO;

public class UsuarioDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public UsuarioDAO()
	   {
	      super();
	   }
	   
	   public boolean cadastrarUsuario(UsuarioTO usuarioTO)
	   {
	      try{
	         String sql = "insert into USUARIO(cnpj, cpf, e_mail, nome, rg, senha, sobrenome,"
	         		+ "UF_RG, ativo) values (?,?,?,?,?,?,?,?,?)";     
	         st = conn.prepareStatement(sql);
	         st.setString(1,usuarioTO.getCnpj());
	         st.setString(2,usuarioTO.getCpf());
	         st.setString(3,usuarioTO.getEmail());
	         st.setString(4,usuarioTO.getNome());
	         st.setString(5,usuarioTO.getRg());
	         st.setString(6,usuarioTO.getSenha());
	         st.setString(7,usuarioTO.getSobrenome());
	         st.setString(8,usuarioTO.getUfRg());
	         st.setBoolean(9,usuarioTO.getAtivo());
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }
	   
	   public boolean alterarUsuario(UsuarioTO usuarioTO)
	   { 
		      try{
		         String sql = "UPDATE USUARIO SET e_mail = ?, nome = ?, "+
				         "rg = ?, senha = ?, sobrenome = ? , UF_RG = ? , ativo = ?  where CD_USUARIO = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1,usuarioTO.getEmail());
				         st.setString(2,usuarioTO.getNome());
				         st.setString(3,usuarioTO.getRg());
				         st.setString(4,usuarioTO.getSenha());
				         st.setString(5,usuarioTO.getSobrenome());
				         st.setString(6,usuarioTO.getUfRg());
				         st.setBoolean(7,usuarioTO.getAtivo());
				         st.setString(8,usuarioTO.getCodigo());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   public boolean ativarUsuario(String codigo)
	   { 
		      try{
		         String sql = "UPDATE USUARIO SET ativo = ?  where CD_USUARIO = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setBoolean(1,false);
				         st.setString(2,codigo);
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   	
	   public UsuarioTO pesquisarUsuario(String codg)
	   {
		   	 UsuarioTO usuarioTO = null;
	         try
	         {
	            String sql = " SELECT * FROM USUARIO WHERE CD_USUARIO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codg);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	usuarioTO = new UsuarioTO();
	            	usuarioTO.setCodigo(resultSet.getString("CD_USUARIO"));
	            	usuarioTO.setNome(resultSet.getString("nome"));
	            	usuarioTO.setSobrenome(resultSet.getString("sobrenome"));
	            	usuarioTO.setCnpj(resultSet.getString("cnpj"));
	            	usuarioTO.setCpf(resultSet.getString("cpf"));
	            	usuarioTO.setRg(resultSet.getString("rg"));	
	            	usuarioTO.setUfRg(resultSet.getString("UF_RG"));
	            	usuarioTO.setEmail(resultSet.getString("e_mail"));
	            	usuarioTO.setSenha(resultSet.getString("senha"));
	            	usuarioTO.setAtivo(resultSet.getBoolean("ativo"));
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
	      
	         return usuarioTO;
	      
	      }
	   	
	      public boolean excluirUsuario(String cod)
	      {
	         try
	         {
	            String sql = " UPDATE USUARIO SET ativo = ?  where CD_USUARIO = ?";
	            st = conn.prepareStatement(sql);
	            st.setString(1,cod);
	            st.setBoolean(2, true);
	            st.executeUpdate();
	            st.close(); 
	         }
	         catch(Exception e)
	         {
	        	 e.printStackTrace();
	        	 return true;
	         }        
	         return false;  
	      }
	      
	      public UsuarioTO pesquisarLogin(String email, String senha){
	    	  
	    	  UsuarioTO usuarioTO = null;
		         try
		         {
		            String sql = " SELECT * FROM USUARIO WHERE E_MAIL = ? AND SENHA = ?";
		            st= conn.prepareStatement(sql);
		            st.setString(1,email);
		            st.setString(2, senha);
		            ResultSet resultSet = st.executeQuery();
		            if(resultSet.next())
		            {
		            	usuarioTO = new UsuarioTO();
		            	usuarioTO.setCodigo(resultSet.getString("CD_USUARIO"));
		            	usuarioTO.setNome(resultSet.getString("nome"));
		            	usuarioTO.setSobrenome(resultSet.getString("sobrenome"));
		            	usuarioTO.setCnpj(resultSet.getString("cnpj"));
		            	usuarioTO.setCpf(resultSet.getString("cpf"));
		            	usuarioTO.setRg(resultSet.getString("rg"));	
		            	usuarioTO.setUfRg(resultSet.getString("UF_RG"));
		            	usuarioTO.setEmail(resultSet.getString("e_mail"));
		            	usuarioTO.setSenha(resultSet.getString("senha"));
		            	usuarioTO.setAtivo(resultSet.getBoolean("ativo"));
		            }	
		            st.close();
		         }
		            catch(Exception e)
		            {
		               e.printStackTrace();
		               return null;
		            }
		      
		         return usuarioTO;
		      
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
}
