package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import banco.MysqlConnect;
import model.ContratoTO;

public class ContratoDAO extends MysqlConnect {
	
	private PreparedStatement st;
	   
	   public ContratoDAO()
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
	   public boolean cadastrar(ContratoTO contratoTO)
	   {
	      try{
	    	
	    	String codigoUsuario = ultimoCodigoUsuario();
	    	String codigoEmpregado = ultimoCodigoEmpregado();
	    	
	    	  String sql = "INSERT INTO CONTRATO(DATA_ADMISSAO, CARGO,SALARIO_BASE,DIA_PAGAMENTO,"
	    		         + "VALE_TRANSPORTE_VALOR,COMPENSACAO_DIAS,REGIME_TRABALHO,"
	    		         + "CONTA,AGENCIA,BANCO, TIPO_CONTA,"
	    		         + "FK_EMPREGADO,FK_USUARIO)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	    		         
	         st = conn.prepareStatement(sql);
	         st.setDate(1,contratoTO.getDataAdmissao());
	         st.setString(2,contratoTO.getCargo());
	         st.setDouble(3,contratoTO.getSalarioBase());
	         st.setString(4,contratoTO.getDiaPagamento());
	         st.setDouble(5,contratoTO.getValeTransporte());
	         st.setString(6,contratoTO.getCompensacaoDias());
	         st.setString(7,contratoTO.getRegimeDeTrabalho());
	         st.setString(8,contratoTO.getConta());
	         st.setString(9,contratoTO.getAgencia());
	         st.setString(10,contratoTO.getBanco());
	         st.setString(11,contratoTO.getTipoConta());
	         st.setString(12,codigoEmpregado);
	         st.setString(13,codigoUsuario); 
	         
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }
	   
	   public boolean alterar(ContratoTO contratoTO)
	   {
	      try{
	    	  
	    	  String sql ="UPDATE CONTRATO SET" 
				+" DATA_ADMISSAO = ?, CARGO=?,SALARIO_BASE = ? ,DIA_PAGAMENTO=?,"
	    		+ "VALE_TRANSPORTE_VALOR = ?,COMPENSACAO_DIAS=?,REGIME_TRABALHO=?,"
	    		+ "CONTA = ?,AGENCIA = ?,BANCO=?, TIPO_CONTA =?,"
	    		+ "WHERE FK_EMPREGADO = ?";
				
	    	 st = conn.prepareStatement(sql);
	         st.setDate(1,contratoTO.getDataAdmissao());
	         st.setString(2,contratoTO.getCargo());
	         st.setDouble(3,contratoTO.getSalarioBase());
	         st.setString(4,contratoTO.getDiaPagamento());
	         st.setDouble(5,contratoTO.getValeTransporte());
	         st.setString(6,contratoTO.getCompensacaoDias());
	         st.setString(7,contratoTO.getRegimeDeTrabalho());
	         st.setString(8,contratoTO.getConta());
	         st.setString(9,contratoTO.getAgencia());
	         st.setString(10,contratoTO.getBanco());
	         st.setString(11,contratoTO.getTipoConta());
	         st.setString(12,contratoTO.getCodigoEmpregado());
	         
	         st.executeUpdate(); 
				st.close(); 
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}   
			return true;
	   }

		public ContratoTO pesquisarEmpregado(String codg) {
			
			ContratoTO contratoTO = null;
	        try
	        {
	           String sql = " SELECT * FROM CONTRATO WHERE FK_EMPREGADO = ?";
	           st= conn.prepareStatement(sql);
	           st.setString(1,codg);
	           ResultSet resultSet = st.executeQuery();
	           if(resultSet.next())
	           {
	        	   
	           	contratoTO = new ContratoTO();
	           	contratoTO.setCodigo(resultSet.getString("CD_CONTRATO"));
	           	contratoTO.setDataAdmissao(resultSet.getDate("DATA_ADMISSAO"));
	           	contratoTO.setCargo(resultSet.getString("CARGO"));
	           	contratoTO.setSalarioBase(resultSet.getDouble("SALARIO_BASE"));
	           	contratoTO.setDiaPagamento(resultSet.getString("DIA_PAGAMENTO"));
	           	contratoTO.setValeTransporte(resultSet.getDouble("VALE_TRANSPORTE_VALOR"));
	           	contratoTO.setCompensacaoDias(resultSet.getString("COMPENSACAO_DIAS"));
	           	contratoTO.setRegimeDeTrabalho(resultSet.getString("REGIME_TRABALHO"));
	           	contratoTO.setConta(resultSet.getString("CONTA"));
	           	contratoTO.setAgencia(resultSet.getString("AGENCIA"));
	        	contratoTO.setBanco(resultSet.getString("BANCO"));
	        	contratoTO.setTipoConta(resultSet.getString("TIPO_CONTA"));
	        	contratoTO.setCodigoEmpregado(resultSet.getString("FK_EMPREGADO"));
	        	contratoTO.setCodigoUsuario(resultSet.getString("FK_USUARIO"));
		    
	           }	
	           st.close();
	        }
	           catch(Exception e)
	           {
	              e.printStackTrace();
	              return null;
	           }
	     
	        return contratoTO;
		}
}
