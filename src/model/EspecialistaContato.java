package model;

import mysqldao.ContatoDAO;

public class EspecialistaContato {
	ContatoTO contatoTO = new ContatoTO();
	ContatoDAO contatoDAO = new ContatoDAO();
	
	public void adicionarUsuario(String tipoContato, String numeroTelefone){

		contatoTO.setTipoContato(tipoContato);
		contatoTO.setNumero(numeroTelefone);
		
		contatoDAO.cadastrarUsuario(contatoTO);
	}
	
	public void adicionarEmpregado(String tipoContato, String numeroTelefone){

		contatoTO.setTipoContato(tipoContato);
		contatoTO.setNumero(numeroTelefone);
		
		contatoDAO.cadastrarEmrpegado(contatoTO);
	}
	
	public ContatoTO pesquisarUsuario(String codg){
		
		contatoTO = contatoDAO.pesquisarUsuario(codg);
		return contatoTO;
	}
	
	public ContatoTO pesquisarEmpregado(String codg){
		
		contatoTO = contatoDAO.pesquisarEmpregado(codg);
		return contatoTO;
	}
	
	public void alterarUsuario(String codigoUsuario, String tipoContato, String numeroTelefone){

		contatoTO.setCodigoUsuario(codigoUsuario);
		contatoTO.setTipoContato(tipoContato);
		contatoTO.setNumero(numeroTelefone);

		contatoDAO.alterarUsuario(contatoTO);
	}
	
	public void alterarEmpregado(String codigoEmpregado, String tipoContato, String numeroTelefone){

		contatoTO.setCodigoEmpregado(codigoEmpregado);
		contatoTO.setTipoContato(tipoContato);
		contatoTO.setNumero(numeroTelefone);

		contatoDAO.alterarEmpregado(contatoTO);
	}
	
	
}
