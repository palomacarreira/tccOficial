package mysqldao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import banco.MysqlConnect;
import model.EmpregadoTO;

public class EmpregadoDAO extends MysqlConnect{
	

	private PreparedStatement st;
	EmpregadoTO empregadoTO = null;
	                                             
	public boolean cadastrarEmpregado(EmpregadoTO empregadoTO)
	{

		try{
			String sql = "INSERT INTO EMPREGADO("
					+ "nome, sobrenome, DATA_NASC, ESTADO_CIVIL, sexo, "
					+ "cpf, rg,UF_RG, NUM_CARTEIRA, SERIE_CARTEIRA, UF_CARTEIRA,"
					+ "E_MAIL, senha, ativo, foto, NUM_DEPENDENTES) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";       
			
			st = (PreparedStatement) conn.prepareStatement(sql);
			
			st.setString(1,empregadoTO.getNome());
			st.setString(2,empregadoTO.getSobrenome());
			st.setDate(3,(java.sql.Date) empregadoTO.getDataNasc());
			st.setString(4,empregadoTO.getEstadoCivil());
			st.setString(5,empregadoTO.getSexo());
			st.setString(6,empregadoTO.getCpf());
			st.setString(7,empregadoTO.getRg());
			st.setString(8,empregadoTO.getUfRg());
			st.setString(9,empregadoTO.getNumCarteira());
			st.setString(10,empregadoTO.getSerieCarteira());
			st.setString(11,empregadoTO.getUfCarteira());
			st.setString(12,empregadoTO.getEmail());
			st.setString(13,empregadoTO.getSenha());
			st.setBoolean(14,empregadoTO.getAtivo());
			st.setBytes(15,empregadoTO.getFoto());
			st.setInt(16,empregadoTO.getQtdDependentes());
			st.executeUpdate(); 
			st.close();

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean alterarEmpregado(EmpregadoTO empregadoTO)
	{ 
		try{
			String sql = "UPDATE EMPREGADO SET" 
					+" nome = ?, sobrenome = ?, dataNasc=?, estadoCivil=?, sexo=?, "
					+ "cpf=?, rg=?, fRg=?, numCarteira=?, serieCarteira=?, ufCarteira=?,"
					+ "login=?, senha=?, ativo=?, foto=?, qtdDependentes=? WHERE CD_EMPREGADO = ?";
					
			st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,empregadoTO.getNome());
			st.setString(2,empregadoTO.getSobrenome());
			st.setDate(3,(java.sql.Date) empregadoTO.getDataNasc());
			st.setString(4,empregadoTO.getEstadoCivil());
			st.setString(5,empregadoTO.getSexo());
			st.setString(6,empregadoTO.getCpf());
			st.setString(7,empregadoTO.getRg());
			st.setString(8,empregadoTO.getUfRg());
			st.setString(9,empregadoTO.getNumCarteira());
			st.setString(10,empregadoTO.getSerieCarteira());
			st.setString(11,empregadoTO.getUfCarteira());
			st.setString(12,empregadoTO.getEmail());
			st.setString(13,empregadoTO.getSenha());
			st.setBoolean(14,empregadoTO.getAtivo());
			st.setBytes(15,empregadoTO.getFoto());
			st.setInt(16,empregadoTO.getQtdDependentes());
			st.setString(17, empregadoTO.getCodigoEmpregado());
			st.executeUpdate(); 
			st.close(); 
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}   
		return true;
	}


	public EmpregadoTO pesquisarEmpregado(String codigo)
	{
		try
		{
			
			String sql = " SELECT * FROM EMPREGADO WHERE CD_EMPREGADO = ?";
			st= (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,codigo);
			ResultSet resultSet = st.executeQuery();
		
			if(resultSet.next())
			{
				empregadoTO = new EmpregadoTO();
				empregadoTO.setCodigo(codigo);
				empregadoTO.setNome(resultSet.getString("nome"));
				empregadoTO.setSobrenome(resultSet.getString("sobrenome"));
				empregadoTO.setDataNasc(resultSet.getDate("data_nasc"));
				empregadoTO.setEstadoCivil(resultSet.getString("estado_civil"));
				empregadoTO.setSexo(resultSet.getString("sexo"));
				empregadoTO.setCpf(resultSet.getString("cpf"));
				empregadoTO.setRg(resultSet.getString("rg"));
				empregadoTO.setUfRg(resultSet.getString("uf_rg"));
				empregadoTO.setNumCarteira(resultSet.getString("num_carteira"));
				empregadoTO.setSerieCarteira(resultSet.getString("serie_carteira"));
				empregadoTO.setUfCarteira(resultSet.getString("uf_carteira"));
				empregadoTO.setEmail(resultSet.getString("e_mail"));
				empregadoTO.setSenha(resultSet.getString("senha"));
				empregadoTO.setQtdDependentes(resultSet.getInt("NUM_DEPENDENTES"));
				empregadoTO.setAtivo(resultSet.getBoolean("ativo"));
				empregadoTO.setFoto(resultSet.getBytes("foto"));
			}	
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return empregadoTO;
	}

	
	public ArrayList<EmpregadoTO> pesquisarTodos()
	{
		ArrayList<EmpregadoTO> lista= new ArrayList<EmpregadoTO>();
		
		try
		{
			String sql ="select b.nome, b.cd_empregado, b.foto from usuario a inner join contrato c on a.cd_usuario = c.fk_usuario"		
			+" inner join empregado b on c.fk_empregado = b.cd_empregado WHERE b.ATIVO = 0";
		
			st= (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			while(resultSet.next())
			{
				empregadoTO = new EmpregadoTO();
				empregadoTO.setCodigo(resultSet.getString("b.cd_empregado"));	
				empregadoTO.setNome(resultSet.getString("b.nome"));
				empregadoTO.setFoto(resultSet.getBytes("b.foto"));
				lista.add(empregadoTO);
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

	
	public boolean excluirEmpregado(String codigo)
	{
		try
		{
			  String sql = " UPDATE EMPREGADO SET ativo = ?  where CD_EMPREGADO = ?";
			  st = (PreparedStatement) conn.prepareStatement(sql);
			  st.setBoolean(1, true);
	           st.setString(2,codigo);
	           st.executeUpdate();
	           st.close(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}        
		return true;  
	}
	
	  public String ultimoCodigoEmpregado()
	   {
		   	String codigo = null;
		   	
	         try
	         {
	            String sql = "SELECT MAX(CD_EMPREGADO) FROM EMPREGADO";
	            st= (PreparedStatement) conn.prepareStatement(sql);
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
}
