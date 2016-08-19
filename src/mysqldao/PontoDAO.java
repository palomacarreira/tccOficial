package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import banco.MysqlConnect;
import model.FeriasTO;
import model.PontoTO;

public class PontoDAO extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public PontoDAO()
	   {
	      super();
	   }
	   
	   public boolean cadastrarPonto(PontoTO pontoTO)
	   {
	      try{
	         String sql = "INSERT INTO PONTO(DATA, HORA_ENTRADA,HORA_INICIO_INTERVALO, HORA_FIM_INTERVALO, "
	         		+ "HORA_SAIDA, FALTA, FALTA_JUSTIFICADA, FK_EMPREGADO) "
	         		+ "values (?,?,?,?,?,?,?,?)";     

	         st = conn.prepareStatement(sql);
	         st.setDate(1,pontoTO.getData());
	         st.setString(2,pontoTO.getHoraEntrada());
	         st.setString(3,pontoTO.getHoraSaidaAlmoco());
	         st.setString(4,pontoTO.getHoraVoltaAlmoco());
	         st.setString(5,pontoTO.getHoraSaida());
	         st.setBoolean(6,pontoTO.getFalta());
	         st.setBoolean(7,pontoTO.getFaltaJustificada());
	         st.setString(8,pontoTO.getCodigoEmpregado());
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
		         String sql = "UPDATE PONTO SET DATA=?, HORA_ENTRADA=?,HORA_INICIO_INTERVALO=?, HORA_FIM_INTERVALO=?, "
			         		+ "HORA_SAIDA=?, FALTA=?, FALTA_JUSTIFICADA=? WHERE FK_EMPREGADO=?";
			         
				         st = conn.prepareStatement(sql);
				         st.setDate(1,pontoTO.getData());
				         st.setString(2,pontoTO.getHoraEntrada());
				         st.setString(3,pontoTO.getHoraSaidaAlmoco());
				         st.setString(4,pontoTO.getHoraVoltaAlmoco());
				         st.setString(5,pontoTO.getHoraSaida());
				         st.setBoolean(6,pontoTO.getFalta());
				         st.setBoolean(7,pontoTO.getFaltaJustificada());
				         st.setString(8,pontoTO.getCodigoEmpregado());
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
				     pontoTO.setData(resultSet.getDate("DATA"));
			         pontoTO.setHoraEntrada(resultSet.getString("HORA_ENTRADA"));
			         pontoTO.setHoraSaidaAlmoco(resultSet.getString("HORA_INICIO_INTERVALO"));
			         pontoTO.setHoraVoltaAlmoco(resultSet.getString("HORA_FIM_INTERVALO"));
			         pontoTO.setHoraSaida(resultSet.getString("HORA_SAIDA"));
			         pontoTO.setFalta(resultSet.getBoolean("FALTA"));
			         pontoTO.setFaltaJustificada(resultSet.getBoolean("FALTA_JUSTIFICADA"));
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
}
