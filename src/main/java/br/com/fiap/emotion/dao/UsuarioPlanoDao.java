package br.com.fiap.emotion.dao;

import br.com.fiap.emotion.exception.ObjectNotFoundException;
import br.com.fiap.emotion.factory.ConnectionFactory;
import br.com.fiap.emotion.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariana
 * Classe com objetivo de realizar o CRUD da entidade UsuarioPlano
 */
public class UsuarioPlanoDao implements AbstractDao<UsuarioPlano> {

	/**
	 * Método utilizado para inserir os dados entre usuario e plano no banco
	 * @param usuarioPlano a ser inserida
	 */
	@Override
	public void cadastrar(UsuarioPlano usuarioPlano) {

		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("insert into T_EM_USUARIO_PLANO(id_usuario_plano, " +
					" id_usuario, id_plano, id_bicicleta, dt_ultima_utilizacao, nr_km_rodados, " +
					" st_usuario_plano, dt_entrega_bike, dt_retorno_bike, " +
					" dt_prevista_retorno, nr_pontos) values " +
					" (SQ_EM_USU_PLANO.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stm.setInt(1, usuarioPlano.getUsuario().getId());
			stm.setInt(2, usuarioPlano.getPlano().getId());
			stm.setInt(3, usuarioPlano.getBicicleta().getId());
			stm.setDate(4, java.sql.Date.valueOf(usuarioPlano.getDataUltimaUtilizacao()));
			stm.setInt(5, usuarioPlano.getKmRodados());
			stm.setString(6, String.valueOf(usuarioPlano.getStatus()));
			stm.setDate(7, java.sql.Date.valueOf(usuarioPlano.getDataEntregaBike()));
			stm.setDate(8, java.sql.Date.valueOf(usuarioPlano.getDataRetornoBike()));
			stm.setDate(9, java.sql.Date.valueOf(usuarioPlano.getDataPrevistaRetorno()));
			stm.setInt(10, usuarioPlano.getPontos());

			stm.executeUpdate();

			ResultSet result = stm.getGeneratedKeys();
			if (result.next()) {
				usuarioPlano.setId(result.getInt(1));
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
	 * @return lista de utilizacoes
	 */
	@Override
	public List<UsuarioPlano> consultarTodos() {
		List<UsuarioPlano> lista = new ArrayList<>();

		try {
			Connection conexao = ConnectionFactory.getConnection();

			Statement stm = conexao.createStatement();

			ResultSet resultSet = stm.executeQuery("select * from T_EM_USUARIO_PLANO " +
					" inner join T_EM_USUARIO TDP on TDP.ID_USUARIO = T_EM_USUARIO_PLANO.ID_USUARIO" +
					" inner join T_EM_PLANO TP on TP.ID_PLANO = T_EM_USUARIO_PLANO.ID_PLANO " +
					" inner join T_EM_BICICLETA TD on TD.ID_BICICLETA = T_EM_USUARIO_PLANO.ID_BICICLETA ");

			while (resultSet.next()) {
				UsuarioPlano usuarioPlano = new UsuarioPlano();
				usuarioPlano.setId(resultSet.getInt("id_usuario_plano"));

				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setNome(resultSet.getString("nm_usuario"));
				usuario.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());
				usuario.setCpf(resultSet.getInt("nr_cpf"));
				usuario.setLogin(resultSet.getString("nm_login"));
				usuario.setSenha(resultSet.getString("nm_senha"));
				usuario.setTotalPontos(resultSet.getInt("nr_total_pontos"));
				usuario.setStatus(resultSet.getString("st_usuario").charAt(0));

				Plano plano = new Plano();
				plano.setId(resultSet.getInt("id_plano"));
				plano.setNome(resultSet.getString("nm_plano"));
				plano.setValor(resultSet.getDouble("nr_valor"));
				plano.setTipo(TipoPlano.valueOf(resultSet.getString("tp_plano")));
				plano.setStatus(resultSet.getString("st_plano").charAt(0));

				Bicicleta bicicleta = new Bicicleta();
				bicicleta.setId(resultSet.getInt("id_bicicleta"));
				bicicleta.setModelo(resultSet.getString("nm_modelo"));
				bicicleta.setMarca(resultSet.getString("nm_marca"));
				bicicleta.setStatus(resultSet.getString("st_bicicleta").charAt(0));

				usuarioPlano.setUsuario(usuario);
				usuarioPlano.setPlano(plano);
				usuarioPlano.setBicicleta(bicicleta);
				usuarioPlano.setDataUltimaUtilizacao(resultSet.getDate("dt_ultima_utilizacao").toLocalDate());
				usuarioPlano.setKmRodados(resultSet.getInt("nr_km_rodados"));
				usuarioPlano.setStatus(resultSet.getString("st_usuario_plano").charAt(0));
				usuarioPlano.setDataEntregaBike(resultSet.getDate("dt_entrega_bike").toLocalDate());
				usuarioPlano.setDataRetornoBike(resultSet.getDate("dt_retorno_bike").toLocalDate());
				usuarioPlano.setDataPrevistaRetorno(resultSet.getDate("dt_prevista_retorno").toLocalDate());
				usuarioPlano.setPontos(resultSet.getInt("nr_pontos"));

				lista.add(usuarioPlano);
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
	 * @param usuarioPlano a ser atualizado
	 * @throws ObjectNotFoundException lancado quando não encontrado nenhum registro
	 */
	@Override
	public void atualizar(UsuarioPlano usuarioPlano) throws ObjectNotFoundException {
		try {
			Connection conexao = ConnectionFactory.getConnection();
			PreparedStatement stm = conexao.prepareStatement("update T_EM_USUARIO_PLANO set  " +
					" id_usuario = ?, id_plano = ?, id_bicicleta = ?, dt_ultima_utilizacao = ?, " +
					" nr_km_roddados = ?, st_usuario_plano = ?, dt_entrega_bike = ?," +
					" dt_retorno_bike = ?, dt_prevista_retorno = ?, nr_pontos = ? where " +
					" id_utilizacao = ?");

			stm.setInt(1, usuarioPlano.getUsuario().getId());
			stm.setInt(2, usuarioPlano.getPlano().getId());
			stm.setInt(3, usuarioPlano.getBicicleta().getId());
			stm.setDate(4, java.sql.Date.valueOf(usuarioPlano.getDataUltimaUtilizacao()));
			stm.setInt(5, usuarioPlano.getKmRodados());
			stm.setString(6, String.valueOf(usuarioPlano.getStatus()));
			stm.setDate(7, java.sql.Date.valueOf(usuarioPlano.getDataEntregaBike()));
			stm.setDate(8, java.sql.Date.valueOf(usuarioPlano.getDataRetornoBike()));
			stm.setDate(9, java.sql.Date.valueOf(usuarioPlano.getDataPrevistaRetorno()));
			stm.setInt(10, usuarioPlano.getPontos());
			stm.setInt(11, usuarioPlano.getId());

			stm.executeUpdate();

			conexao.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe (Driver) não existe");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar no banco de dados");
			e.printStackTrace();
		}
		if (usuarioPlano == null) {
			throw new ObjectNotFoundException("Essa utilizacao não pode ser encontrada");
		}
	}

	/**
	 * Método utilizado para remover um registro do banco
	 * @param usuarioPlano a ser removido
	 */
	@Override
	public void apagar(UsuarioPlano usuarioPlano) {
		try {
			Connection conexao = ConnectionFactory.getConnection();

			PreparedStatement stm = conexao.prepareStatement("delete from T_EM_USUARIO_PLANO where id_usuario_plano = ?");
			stm.setInt(1, usuarioPlano.getId());

			stm.executeQuery();

			conexao.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
