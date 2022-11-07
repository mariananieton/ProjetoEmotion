package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.TelefoneUsuario;
import br.com.fiap.emotion.model.TipoTelefone;
import br.com.fiap.emotion.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Telefone Usuario
 */
public class TelefoneUsuarioDao implements AbstractDao<TelefoneUsuario> {

	/**
	 * Método utilizado para inserir um Telefone no banco
	 * @param telefone a ser inserido
	 */
	@Override
	public void cadastrar(TelefoneUsuario telefone) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_TELEFONE_USUARIO(id_telefone, " +
					" id_usuario, nr_ddi, nr_ddd, nr_telefone, st_telefone, tp_telefone) values " +
					" (SQ_EM_TELEFONE_USU.nextval, ?, ?, ?, ?, ?, ?)");

			stm.setInt(1, telefone.getUsuario().getId());
			stm.setInt(2, telefone.getDdi());
			stm.setInt(3, telefone.getDdd());
			stm.setInt(4, telefone.getNumero());
			stm.setString(5, String.valueOf(telefone.getStatus()));
			stm.setString(6, telefone.getTipo().name());


			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				telefone.setId(result.getInt(1));
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
	 * @return lista de telefones
	 */
	@Override
	public List<TelefoneUsuario> consultarTodos() {
		List<TelefoneUsuario> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_TELEFONE_USUARIO inner join T_EM_USUARIO TDP on TDP.ID_USUARIO = T_EM_TELEFONE_USUARIO.ID_USUARIO");

			while (resultSet.next()) {
				TelefoneUsuario telefone = new TelefoneUsuario();
				telefone.setId(resultSet.getInt("id_telefone"));

				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setNome(resultSet.getString("nm_usuario"));
				usuario.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				usuario.setCpf(resultSet.getInt("nr_cpf"));
				usuario.setLogin(resultSet.getString("nm_login"));
				usuario.setSenha(resultSet.getString("nm_senha"));
				usuario.setTotalPontos(resultSet.getInt("nr_total_pontos"));
				usuario.setStatus(resultSet.getString("st_usuario").charAt(0));

				telefone.setUsuario(usuario);
				telefone.setDdi(resultSet.getInt("nr_ddi"));
				telefone.setDdd(resultSet.getInt("nr_ddd"));
				telefone.setNumero(resultSet.getInt("nr_telefone"));
				telefone.setStatus(resultSet.getString("st_telefone").charAt(0));
				telefone.setTipo(TipoTelefone.valueOf(resultSet.getString("tp_telefone")));

				lista.add(telefone);
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
	 * @param telefone a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(TelefoneUsuario telefone) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_TELEFONE_USUARIO set  " +
					" id_usuario = ?, nr_ddi = ?, nr_ddd = ?, nr_telefone = ?, " +
					" st_telefone = ?, tp_telefone = ? where " +
					" id_telefone = ?");

			stm.setInt(1, telefone.getUsuario().getId());
			stm.setInt(2, telefone.getDdi());
			stm.setInt(3, telefone.getDdd());
			stm.setInt(4, telefone.getNumero());
			stm.setString(5, String.valueOf(telefone.getStatus()));
			stm.setString(6, telefone.getTipo().name());
			stm.setInt(7, telefone.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (telefone == null) {
			throw new ObjectNotFoundException("Esse telefone não pode ser encontrado");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 * @param telefone a ser removido
	 */
	@Override
	public void apagar(TelefoneUsuario telefone) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_TELEFONE_USUARIO where id_telefone = ?");
			stm.setInt(1, telefone.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
