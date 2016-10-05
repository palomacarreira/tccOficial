package mysqldao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DataUtil;
import model.FeriasTO;
import banco.MysqlConnect;

public class FeriasDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public FeriasDAO()
	   {
	      super();
	   }
	   
	   public boolean cadastrarFerias(FeriasTO feriasTO)
	   {
	      try{
	         String sql = "insert into FERIAS(PERIODO_AQUISITIVO_INICIO, PERIODO_AQUISITIVO_FIM, SITUACAO, DATA_INICIO, DATA_FIM, "
	         		+ "QTD_DIAS_FERIAS, VALOR, VENDA_FERIAS, FK_CONTRATO) "
	         		+ "values (?,?,?,?,?,?,?,?,?)";     
	         st = conn.prepareStatement(sql);
	         st.setDate(1, feriasTO.getPeriodoAquisitivoInicio());
	         st.setDate(2, feriasTO.getPeriodoAquisitivoFim());
	         st.setString(3,feriasTO.getSituacao());
	         st.setDate(4,feriasTO.getDataInicio());
	         st.setDate(5,feriasTO.getDataFinal());
	         st.setInt(6,feriasTO.getQtdDiasFerias());
	         st.setDouble(7,feriasTO.getValor());
	         st.setBoolean(8,feriasTO.getVendaFerias());
	         st.setString(9,feriasTO.getCodigoContrato());
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }
	   
	   public boolean alterarFerias(FeriasTO feriasTO)
	   { 
		      try{
		         String sql = "UPDATE FERIAS SET PERIODO_AQUISITIVO_INICIO =?, PERIODO_AQUISITIVO_FIM =?, SITUACAO = ?, DATA_INICIO = ?, "
		         		+ "DATA_FIM = ?, QTD_DIAS_FERIAS = ?, VALOR = ?, VENDA_FERIAS =? WHERE CD_FERIAS = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setDate(1,feriasTO.getPeriodoAquisitivoInicio());
				         st.setDate(2,feriasTO.getPeriodoAquisitivoFim());
				         st.setString(3,feriasTO.getSituacao());
				         st.setDate(4,feriasTO.getDataInicio());
				         st.setDate(5, feriasTO.getDataFinal());
				         st.setInt(6,feriasTO.getQtdDiasFerias());
				         st.setDouble(7,feriasTO.getValor());
				         st.setBoolean(8,feriasTO.getVendaFerias());
				         st.setString(9,feriasTO.getCodigo());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   public boolean alterarPagamento(FeriasTO feriasTO)
	   { 
		      try{
		         String sql = "UPDATE FERIAS SET DATA_PAGAMENTO =?, SITUACAO = ? WHERE CD_FERIAS = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setDate(1,feriasTO.getDiaPagamento());
				         st.setString(2,feriasTO.getSituacao());
				         st.setString(3,feriasTO.getCodigo());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   	
	   public ArrayList<FeriasTO> pesquisarTodos(String codigo)
	   {
		   ArrayList<FeriasTO> lista = new ArrayList<FeriasTO>();
		   FeriasTO feriasTO = null;
	         try
	         {
	            String sql = " SELECT * FROM FERIAS WHERE FK_CONTRATO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codigo);
	            ResultSet resultSet = st.executeQuery();
				while(resultSet.next())
				{
	            	feriasTO = new FeriasTO();
	            	feriasTO.setPeriodoAquisitivoInicio(resultSet.getDate("PERIODO_AQUISITIVO_INICIO"));
	            	feriasTO.setPeriodoAquisitivoFim(resultSet.getDate("PERIODO_AQUISITIVO_FIM"));
	            	feriasTO.setSituacao(resultSet.getString("SITUACAO"));
	            	feriasTO.setDataInicio(resultSet.getDate("DATA_INICIO"));
	            	feriasTO.setDataFinal(resultSet.getDate("DATA_FIM"));
	            	feriasTO.setQtdDiasFerias(resultSet.getInt("QTD_DIAS_FERIAS"));
	            	feriasTO.setValor(resultSet.getDouble("VALOR"));	
	            	feriasTO.setVendaFerias(resultSet.getBoolean("VENDA_FERIAS"));
	            	feriasTO.setDiaPagamento(resultSet.getDate("DATA_PAGAMENTO"));
	            	feriasTO.setCodigo(resultSet.getString("CD_FERIAS"));
	            	
	            	lista.add(feriasTO);
				}	
				st.close();
	         }
	         catch(Exception e)
	 		{
	 			e.printStackTrace();
	 			return null;
	 		}
	 		return lista;
	      
	      }
	   	
	      public boolean excluirFerias(String cod)
	      {
	         try
	         {
	            String sql = " DELETE FROM FERIAS WHERE CD_FERIAS = ?";
	            st = conn.prepareStatement(sql);
	            st.setString(1, cod);
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
	      
	      public FeriasTO pesquisarFerias(String codigo)
		  {
			   FeriasTO feriasTO = null;
		         try
		         {
		            String sql = " SELECT * FROM FERIAS WHERE CD_FERIAS = ?";
		            st= conn.prepareStatement(sql);
		            st.setString(1,codigo);
		            ResultSet resultSet = st.executeQuery();
					if(resultSet.next())
					{
		            	feriasTO = new FeriasTO();
		            	feriasTO.setPeriodoAquisitivoInicio(resultSet.getDate("PERIODO_AQUISITIVO_INICIO"));
		            	feriasTO.setPeriodoAquisitivoFim(resultSet.getDate("PERIODO_AQUISITIVO_FIM"));
		            	feriasTO.setSituacao(resultSet.getString("SITUACAO"));
		            	feriasTO.setDataInicio(resultSet.getDate("DATA_INICIO"));
		            	feriasTO.setDataFinal(resultSet.getDate("DATA_FIM"));
		            	feriasTO.setQtdDiasFerias(resultSet.getInt("QTD_DIAS_FERIAS"));
		            	feriasTO.setValor(resultSet.getDouble("VALOR"));	
		            	feriasTO.setVendaFerias(resultSet.getBoolean("VENDA_FERIAS"));
		            	feriasTO.setDiaPagamento(resultSet.getDate("DATA_PAGAMENTO"));
					}	
					st.close();
		         }
		         catch(Exception e)
		 		{
		 			e.printStackTrace();
		 			return null;
		 		}
		 		return feriasTO;
		      
		    }
	      
	 
}
