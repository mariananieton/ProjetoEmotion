package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Empresa;
import br.com.fiap.emotion.model.Recompensas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Recompensas
 */
public class RecompensasDao implements AbstractDao<Recompensas> {

	/**
	 * Método utilizado para inserir recompensas no banco
	 *
	 * @param recompensas a ser inserido
	 */
	@Override
	public void cadastrar(Recompensas recompensas) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_RECOMPENSAS(id_recompensa, " +
					" id_empresa, nm_recompensa, nr_valor_pontos) values " +
					" (SQ_EM_RECOMP.nextval, ?, ?, ?)");

			stm.setInt(1, recompensas.getEmpresa().getId());
			stm.setString(2, recompensas.getNome());
			stm.setInt(3, recompensas.getValor());

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				recompensas.setId(result.getInt(1));
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
	 * @return lista de recompensas
	 */
	@Override
	public List<Recompensas> consultarTodos() {
		List<Recompensas> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_RECOMPENSAS inner join T_EM_EMPRESA TDP on TDP.ID_EMPRESA = T_EM_RECOMPENSAS.ID_EMPRESA");

			while (resultSet.next()) {
				Recompensas recompensas = new Recompensas();
				recompensas.setId(resultSet.getInt("id_recompensa"));

				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));

				recompensas.setEmpresa(empresa);
				recompensas.setNome(resultSet.getString("nm_recompensa"));
				recompensas.setValor(resultSet.getInt("nr_valor_pontos"));

				lista.add(recompensas);
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
	 * @param recompensa a ser atualizada
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(Recompensas recompensa) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_RECOMPENSAS set  " +
					" id_empresa = ?, nm_recompensa = ?, nr_valor_pontos = ? where " +
					" id_recompensa = ?");

			stm.setInt(1, recompensa.getEmpresa().getId());
			stm.setString(2, recompensa.getNome());
			stm.setInt(3, recompensa.getValor());
			stm.setInt(4, recompensa.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (recompensa == null) {
			throw new ObjectNotFoundException("Essa recompensa não pode ser encontrada");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 *
	 * @param recompensa a ser removido
	 */
	@Override
	public void apagar(Recompensas recompensa) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_RECOMPENSAS where id_recompensa = ?");
			stm.setInt(1, recompensa.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Método utilizado para pesquisar uma recompensa pelo nome
	 * @param nome a ser pesquisado
	 * @return lista de recompensas referentes ao nome pesquisado
	 */
	public List<Recompensas> pesquisarPorNome(String nome) {
		List<Recompensas> recompensaList = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("select * from T_EM_RECOMPENSAS where nm_recompensa = ?");
			stm.setString(1, nome);
			ResultSet resultSet = stm.executeQuery();

			while (resultSet.next()) {
				Recompensas recompensa = new Recompensas();
				recompensa.setId(resultSet.getInt("id_recompensa"));

				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));

				recompensa.setEmpresa(empresa);
				recompensa.setNome(resultSet.getString("nm_recompensa"));
				recompensa.setValor(resultSet.getInt("nr_valor_pontos"));

				recompensaList.add(recompensa);
			}

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		return recompensaList;
	}
}
