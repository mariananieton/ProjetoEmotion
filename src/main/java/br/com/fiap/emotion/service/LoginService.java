package br.com.fiap.emotion.service;

import br.com.fiap.emotion.dao.UsuarioDao;
import br.com.fiap.emotion.exception.InvalidPasswordException;
import br.com.fiap.emotion.model.Usuario;

public class LoginService {

	private static final UsuarioDao usuarioDao = new UsuarioDao();

	public Usuario validaLogin(String login, String senha) throws InvalidPasswordException {
		Usuario usuario = usuarioDao.buscaUsuarioPorLogin(login);
		if (!usuario.getSenha().equals(senha)) {
			throw new InvalidPasswordException("Senha inválida");
		}
		return usuario;
	}
}
