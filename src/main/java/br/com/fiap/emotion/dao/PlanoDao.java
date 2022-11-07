package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Plano;
import br.com.fiap.emotion.model.TipoPlano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Plano
 */
public class PlanoDao implements AbstractDao<Plano> {

	/**
	 * Método utilizado para inserir um Plano no banco
	 *
	 * @param plano a ser inserido
	 */
	@Override
	public void cadastrar(Plano plano) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_PLANO(id_plano, " +
					" nm_plano, nr_valor, tp_plano, st_plano) values " +
					" (SQ_EM_PLANO.nextval, ?, ?, ?, ?)");

			stm.setString(1, plano.getNome());
			stm.setDouble(2, plano.getValor());
			stm.setString(3,String.valueOf(plano.getTipo()));
			stm.setString(4, String.valueOf(plano.getStatus()));

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				plano.setId(result.getInt(1));
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
	 * Método utilizado para pesquisar um Plano pelo Id do banco
	 * @param id a ser pesquisado
	 * @return plano referente ao id pesquisado
	 */
	public Plano pesquisarPorCodigo(int id) {

		Plano plano = null;
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("select * from T_EM_PLANO where id_plano = ?");
			stm.setInt(1, id);
			ResultSet resultSet = stm.executeQuery();

			if (resultSet.next()) {
				plano = new Plano();

				plano.setId(resultSet.getInt("id_plano"));
				plano.setNome(resultSet.getString("nm_plano"));
				plano.setValor(resultSet.getDouble("nr_valor"));
				plano.setTipo(TipoPlano.valueOf(resultSet.getString("tp_plano")));
				plano.setStatus(resultSet.getString("st_plano").charAt(0));
			}

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return plano;
	}

	/**
	 * Método utilizado para listar todos os registros do banco
	 *
	 * @return lista de planos
	 */
	@Override
	public List<Plano> consultarTodos() {
		List<Plano> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_PLANO");

			while (resultSet.next()) {
				Plano plano = new Plano();
				plano.setId(resultSet.getInt("id_plano"));
				plano.setNome(resultSet.getString("nm_plano"));
				plano.setValor(resultSet.getDouble("nr_plano"));
				plano.setTipo(TipoPlano.valueOf(resultSet.getString("tp_plano")));
				plano.setStatus(resultSet.getString("st_plano").charAt(0));

				lista.add(plano);
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
	 * @param plano a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(Plano plano) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_PLANO set  " +
					" nm_plano = ?, nr_valor = ?, tp_plano = ?, st_plano = ? where + " +
					" id_plano = ?");

			stm.setString(1, plano.getNome());
			stm.setDouble(2, plano.getValor());
			stm.setString(3, plano.getTipo().name());
			stm.setString(4, String.valueOf(plano.getStatus()));
			stm.setInt(5, plano.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (plano == null) {
			throw new ObjectNotFoundException("Esse plano não pode ser encontrado");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 * @param plano a ser removido
	 */
	@Override
	public void apagar(Plano plano) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_PLANO where id_plano = ?");
			stm.setInt(1, plano.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
