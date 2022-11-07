package br.com.fiap.emotion.view;

import br.com.fiap.emotion.model.EmailUsuario;
import br.com.fiap.emotion.model.EnderecoUsuario;
import br.com.fiap.emotion.model.TelefoneUsuario;
import br.com.fiap.emotion.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UsuarioView {

	public static Usuario criarUsuario() {
		Scanner scanner = new Scanner(System.in);
		Usuario usuario = new Usuario();
		System.out.println("Nome : ");
		usuario.setNome(scanner.nextLine());
		System.out.println("Data de Nascimento (aaaa-mm-dd) : ");
		usuario.setDataNascimento(LocalDate.parse(scanner.nextLine()));
		System.out.println("Email : ");
		final EmailUsuario email = new EmailUsuario();
		email.setEmail(scanner.nextLine());
		email.setStatus('A');
		usuario.setEmail(email);
		System.out.println("Login : ");
		usuario.setLogin(scanner.nextLine());
		System.out.println("Senha : ");
		usuario.setSenha(scanner.nextLine());
		System.out.println("Cpf : ");
		usuario.setCpf(scanner.nextInt());
		System.out.println("Total de Pontos : ");
		usuario.setTotalPontos(scanner.nextInt());
		usuario.setStatus('A');


		System.out.println("-------------- Sobre Telefone --------------");
		TelefoneUsuario telefone = TelefoneView.criarTelefoneUsuario();
		usuario.setTelefone(telefone);

		System.out.println("-------------- Sobre Endereço --------------");
		EnderecoUsuario endereco = EnderecoView.criarEnderecoUsuario();
		usuario.setEndereco(endereco);

		return usuario;
	}

	public static void listarUsuarios(List<Usuario> usuarioList) {
		for (int i = 0; i < usuarioList.size(); i++) {
			Usuario usuario = usuarioList.get(i);
			System.out.println((i + 1) + " - " + usuario);
		}
	}
}
