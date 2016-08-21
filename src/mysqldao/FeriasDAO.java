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
	         String sql = "insert into FERIAS(PERIODO_AQUISITIVO_INICIO,SITUACAO, DATA_INICIO, DATA_FIM, "
	         		+ "QTD_DIAS_FERIAS, VALOR, VENDA_FERIAS, FK_CONTRATO, PERIODO_AQUISITIVO_FIM) "
	         		+ "values (?,?,?,?,?,?,?,?,?)";     
	         st = conn.prepareStatement(sql);
	         st.setString(1, feriasTO.getPeriodoAquisitivoInicio());
	         st.setString(2,feriasTO.getSituacao());
	         st.setString(3,feriasTO.getDataInicio());
	         st.setString(4,feriasTO.getDataFinal());
	         st.setInt(5,feriasTO.getQtdDiasFerias());
	         st.setDouble(6,feriasTO.getValor());
	         st.setBoolean(7,feriasTO.getVendaFerias());
	         st.setString(8,feriasTO.getCodigoContrato());
	         st.setString(9,feriasTO.getPeriodoAquisitivoFim());
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
		         String sql = "UPDATE FERIAS SET PERIODO_AQUISITIVO_INICIO=?, SITUACAO = ?, DATA_INICIO = ?, "
		         		+ "DATA_FIM = ?, QTD_DIAS_FERIAS = ?, VALOR = ?, VENDA_FERIAS =? PERIODO_AQUISITIVO_INICIO=? WHERE CD_FERIAS = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1,feriasTO.getPeriodoAquisitivoInicio());
				         st.setString(2,feriasTO.getSituacao());
				         st.setString(3,feriasTO.getDataInicio());
				         st.setString(4, feriasTO.getDataFinal());
				         st.setInt(5,feriasTO.getQtdDiasFerias());
				         st.setDouble(6,feriasTO.getValor());
				         st.setBoolean(7,feriasTO.getVendaFerias());
				         st.setString(8, feriasTO.getPeriodoAquisitivoFim());
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
		         String sql = "UPDATE FERIAS SET DATA_PAGAMENTO WHERE CD_FERIAS = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1,feriasTO.getDiaPagamento());
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
	            	
	            	DataUtil dtUtil = new DataUtil();
	            	
	            	feriasTO.setPeriodoAquisitivoInicio(dtUtil.retornaDataFormatadaCompletaMySQL(resultSet.getDate("PERIODO_AQUISITIVO_INICIO")));
	            	feriasTO.setPeriodoAquisitivoFim(dtUtil.retornaDataFormatadaCompletaMySQL(resultSet.getDate("PERIODO_AQUISITIVO_FIM")));
	            	feriasTO.setSituacao(resultSet.getString("SITUACAO"));
	            	feriasTO.setDataInicio(dtUtil.retornaDataFormatadaCompletaMySQL(resultSet.getDate("DATA_INICIO")));
	            	feriasTO.setDataFinal(dtUtil.retornaDataFormatadaCompletaMySQL(resultSet.getDate("DATA_FIM")));
	            	feriasTO.setQtdDiasFerias(resultSet.getInt("QTD_DIAS_FERIAS"));
	            	feriasTO.setValor(resultSet.getDouble("VALOR"));	
	            	feriasTO.setVendaFerias(resultSet.getBoolean("VENDA_FERIAS"));
	            	feriasTO.setDiaPagamento(resultSet.getString("DATA_PAGAMENTO"));
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
