package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.EmailUsuario;
import br.com.fiap.emotion.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Email Usuario
 */
public class EmailUsuarioDao implements AbstractDao<EmailUsuario> {

	/**
	 * Método utilizado para inserir um Email no banco
	 *
	 * @param email a ser inserido
	 */
	@Override
	public void cadastrar(EmailUsuario email) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_EMAIL_USUARIO(id_email, " +
					" id_usuario, ds_email, st_email) values " +
					" (SQ_EM_EMAIL_USU.nextval, ?, ?, ?)");

			stm.setInt(1, email.getUsuario().getId());
			stm.setString(2, email.getEmail());
			stm.setString(3, String.valueOf(email.getStatus()));

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				email.setId(result.getInt(1));
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
	 * Método utilizado para listar todos os registros do banco
	 *
	 * @return lista de emails
	 */
	@Override
	public List<EmailUsuario> consultarTodos() {
		List<EmailUsuario> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_EMAIL_USUARIO inner join T_EM_USUARIO TDP on TDP.ID_USUARIO = T_EM_EMAIL_USUARIO.ID_USUARIO");

			while (resultSet.next()) {
				EmailUsuario email = new EmailUsuario();
				email.setId(resultSet.getInt("id_email"));

				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setNome(resultSet.getString("nm_usuario"));
				usuario.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				usuario.setCpf(resultSet.getInt("nr_cpf"));
				usuario.setLogin(resultSet.getString("nm_login"));
				usuario.setSenha(resultSet.getString("nm_senha"));
				usuario.setTotalPontos(resultSet.getInt("nr_total_pontos"));
				usuario.setStatus(resultSet.getString("st_usuario").charAt(0));

				email.setUsuario(usuario);
				email.setEmail(resultSet.getString("ds_email"));
				email.setStatus(resultSet.getString("st_email").charAt(0));

				lista.add(email);
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
	 * @param email a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(EmailUsuario email) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_EMAIL_USUARIO set  " +
					" id_usuario = ?, ds_email = ?, st_email = ? where " +
					" id_email = ?");

			stm.setInt(1, email.getUsuario().getId());
			stm.setString(2, email.getEmail());
			stm.setString(3, String.valueOf(email.getStatus()));
			stm.setInt(4, email.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (email == null) {
			throw new ObjectNotFoundException("Esse email não pode ser encontrado");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 *
	 * @param email a ser removido
	 */
	@Override
	public void apagar(EmailUsuario email) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_EMAIL_USUARIO where id_email = ?");
			stm.setInt(1, email.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
