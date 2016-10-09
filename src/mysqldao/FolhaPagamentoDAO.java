package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import banco.MysqlConnect;
import model.FolhaPagamentoTO;

public class FolhaPagamentoDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public FolhaPagamentoDAO()
	   {
	      super();
	   }
   
	   public String ultimoCodigoContrato()
	   {
		   	String codigo = null;
		   	
	         try
	         {
	            String sql = "SELECT MAX(CD_CONTRATO) FROM CONTRATO";
	            st= conn.prepareStatement(sql);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	codigo = resultSet.getString("MAX(CD_CONTRATO)");
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
	   
	   public boolean cadastrarFolhaPagamento(FolhaPagamentoTO folhaPagamentoTO)
	   {
	      try{
	    	
	    	String codigoContrato = ultimoCodigoContrato();
	    	 
	         String sql = "INSERT INTO FOLHA_PAGAMENTO(DECIMO_TERCEIRO_1PARCELA, DECIMO_TERCEIRO_2PARCELA,"
	         		+ "SALARIO_LIQUIDO,FGTS,INSS,IR_RETIDO,VALE_TRANSPORTE,BENEFICIOS,"
	         		+ "MES_REFERENCIA, ANO_REFERENCIA, VALOR_HORA_EXTRA, FK_CONTRATO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";     
	         
	         st = conn.prepareStatement(sql);
	         st.setDouble(1,folhaPagamentoTO.getDecTercPrimeiro());
	         st.setDouble(2,folhaPagamentoTO.getDecTercSegunda());
	         st.setDouble(3,folhaPagamentoTO.getSalarioLiquido());
	         st.setDouble(4,folhaPagamentoTO.getFgts());
	         st.setDouble(5,folhaPagamentoTO.getInss());
	         st.setDouble(6,folhaPagamentoTO.getIrRetido());
	         st.setDouble(7,folhaPagamentoTO.getValeTransporte());
	         st.setDouble(8,folhaPagamentoTO.getBeneficios());
	         st.setString(9,folhaPagamentoTO.getMesReferencia());
	         st.setString(10,folhaPagamentoTO.getAnoReferencia());
	         st.setDouble(11,folhaPagamentoTO.getHoraExtra());
	         st.setString(12,codigoContrato);
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

	   public boolean alterarFolhaPagamento(FolhaPagamentoTO folhaPagamentoTO)
	   { 
		      try{
		         String sql = "UPDATE FOLHA_PAGAMENTO SET DECIMO_TERCEIRO_1PARCELA = ?, "
		     + "DECIMO_TERCEIRO_2PARCELA = ?, SALARIO_LIQUIDO = ?,FGTS = ?,"
		     + "INSS = ?,IR_RETIDO = ?,VALE_TRANSPORTE = ?,BENEFICIOS = ?,"
	         + "MES_REFERENCIA = ?, ANO_REFERENCIA = ?, VALOR_HORA_EXTRA = ? WHERE FK_CONTRATO = ?";
					         
				st = conn.prepareStatement(sql); 
				st.setDouble(1,folhaPagamentoTO.getDecTercPrimeiro());
				st.setDouble(2,folhaPagamentoTO.getDecTercSegunda());
				st.setDouble(3,folhaPagamentoTO.getSalarioLiquido());
				st.setDouble(4,folhaPagamentoTO.getFgts());
				st.setDouble(5,folhaPagamentoTO.getInss());
				st.setDouble(6,folhaPagamentoTO.getIrRetido());
				st.setDouble(7,folhaPagamentoTO.getValeTransporte());
				st.setDouble(8,folhaPagamentoTO.getBeneficios());
				st.setString(9,folhaPagamentoTO.getMesReferencia());
				st.setString(10,folhaPagamentoTO.getAnoReferencia());
				st.setDouble(11,folhaPagamentoTO.getHoraExtra());
				st.setString(12,folhaPagamentoTO.getCodigoContrato());
				st.executeUpdate(); 
				st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
		   }

	   
	   public FolhaPagamentoTO pesquisarFolhaPagamento(String codg)
	   {
		   	 FolhaPagamentoTO folhaPagamentoTO = null;
	         try
	         {
	            String sql = " SELECT * FROM FOLHA_PAGAMENTO WHERE FK_CONTRATO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codg);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	folhaPagamentoTO = new FolhaPagamentoTO();
	            	//folhaPagamentoTO.setDecTercPrimeiro(resultSet.getDouble("DECIMO_TERCEIRO_1PARCELA"));
					//folhaPagamentoTO.setDecTercSegunda(resultSet.getDouble("DECIMO_TERCEIRO_2PARCELA"));
					folhaPagamentoTO.setSalarioLiquido(resultSet.getDouble("SALARIO_LIQUIDO"));
					folhaPagamentoTO.setFgts(resultSet.getDouble("FGTS"));
					folhaPagamentoTO.setInss(resultSet.getDouble("INSS"));
					folhaPagamentoTO.setIrRetido(resultSet.getDouble("IR_RETIDO"));
					folhaPagamentoTO.setValeTransporte(resultSet.getDouble("VALE_TRANSPORTE"));
					folhaPagamentoTO.setBeneficios(resultSet.getDouble("BENEFICIOS"));
					folhaPagamentoTO.setMesReferencia(resultSet.getString("MES_REFERENCIA"));
					folhaPagamentoTO.setAnoReferencia(resultSet.getString("ANO_REFERENCIA"));
					//folhaPagamentoTO.setHoraExtra(resultSet.getDouble("VALOR_HORA_EXTRA"));
					folhaPagamentoTO.setCodigo(resultSet.getString("CD_FOLHA_PAGAMENTO"));
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
	      
	         return folhaPagamentoTO;
	      
	   }
}
