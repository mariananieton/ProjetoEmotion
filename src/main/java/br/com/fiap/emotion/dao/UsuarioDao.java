package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Usuario
 */
public class UsuarioDao implements AbstractDao<Usuario> {

	/**
	 * Método utilizado para inserir um Usuario no banco
	 *
	 * @param usuario a ser inserido
	 */
	@Override
	public void cadastrar(Usuario usuario) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_USUARIO(id_usuario, " +
					" nm_usuario, dt_nascimento, nr_cpf, nm_login, nm_senha, " +
					" nr_total_pontos, st_usuario) values " +
					" (SQ_EM_USUARIO.nextval, ?, ?, ?, ?, ?, ?, ?)");

			stm.setString(1, usuario.getNome());
			stm.setDate(2, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stm.setInt(3, usuario.getCpf());
			stm.setString(4, usuario.getLogin());
			stm.setString(5, usuario.getSenha());
			stm.setInt(6, usuario.getTotalPontos());
			stm.setString(7, String.valueOf(usuario.getStatus()));

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				usuario.setId(result.getInt(1));
			}

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
	}

	/**
	 * Método utilizado para pesquisar um Usuario pelo Id do banco
	 *
	 * @param id a ser pesquisado
	 * @return usuario referente ao id pesquisado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	public Usuario pesquisarPorCodigo(int id) throws ObjectNotFoundException {

		Usuario usuario = null;
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("select * from T_EM_USUARIO where id_usuario = ?");
			stm.setInt(1, id);
			ResultSet resultSet = stm.executeQuery();

			if (resultSet.next()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setNome(resultSet.getString("nm_usuario"));
				usuario.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				usuario.setCpf(resultSet.getInt("nr_cpf"));
				usuario.setLogin(resultSet.getString("nm_login"));
				usuario.setSenha(resultSet.getString("nm_senha"));
				usuario.setTotalPontos(resultSet.getInt("nr_total_pontos"));
				usuario.setStatus(resultSet.getString("st_usuario").charAt(0));
			}

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (usuario == null) {
			throw new ObjectNotFoundException("Esse usuario não pode ser encontrado");
		}
		return usuario;
	}

	/**
	 * Método utilizado para listar todos os registros do banco
	 *
	 * @return lista de usuarios
	 */
	@Override
	public List<Usuario> consultarTodos() {
		List<Usuario> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_USUARIO");

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setNome(resultSet.getString("nm_usuario"));
				usuario.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				usuario.setCpf(resultSet.getInt("nr_cpf"));
				usuario.setLogin(resultSet.getString("nm_login"));
				usuario.setSenha(resultSet.getString("nm_senha"));
				usuario.setTotalPontos(resultSet.getInt("nr_total_pontos"));
				usuario.setStatus(resultSet.getString("st_usuario").charAt(0));

				lista.add(usuario);
			}

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	/**
	 * Método utilizado para atualizar um registro do banco
	 *
	 * @param usuario a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(Usuario usuario) throws ObjectNotFoundException {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_USUARIO set  " +
					" nm_usuario = ?, dt_nascimento = ?, nr_cpf = ?, " +
					" nm_login = ?, nm_senha = ?, nr_total_pontos = ?, st_usuario = ?" +
					" where id_usuario = ?");

			stm.setString(1, usuario.getNome());
			stm.setDate(2, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stm.setInt(3, usuario.getCpf());
			stm.setString(4, usuario.getLogin());
			stm.setString(5, usuario.getSenha());
			stm.setInt(6, usuario.getTotalPontos());
			stm.setString(7, String.valueOf(usuario.getStatus()));
			stm.setInt(8, usuario.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (usuario == null) {
			throw new ObjectNotFoundException("Esse usuario não pode ser encontrado");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 *
	 * @param usuario a ser removido
	 */
	@Override
	public void apagar(Usuario usuario) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_USUARIO where id_usuario = ?");
			stm.setInt(1, usuario.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscaUsuarioPorLogin(String login) {
		Usuario usuario = null;

		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("select * from T_EM_USUARIO where nm_login = ?");
			stm.setString(1, login);

			ResultSet resultSet = stm.executeQuery();

			if (resultSet.next()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setNome(resultSet.getString("nm_usuario"));
				usuario.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				usuario.setCpf(resultSet.getInt("nr_cpf"));
				usuario.setLogin(resultSet.getString("nm_login"));
				usuario.setSenha(resultSet.getString("nm_senha"));
				usuario.setTotalPontos(resultSet.getInt("nr_total_pontos"));
				usuario.setStatus(resultSet.getString("st_usuario").charAt(0));
			}
			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuario;
	}
}
