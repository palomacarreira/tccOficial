package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import banco.MysqlConnect;
import model.FeriasTO;

public class FeriasDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public FeriasDAO()
	   {
	      super();
	   }
	   
	   public boolean cadastrarFerias(FeriasTO feriasTO)
	   {
	      try{
	         String sql = "insert into FERIAS(PERIODO_AQUISITIVO,SITUACAO, DATA_INICIO, DATA_FIM, "
	         		+ "QTD_DIAS_FERIAS, VALOR, VENDA_FERIAS, FK_CONTRATO) "
	         		+ "values (?,?,?,?,?,?,?,?)";     
	         st = conn.prepareStatement(sql);
	         st.setString(1,feriasTO.getPeriodoAquisitivo());
	         st.setString(2,feriasTO.getSituacao());
	         st.setDate(3,feriasTO.getDataInicio());
	         st.setDate(4,feriasTO.getDataFinal());
	         st.setInt(5,feriasTO.getQtdDiasFerias());
	         st.setDouble(6,feriasTO.getValor());
	         st.setBoolean(7,feriasTO.getVendaFerias());
	         st.setString(8,feriasTO.getCodigoContrato());
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
		         String sql = "UPDATE FERIAS SET PERIODO_AQUISITIVO=?, SITUACAO = ?, DATA_INICIO = ?, "
		         		+ "DATA_FIM = ?, QTD_DIAS_FERIAS = ?, VALOR = ?, VENDA_FERIAS =? WHERE CD_FERIAS = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1,feriasTO.getPeriodoAquisitivo());
				         st.setString(2,feriasTO.getSituacao());
				         st.setDate(3,feriasTO.getDataInicio());
				         st.setDate(4,feriasTO.getDataFinal());
				         st.setInt(5,feriasTO.getQtdDiasFerias());
				         st.setDouble(6,feriasTO.getValor());
				         st.setBoolean(7,feriasTO.getVendaFerias());
				         st.setString(8,feriasTO.getCodigo());
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
		         String sql = "UPDATE FERIAS SET DATA_PAGAMENTO WHERE CD_FERIAS = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setDate(1,feriasTO.getDiaPagamento());
				         st.setString(2,feriasTO.getCodigo());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   	
	   public ArrayList<FeriasTO> pesquisarFerias(String codigo)
	   {
		   ArrayList<FeriasTO> lista= new ArrayList<FeriasTO>();
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
	            	feriasTO.setPeriodoAquisitivo(resultSet.getString("PERIODO_AQUISITIVO"));
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
	      
	 
}
