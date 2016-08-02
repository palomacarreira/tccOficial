package model;

import mysqldao.EnderecoDAO;

public class EspecialistaEndereco {
	
	EnderecoTO enderecoTO = new EnderecoTO();
	EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	
	public EnderecoTO pesquisarUsuario(String codg){
		
		enderecoTO = enderecoDAO.pesquisarUsuario(codg);
		return enderecoTO;
		
	}
	
	public void adicionarUsuario(String endereco, String cidade, String estado, int numeroEndereco,
			String complemento, String cep, String bairro){	
		
		enderecoTO.setEndereco(endereco);
		enderecoTO.setCidade(cidade);
		enderecoTO.setEstado(estado);
		enderecoTO.setNumero(numeroEndereco);
		enderecoTO.setComplemento(complemento);
		enderecoTO.setCep(cep);
		enderecoTO.setBairro(bairro);
		
    	enderecoDAO.cadastrarUsuario(enderecoTO);
	}

	public void alterarUsuario(String codigoUsuario, String endereco, String cidade, String estado, int numeroEndereco,
			String complemento, String cep, String bairro){	
	
		enderecoTO.setCodigoUsuario(codigoUsuario);
		enderecoTO.setEndereco(endereco);
		enderecoTO.setCidade(cidade);
		enderecoTO.setEstado(estado);
		enderecoTO.setNumero(numeroEndereco);
		enderecoTO.setComplemento(complemento);
		enderecoTO.setCep(cep);
		enderecoTO.setBairro(bairro);
		
		enderecoDAO.alterarUsuario(enderecoTO);
		
	}
	
	public EnderecoTO pesquisarEmpregado(String codg){
		
		enderecoTO = enderecoDAO.pesquisarEmpregado(codg);
		return enderecoTO;
		
	}
	
	public void adicionarEmpregado(String endereco, String cidade, String estado, int numeroEndereco,
			String complemento, String cep, String bairro){	
		
		enderecoTO.setEndereco(endereco);
		enderecoTO.setCidade(cidade);
		enderecoTO.setEstado(estado);
		enderecoTO.setNumero(numeroEndereco);
		enderecoTO.setComplemento(complemento);
		enderecoTO.setCep(cep);
		enderecoTO.setBairro(bairro);
		
    	enderecoDAO.cadastrarEmpregado(enderecoTO);
	}

	public void alterarEmpregado(String codigoEmpregado, String endereco, String cidade, String estado, int numeroEndereco,
			String complemento, String cep, String bairro){	
	
		enderecoTO.setCodigoEmpregado(codigoEmpregado);
		enderecoTO.setEndereco(endereco);
		enderecoTO.setCidade(cidade);
		enderecoTO.setEstado(estado);
		enderecoTO.setNumero(numeroEndereco);
		enderecoTO.setComplemento(complemento);
		enderecoTO.setCep(cep);
		enderecoTO.setBairro(bairro);
		
		enderecoDAO.alterarEmpregado(enderecoTO);
		
	}
}
