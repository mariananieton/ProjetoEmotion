package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Bicicleta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Bicicleta
 */
public class BicicletaDao implements AbstractDao<Bicicleta> {

	/**
	 * Método utilizado para inserir uma Bicicleta no banco
	 *
	 * @param bicicleta a ser inserida
	 */
	@Override
	public void cadastrar(Bicicleta bicicleta) {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_BICICLETA(id_bicicleta, " +
					" nm_modelo, nm_marca, st_bicicleta) values " +
					" (SQ_EM_BICICLETA.nextval, ?, ?, ?)");

			stm.setString(1, bicicleta.getModelo());
			stm.setString(2, bicicleta.getMarca());
			stm.setString(3, String.valueOf(bicicleta.getStatus()));

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				bicicleta.setId(result.getInt(1));
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
	 * @return lista de bicicletas
	 */
	@Override
	public List<Bicicleta> consultarTodos() {
		List<Bicicleta> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_BICICLETA");

			while (resultSet.next()) {
				Bicicleta bicicleta = new Bicicleta();
				bicicleta.setId(resultSet.getInt("id_bicicleta"));
				bicicleta.setModelo(resultSet.getString("nm_modelo"));
				bicicleta.setMarca(resultSet.getString("nm_marca"));
				bicicleta.setStatus(resultSet.getString("st_bicicleta").charAt(0));

				lista.add(bicicleta);
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
	 * @param bicicleta a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(Bicicleta bicicleta) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_BICICLETA set  " +
					" nm_modelo = ?, nm_marca = ?, st_bicicleta = ? where " +
					" id_bicicleta = ?");

			stm.setString(1, bicicleta.getModelo());
			stm.setString(2, bicicleta.getMarca());
			stm.setString(3, String.valueOf(bicicleta.getStatus()));
			stm.setInt(4, bicicleta.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (bicicleta == null) {
			throw new ObjectNotFoundException("Essa bicicleta não pode ser encontrada");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 * @param bicicleta a ser removido
	 */
	@Override
	public void apagar(Bicicleta bicicleta) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_BICICLETA where id_bicicleta = ?");
			stm.setInt(1, bicicleta.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
