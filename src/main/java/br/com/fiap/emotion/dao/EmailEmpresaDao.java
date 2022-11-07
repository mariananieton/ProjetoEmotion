package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.EmailEmpresa;
import br.com.fiap.emotion.model.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade Email Empresa
 */
public class EmailEmpresaDao implements AbstractDao<EmailEmpresa> {

	/**
	 * Método utilizado para inserir um Email no banco
	 *
	 * @param email a ser inserido
	 */
	@Override
	public void cadastrar(EmailEmpresa email) {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_EMAIL_EMPRESA(id_email, " +
					" id_empresa, ds_email, st_email) values " +
					" (SQ_EM_EMAIL_EMP.nextval, ?, ?, ?)");

			stm.setInt(1, email.getEmpresa().getId());
			stm.setString(2, email.getEmail());
			stm.setString(3,String.valueOf(email.getStatus()));

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
	public List<EmailEmpresa> consultarTodos() {
		List<EmailEmpresa> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_EMAIL_EMPRESA inner join T_EM_EMPRESA TDP on TDP.ID_EMPRESA = T_EM_EMAIL_EMPRESA.ID_EMPRESA");

			while (resultSet.next()) {
				EmailEmpresa email = new EmailEmpresa();
				email.setId(resultSet.getInt("id_email"));

				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setCnpj(resultSet.getInt("nr_cnpj"));
				empresa.setRazaoSocial(resultSet.getString("nm_razao_social"));

				email.setEmpresa(empresa);
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
	public void atualizar(EmailEmpresa email) throws ObjectNotFoundException {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_EMAIL_EMPRESA set  " +
					" id_empresa = ?, ds_email = ?, st_email = ? where " +
					" id_email = ?");

			stm.setInt(1, email.getEmpresa().getId());
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
	public void apagar(EmailEmpresa email) {

		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_EMAIL_EMPRESA where id_email = ?");
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
