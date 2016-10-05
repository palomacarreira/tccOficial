package mysqldao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import banco.MysqlConnect;
import model.EnderecoTO;
import model.FeriasTO;
import model.PontoTO;

public class PontoDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public PontoDAO()
	   {
	      super();
	   }
	   
	   public String ultimoCodigoPonto()
	   {
		   	String codigo = null;
		   	
	         try
	         {
	            String sql = "SELECT MAX(CD_PONTO) FROM PONTO";
	            st= (PreparedStatement) conn.prepareStatement(sql);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	codigo = resultSet.getString("MAX(CD_PONTO)");
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
	   
	   public boolean cadastrarPonto(PontoTO pontoTO)
	   {
	      try{
	         String sql = "INSERT INTO PONTO(DATA, HORA_ENTRADA,HORA_INICIO_INTERVALO, HORA_FIM_INTERVALO, "
	         		+ "HORA_SAIDA, ACAO, FK_EMPREGADO) "
	         		+ "values (?,?,?,?,?,?,?)";     

	         st = conn.prepareStatement(sql);
	         st.setDate(1,pontoTO.getDataPonto());
	         st.setString(2,pontoTO.getHoraEntrada());
	         st.setString(3,pontoTO.getHoraSaidaAlmoco());
	         st.setString(4,pontoTO.getHoraVoltaAlmoco());
	         st.setString(5,pontoTO.getHoraSaida());
	         st.setString(6,pontoTO.getAcao());
	         st.setString(7,pontoTO.getCodigoEmpregado());
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }
	
	   
	   public boolean alterarPonto(PontoTO pontoTO)
	   { 
		      try{
		         String sql = "UPDATE PONTO SET HORA_ENTRADA=?, HORA_INICIO_INTERVALO=?, HORA_FIM_INTERVALO=?, "
			         		+ "HORA_SAIDA=?, ACAO=? WHERE CD_PONTO=?";
			         
				         st = conn.prepareStatement(sql);
				         st.setString(1,pontoTO.getHoraEntrada());
				         st.setString(2,pontoTO.getHoraSaidaAlmoco());
				         st.setString(3,pontoTO.getHoraVoltaAlmoco());
				         st.setString(4,pontoTO.getHoraSaida());
				         st.setString(5,pontoTO.getAcao());
				         st.setString(6,pontoTO.getCodigo());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
	   }
	   
	   public ArrayList<PontoTO> pesquisarPonto(String codigo)
	   {
		   ArrayList<PontoTO> lista= new ArrayList<PontoTO>();
		   PontoTO pontoTO = null;
	         try
	         {
	            String sql = " SELECT * FROM PONTO WHERE FK_EMPREGADO = ?";
	            st= conn.prepareStatement(sql);
	            st.setString(1,codigo);
	            ResultSet resultSet = st.executeQuery();
				while(resultSet.next())
				{
					 pontoTO = new PontoTO();
				     pontoTO.setDataPonto(resultSet.getDate("DATA"));
			         pontoTO.setHoraEntrada(resultSet.getString("HORA_ENTRADA"));
			         pontoTO.setHoraSaidaAlmoco(resultSet.getString("HORA_INICIO_INTERVALO"));
			         pontoTO.setHoraVoltaAlmoco(resultSet.getString("HORA_FIM_INTERVALO"));
			         pontoTO.setHoraSaida(resultSet.getString("HORA_SAIDA"));
			         pontoTO.setAcao(resultSet.getString("ACAO"));
			         pontoTO.setCodigoEmpregado(resultSet.getString("FK_EMPREGADO"));
			         pontoTO.setCodigo(resultSet.getString("CD_PONTO"));
	            	 lista.add(pontoTO);
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
	   
	   public PontoTO pesquisaPorData(Date data){
		   
		   PontoTO pontoTO = null;
	         try
	         {
	            String sql = " SELECT * FROM PONTO WHERE DATA = ?";
	            st= conn.prepareStatement(sql);
	            st.setDate(1, data);
	            ResultSet resultSet = st.executeQuery();
	            if(resultSet.next())
	            {
	            	pontoTO = new PontoTO();
			        pontoTO.setHoraEntrada(resultSet.getString("HORA_ENTRADA"));
			        pontoTO.setHoraSaidaAlmoco(resultSet.getString("HORA_INICIO_INTERVALO"));
			        pontoTO.setHoraVoltaAlmoco(resultSet.getString("HORA_FIM_INTERVALO"));
			        pontoTO.setHoraSaida(resultSet.getString("HORA_SAIDA"));
			        pontoTO.setAcao(resultSet.getString("ACAO"));
			        pontoTO.setCodigoEmpregado(resultSet.getString("FK_EMPREGADO"));
			        pontoTO.setCodigo(resultSet.getString("CD_PONTO"));
	            }	
	            st.close();
	         }
	            catch(Exception e)
	            {
	               e.printStackTrace();
	               return null;
	            }
	      
	         return pontoTO;
	   }
}
