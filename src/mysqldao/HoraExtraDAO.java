package mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import banco.MysqlConnect;
import model.EmpregadoTO;
import model.FeriasTO;
import model.HoraExtraTO;
import util.DataUtil;

public class HoraExtraDAO  extends MysqlConnect {
	   
	private PreparedStatement st;
	   
	   public HoraExtraDAO()
	   {
	      super();
	   }
	   
	   public boolean cadastrar(HoraExtraTO horaExtraTO)
	   {
	      try
	      {
	         String sql = "INSERT INTO HORA_EXTRA(HORA_EXTRA, HORA_EXTRA_NOTURNO, FOLGA_COMPENSATORIA, FK_PONTO) VALUES (?,?,?,?)";   
	     
		         st = conn.prepareStatement(sql);
		         st.setString(1, horaExtraTO.getTotalDeHorasExtras());
		         st.setString(2,horaExtraTO.getTotalDeHorasExtrasNoturno());
		         st.setString(3, horaExtraTO.getFolgaCompensatoria());
		         st.setString(4, horaExtraTO.getCodigoPonto());
		         st.executeUpdate(); 
		         st.close();
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }
	   
	   public boolean alterar(HoraExtraTO horaExtraTO)
	   { 
		      try
		      {
		         String sql = "UPDATE HORA_EXTRA SET HORA_EXTRA=?, HORA_EXTRA_NOTURNO = ?, FOLGA_COMPENSATORIA = ? WHERE FK_PONTO = ?";
				         
				         st = conn.prepareStatement(sql); 
				         st.setString(1, horaExtraTO.getTotalDeHorasExtras());
				         st.setString(2,horaExtraTO.getTotalDeHorasExtrasNoturno());
				         st.setString(3, horaExtraTO.getFolgaCompensatoria());
				         st.setString(4, horaExtraTO.getCodigoPonto());
				         st.executeUpdate(); 
				         st.close(); 
		      }
		      catch(Exception e){
		         e.printStackTrace();
		         return false;
		      }   
		      return true;
	   }
	   
	   public boolean excluir(String cod)
	   {
	         try
	         {
	            String sql = " DELETE FROM HORA_EXTRA WHERE CD_HORAS_EXTRAS = ?";
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
	   
	   public HoraExtraTO pesquisar(String codigo)
		{
		   HoraExtraTO horaExtraTO = null;
			try
			{
				
				String sql = " SELECT * FROM HORA_EXTRA WHERE CD_HORA_EXTRA = ?";
				st= (PreparedStatement) conn.prepareStatement(sql);
				st.setString(1,codigo);
				ResultSet resultSet = st.executeQuery();

				if(resultSet.next())
				{
					horaExtraTO = new HoraExtraTO();
					horaExtraTO.setCodigoPonto(resultSet.getString("FK_PONTO"));
					horaExtraTO.setTotalDeHorasExtras(resultSet.getString("HORA_EXTRA"));
					horaExtraTO.setFolgaCompensatoria(resultSet.getString("FOLGA_COMPENSATORIA"));
					horaExtraTO.setTotalDeHorasExtrasNoturno(resultSet.getString("HORA_EXTRA_NOTURNO"));
				}	
				st.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
			return horaExtraTO;
		}

}
