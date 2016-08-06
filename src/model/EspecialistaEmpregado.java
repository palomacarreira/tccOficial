package model;

import java.sql.Date;
import java.util.ArrayList;
import mysqldao.EmpregadoDAO;

public class EspecialistaEmpregado 
{
	EmpregadoDAO empregadoDAO = new EmpregadoDAO();	
	EmpregadoTO empregadoTO = new EmpregadoTO();
	
	public void adicionar(String nome, String sobrenome, Date dataNasc, String estadoCivil, String sexo, 
	String cpf, String rg, String ufRg, String numCarteira,String serieCarteira, String ufCarteira, 
	String email, String senha, Boolean ativo, byte[] foto, int qtdDependentes){

		empregadoTO.setNome(nome);
		empregadoTO.setSobrenome(sobrenome);
		empregadoTO.setDataNasc(dataNasc);
		empregadoTO.setEstadoCivil(estadoCivil);
		empregadoTO.setSexo(sexo);
		empregadoTO.setCpf(cpf);
		empregadoTO.setRg(rg);
		empregadoTO.setUfRg(ufRg);
		empregadoTO.setNumCarteira(numCarteira);
		empregadoTO.setSerieCarteira(serieCarteira);
		empregadoTO.setUfCarteira(ufCarteira);
		empregadoTO.setEmail(email);
		empregadoTO.setSenha(senha);
		empregadoTO.setAtivo(ativo);
		empregadoTO.setFoto(foto);
		empregadoTO.setQtdDependentes(qtdDependentes);
		
        empregadoDAO.cadastrarEmpregado(empregadoTO); 
	}
	
	public void alterar(String codigo, String nome, String sobrenome, Date dataNasc, String estadoCivil, String sexo, 
			String cpf, String rg, String ufRg, String numCarteira,String serieCarteira, String ufCarteira, 
			String email, String senha, Boolean ativo, byte[] foto, int qtdDependentes){
		
		empregadoTO.setCodigo(codigo);
		empregadoTO.setNome(nome);
		empregadoTO.setSobrenome(sobrenome);
		empregadoTO.setDataNasc(dataNasc);
		empregadoTO.setEstadoCivil(estadoCivil);
		empregadoTO.setSexo(sexo);
		empregadoTO.setCpf(cpf);
		empregadoTO.setRg(rg);
		empregadoTO.setUfRg(ufRg);
		empregadoTO.setNumCarteira(numCarteira);
		empregadoTO.setSerieCarteira(serieCarteira);
		empregadoTO.setUfCarteira(ufCarteira);
		empregadoTO.setEmail(email);
		empregadoTO.setSenha(senha);
		empregadoTO.setAtivo(ativo);
		empregadoTO.setFoto(foto);
		empregadoTO.setQtdDependentes(qtdDependentes);
		
		empregadoDAO.alterarEmpregado(empregadoTO);
        
	}
	
	public Boolean excluir(String codigo){
		
		return empregadoDAO.excluirEmpregado(codigo);
        
	}
	
	public EmpregadoTO pesquisar(String codigo){
		
		empregadoTO = empregadoDAO.pesquisarEmpregado(codigo);
		return empregadoTO;
	}
	
	public ArrayList<EmpregadoTO> pesquisarTodos(String codigoUsuario){
		ArrayList<EmpregadoTO> empregadoTO = new ArrayList<EmpregadoTO>();
		empregadoTO  = empregadoDAO.pesquisarTodos();
		return empregadoTO;
	}
	
	public String getUltimoCodigo(){
		
		String codigo = empregadoDAO.ultimoCodigoEmpregado();
		
		return codigo;
	}

}
