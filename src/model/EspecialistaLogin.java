package model;

import mysqldao.UsuarioDAO;

public class EspecialistaLogin {
	
	public UsuarioTO verificaLogin(String email, String senha){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();	
		UsuarioTO usuario = usuarioDAO.pesquisarLogin(email, senha);
		
		if(usuario == null){
			return null;
		}
		else {
			
				return usuario;
			
		}
	}
}
