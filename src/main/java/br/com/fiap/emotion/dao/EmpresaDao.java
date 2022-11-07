package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Empresa
 */
public class EmpresaDao implements AbstractDao<Empresa> {

	/**
	 * Método utilizado para inserir uma Empresa no banco
	 *
	 * @param empresa a ser inserida
	 */
	@Override
	public void cadastrar(Empresa empresa) {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_EMPRESA(id_empresa, " +
					" nr_cnpj, nm_razao_social) values " +
					" (SQ_EM_EMPRESA.nextval, ?, ?)");

			stm.setInt(1, empresa.getCnpj());
			stm.setString(2, empresa.getRazaoSocial());

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				empresa.setId(result.getInt(1));
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
	 * Método utilizado para pesquisar um Empresa pelo Id do banco
	 *
	 * @param id a ser pesquisado
	 * @return empresa referente ao id pesquisado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	public Empresa pesquisarPorCodigo(int id) throws ObjectNotFoundException {

		Empresa empresa = null;
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("select * from T_EM_EMPRESA where id_empresa = ?");
			stm.setInt(1, id);
			ResultSet resultSet = stm.executeQuery();

			if (resultSet.next()) {
				empresa = new Empresa();

				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));
			}

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (empresa == null) {
			throw new ObjectNotFoundException("Essa empresa não pode ser encontrada");
		}
		return empresa;
	}

	/**
	 * Método utilizado para listar todos os registros do banco
	 *
	 * @return lista de empresas
	 */
	@Override
	public List<Empresa> consultarTodos() {
		List<Empresa> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_EMPRESA");

			while (resultSet.next()) {
				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));

				lista.add(empresa);
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
	 * @param empresa a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(Empresa empresa) throws ObjectNotFoundException {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_EMPRESA set  " +
					" nr_cnpj = ?, nm_razao_social = ? where " +
					" id_empresa = ?");

			stm.setInt(1, empresa.getCnpj());
			stm.setString(2, empresa.getRazaoSocial());
			stm.setInt(3, empresa.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (empresa == null) {
			throw new ObjectNotFoundException("Essa empresa não pode ser encontrada");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 *
	 * @param empresa a ser removido
	 */
	@Override
	public void apagar(Empresa empresa) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_EMPRESA where id_empresa = ?");
			stm.setInt(1, empresa.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
