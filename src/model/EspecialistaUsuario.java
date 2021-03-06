package model;


import mysqldao.UsuarioDAO;


	public class EspecialistaUsuario {
		
		UsuarioTO usuarioTO = new UsuarioTO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();	
		
		
		public String getUltimoCodigo(){
			
			String codigo = usuarioDAO.ultimoCodigoUsuario();
			
			return codigo;
		}


		public void adicionar(String nome, String sobrenome, String cpf, String cnpj, String rg,
				String ufRg, String email, String senha, Boolean ativo){		
			
			usuarioTO.setNome(nome); 
			usuarioTO.setSobrenome(sobrenome);
			usuarioTO.setCpf(cpf); 
			usuarioTO.setCnpj(cnpj); 
			usuarioTO.setRg(rg);
			usuarioTO.setUfRg(ufRg);
			usuarioTO.setEmail(email);
			usuarioTO.setSenha(senha);
			usuarioTO.setAtivo(ativo);
		
	    	usuarioDAO.cadastrarUsuario(usuarioTO);
		}
		
		
		public void alterar(String codigo, String nome, String sobrenome, String cpf, String cnpj, String rg,
				String ufRg, String email, String senha, Boolean ativo){	
			
			usuarioTO.setCodigo(codigo);
			usuarioTO.setNome(nome); 
			usuarioTO.setSobrenome(sobrenome);
			usuarioTO.setCpf(cpf); 
			usuarioTO.setCnpj(cnpj); 
			usuarioTO.setRg(rg);
			usuarioTO.setUfRg(ufRg);
			usuarioTO.setEmail(email);
			usuarioTO.setSenha(senha);
			usuarioTO.setAtivo(ativo);
			
			usuarioDAO.alterarUsuario(usuarioTO);
	  
		}
		
		 public void excluir(String cod){
			
			usuarioDAO.excluirUsuario(cod);

		}
		
		public UsuarioTO pesquisarUsuario(String codg){
			
			usuarioTO = usuarioDAO.pesquisarUsuario(codg);
			return usuarioTO;
		}
		
		public UsuarioTO pesquisarNomeEmpregador(String codg){
			
			usuarioTO = usuarioDAO.pesquisarNomeEmpregador(codg);
			return usuarioTO;
		}
		
		 public boolean ativarUsuario(String codigo)
		 {
			 usuarioDAO.ativarUsuario(codigo);
			return true;
		 }
}
