package mysqldao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import banco.MysqlConnect;
import model.JornadaTrabalhoTO;

public class JornadaTrabalhoDAO extends MysqlConnect{

	private PreparedStatement st;
	   
	   public JornadaTrabalhoDAO()
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
	   
	   public boolean cadastrar(JornadaTrabalhoTO jornadaTrabalhoTO)
	   {
	      try{
	    	
	    	String codigoContrato = ultimoCodigoContrato();
	    	
	    	  String sql = "INSERT INTO JORNADA_TRABALHO("
	    		         + "HORA_ENTRADA, HORA_SAIDA, "
	    		         + "HORA_INICIO_INTERVALO, HORA_FIM_INTERVALO,"
	    		         + "DIA_SEMANA,DIA_FOLGA, DIA_MEIO_PERIODO, "
	    		         + "FK_CONTRATO)VALUES (?,?,?,?,?,?,?,?)"; 
	    		         
	         st = conn.prepareStatement(sql);
	    	
	         st.setString(1,jornadaTrabalhoTO.getHoraEntrada());
	         st.setString(2,jornadaTrabalhoTO.getHoraSaida());
	         st.setString(3,jornadaTrabalhoTO.getHoraSaidaAlmoco());
	         st.setString(4,jornadaTrabalhoTO.getHoraVoltaAlmoco());
	         st.setInt(5,jornadaTrabalhoTO.getDiaSemana());
	         st.setBoolean(6,jornadaTrabalhoTO.getDiaFolga());
	         st.setBoolean(7,jornadaTrabalhoTO.getDiaMeioPeriodo());
	         st.setString(8,codigoContrato);
	         st.executeUpdate(); 
	         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	      }
	   
	   
	   public boolean alterar(JornadaTrabalhoTO jornadaTrabalhoTO)
	   {

	      try{
	    	
	    		String sql =  "UPDATE JORNADA_TRABALHO SET " 
				+ "HORA_ENTRADA=?, HORA_SAIDA=?, HORA_INICIO_INTERVALO=?, HORA_FIM_INTERVALO=?, "
				+ "DIA_SEMANA=?, DIA_FOLGA=?, DIA_MEIO_PERIODO=? "		
				+ "WHERE CD_JORNADA_TRABALHO = ?";
	    	  
	    	  	 st = conn.prepareStatement(sql);
	    	  	 st.setString(1,jornadaTrabalhoTO.getHoraEntrada());
		         st.setString(2,jornadaTrabalhoTO.getHoraSaida());
		         st.setString(3,jornadaTrabalhoTO.getHoraSaidaAlmoco());
		         st.setString(4,jornadaTrabalhoTO.getHoraVoltaAlmoco());
		         st.setInt(5,jornadaTrabalhoTO.getDiaSemana());
		         st.setBoolean(6,jornadaTrabalhoTO.getDiaFolga());
		         st.setBoolean(7,jornadaTrabalhoTO.getDiaMeioPeriodo());
		         st.setString(8,jornadaTrabalhoTO.getCodigo());
		         st.executeUpdate(); 
					st.close(); 
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}   
				return true;
	      }
	   
	   
	   public ArrayList<JornadaTrabalhoTO> pesquisarJornada(String codg) {
			
		   ArrayList<JornadaTrabalhoTO> lista = new ArrayList<JornadaTrabalhoTO>();
		   JornadaTrabalhoTO jornadaTrabalhoTO = null;
		   
	        try
	        {
	           String sql = " SELECT * FROM JORNADA_TRABALHO WHERE FK_CONTRATO = ?";
	           st= conn.prepareStatement(sql);
	           st.setString(1,codg);
	           ResultSet resultSet = st.executeQuery();
	           
	           while(resultSet.next())
	           {
	        	   jornadaTrabalhoTO = new JornadaTrabalhoTO();
	        	   jornadaTrabalhoTO.setCodigo(resultSet.getString("CD_JORNADA_TRABALHO"));
	        	   jornadaTrabalhoTO.setHoraEntrada(resultSet.getString("HORA_ENTRADA"));
	        	   jornadaTrabalhoTO.setHoraSaida(resultSet.getString("HORA_SAIDA"));
	        	   jornadaTrabalhoTO.setHoraSaidaAlmoco(resultSet.getString("HORA_INICIO_INTERVALO"));
	        	   jornadaTrabalhoTO.setHoraVoltaAlmoco(resultSet.getString("HORA_FIM_INTERVALO"));
	        	   jornadaTrabalhoTO.setDiaSemana(resultSet.getInt("DIA_SEMANA"));
	        	   jornadaTrabalhoTO.setDiaFolga(resultSet.getBoolean("DIA_FOLGA"));
	        	   jornadaTrabalhoTO.setDiaMeioPeriodo(resultSet.getBoolean("DIA_MEIO_PERIODO"));
	        	   lista.add(jornadaTrabalhoTO);
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
