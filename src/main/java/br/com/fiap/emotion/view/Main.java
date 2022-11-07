package br.com.fiap.emotion.view;

import br.com.fiap.emotion.dao.EmpresaDao;
import br.com.fiap.emotion.dao.UsuarioDao;
import br.com.fiap.emotion.model.Empresa;
import br.com.fiap.emotion.model.Usuario;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {

	private static final UsuarioDao usuarioDao = new UsuarioDao();
	private static final EmpresaDao empresaDao = new EmpresaDao();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		listarOpcoes();
		while (scanner.hasNext()) {
			int option = scanner.nextInt();
			switch (option) {
				case 1:
					Usuario usuario = UsuarioView.criarUsuario();
					usuarioDao.cadastrar(usuario);
					querContinuar();
					break;
				case 2:
					List<Usuario> usuarioList = usuarioDao.consultarTodos();
					UsuarioView.listarUsuarios(usuarioList);
					querContinuar();
					break;
				case 3:
					Empresa empresa = EmpresaView.criarEmpresa();
					empresaDao.cadastrar(empresa);
					querContinuar();
					break;
				case 4:
					List<Empresa> empresaList = empresaDao.consultarTodos();
					EmpresaView.listarEmpresas(empresaList);
					querContinuar();
					break;
				case 0:
					System.out.println("Finalizando sessão");
					return;
			}
			listarOpcoes();
		}
	}

	private static void querContinuar() {
		System.out.println("Deseja continuar? (1 -> Sim / 2 -> Não)");
		Scanner scanner = new Scanner(System.in);
		if (scanner.nextInt() == 2) {
			System.exit(500);
		}
	}

	private static void listarOpcoes() {
		System.out.println("Selecione a opção desejada: ");
		StringJoiner options = new StringJoiner("\n");
		options.add("---------------------------");
		options.add("1 - Cadastrar Usuário.");
		options.add("2 - Listar Usuários.");
		options.add("3 - Cadastrar Empresa.");
		options.add("4 - Listar Empresas.");
		options.add("0 - Sair.");
		options.add("---------------------------");
		System.out.println(options);
	}
}
