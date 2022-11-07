package br.com.fiap.emotion.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de conexões
 * @author Mariana
 *
 */
public class ConnectionFactory {

	/**
	 * Obtem uma conexão com o banco de dados
	 * @return Connection conexão com o banco de dados
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		//Registrar o driver do banco que será utilizado
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//Obter a conexão com o banco de dados
		Connection conexao = DriverManager.getConnection(
				"", "", "");

		return conexao;
	}
}
