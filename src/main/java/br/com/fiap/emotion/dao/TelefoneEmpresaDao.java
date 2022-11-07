package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Empresa;
import br.com.fiap.emotion.model.TelefoneEmpresa;
import br.com.fiap.emotion.model.TipoTelefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Telefone Empresa
 */
public class TelefoneEmpresaDao implements AbstractDao<TelefoneEmpresa> {

	/**
	 * Método utilizado para inserir um Telefone no banco
	 *
	 * @param telefone a ser inserido
	 */
	@Override
	public void cadastrar(TelefoneEmpresa telefone) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_TELEFONE_EMPRESA(id_telefone, " +
					" id_empresa, nr_ddi, nr_ddd, nr_telefone, st_telefone, tp_telefone) values " +
					" (SQ_EM_TELEFONE_EMP.nextval, ?, ?, ?, ?, ?, ?)");

			stm.setInt(1, telefone.getEmpresa().getId());
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
	 *
	 * @return lista de telefones
	 */
	@Override
	public List<TelefoneEmpresa> consultarTodos() {
		List<TelefoneEmpresa> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_TELEFONE_EMPRESA inner join T_EM_EMPRESA TDP on TDP.ID_EMPRESA = T_EM_TELEFONE_EMPRESA.ID_EMPRESA");

			while (resultSet.next()) {
				TelefoneEmpresa telefone = new TelefoneEmpresa();
				telefone.setId(resultSet.getInt("id_telefone"));

				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));

				telefone.setEmpresa(empresa);
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
	 *
	 * @param telefone a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(TelefoneEmpresa telefone) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_TELEFONE_EMPRESA set  " +
					" id_empresa = ?, nr_ddi = ?, nr_ddd = ?, nr_telefone = ?, " +
					" st_telefone = ?, tp_telefone = ? where " +
					" id_telefone = ?");

			stm.setInt(1, telefone.getEmpresa().getId());
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
	 *
	 * @param telefone a ser removido
	 */
	@Override
	public void apagar(TelefoneEmpresa telefone) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_TELEFONE_EMPRESA where id_telefone = ?");
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
