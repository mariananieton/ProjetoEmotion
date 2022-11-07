package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.Empresa;
import br.com.fiap.emotion.model.EnderecoEmpresa;
import br.com.fiap.emotion.model.TipoEndereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Endereco Empresa
 */
public class EnderecoEmpresaDao implements AbstractDao<EnderecoEmpresa> {

	/**
	 * Método utilizado para inserir um Endereco no banco
	 *
	 * @param endereco a ser inserido
	 */
	@Override
	public void cadastrar(EnderecoEmpresa endereco) {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_ENDERECO_EMPRESA(id_endereco, " +
					" id_empresa, nr_cep, nm_bairro, nm_logradouro, nr_logradouro, " +
					" ds_complemento, ds_ponto_referencia, nm_cidade, nm_estado, " +
					" sg_estado, nm_pais, tp_endereco) values " +
					" (SQ_EM_ENDERECO_EMP.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stm.setInt(1, endereco.getEmpresa().getId());
			stm.setInt(2, endereco.getCep());
			stm.setString(3, endereco.getBairro());
			stm.setString(4, endereco.getLogradouro());
			stm.setInt(5, endereco.getNumero());
			stm.setString(6, endereco.getComplemento());
			stm.setString(7, endereco.getReferencia());
			stm.setString(8, endereco.getCidade());
			stm.setString(9, endereco.getEstado());
			stm.setString(10, endereco.getSiglaEstado());
			stm.setString(11, endereco.getPais());
			stm.setString(12, endereco.getTipo().name());

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				endereco.setId(result.getInt(1));
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
	 * @return lista de enderecos
	 */
	@Override
	public List<EnderecoEmpresa> consultarTodos() {
		List<EnderecoEmpresa> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_ENDERECO_EMPRESA inner join T_EM_EMPRESA TDP on TDP.ID_EMPRESA = T_EM_ENDERECO_EMPRESA.ID_EMPRESA");

			while (resultSet.next()) {
				EnderecoEmpresa endereco = new EnderecoEmpresa();
				endereco.setId(resultSet.getInt("id_endereco"));

				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));

				endereco.setEmpresa(empresa);
				endereco.setCep(resultSet.getInt("nr_cep"));
				endereco.setBairro(resultSet.getString("nm_bairro"));
				endereco.setLogradouro(resultSet.getString("nm_logradouro"));
				endereco.setNumero(resultSet.getInt("nr_logradouro"));
				endereco.setComplemento(resultSet.getString("ds_complemento"));
				endereco.setReferencia(resultSet.getString("ds_ponto_referencia"));
				endereco.setCidade(resultSet.getString("nm_cidade"));
				endereco.setEstado(resultSet.getString("nm_estado"));
				endereco.setSiglaEstado(resultSet.getString("sg_estado"));
				endereco.setPais(resultSet.getString("nm_pais"));
				endereco.setTipo(TipoEndereco.valueOf(resultSet.getString("tp_endereco")));

				lista.add(endereco);
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
	 * @param endereco a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(EnderecoEmpresa endereco) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_ENDERECO_EMPRESA set  " +
					" id_empresa = ?, nr_cep = ?, nm_bairro = ?, nm_logradouro = ?, " +
					" nr_logradouro = ?, ds_complemento = ?, ds_ponto_referencia = ?, " +
					" nm_cidade = ?, nm_estado = ? , sg_estado = ?, " +
					" nm_pais = ?, tp_endereco = ? where " +
					" id_endereco = ?");

			stm.setInt(1, endereco.getEmpresa().getId());
			stm.setInt(2, endereco.getCep());
			stm.setString(3, endereco.getBairro());
			stm.setString(4, endereco.getLogradouro());
			stm.setInt(5, endereco.getNumero());
			stm.setString(6, endereco.getComplemento());
			stm.setString(7, endereco.getReferencia());
			stm.setString(8, endereco.getCidade());
			stm.setString(9, endereco.getEstado());
			stm.setString(10, endereco.getSiglaEstado());
			stm.setString(11, endereco.getPais());
			stm.setString(12, endereco.getTipo().name());
			stm.setInt(13, endereco.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (endereco == null) {
			throw new ObjectNotFoundException("Esse endereco não pode ser encontrado");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 *
	 * @param endereco a ser removido
	 */
	@Override
	public void apagar(EnderecoEmpresa endereco) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_ENDERECO_EMPRESA where id_endereco = ?");
			stm.setInt(1, endereco.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
