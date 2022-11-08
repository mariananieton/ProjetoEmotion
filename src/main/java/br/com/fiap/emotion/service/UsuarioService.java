package br.com.fiap.emotion.service;

import br.com.fiap.emotion.dao.UsuarioDao;
import br.com.fiap.emotion.exception.NotUniqueLoginException;
import br.com.fiap.emotion.model.Usuario;

public class UsuarioService {

	private static final UsuarioDao usuarioDao = new UsuarioDao();

	public void cadastrarUsuario(Usuario usuario) throws NotUniqueLoginException {
		Usuario usuarioExistente = usuarioDao.buscaUsuarioPorLogin(usuario.getLogin());
		if (usuarioExistente != null) {
			throw new NotUniqueLoginException("Esse login já está sendo utilizado!");
		}
		usuarioDao.cadastrar(usuario);
	}
}
