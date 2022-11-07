package br.com.fiap.emotion.view;

import br.com.fiap.emotion.model.EnderecoEmpresa;
import br.com.fiap.emotion.model.EnderecoUsuario;
import br.com.fiap.emotion.model.TipoEndereco;

import java.util.Scanner;

public class EnderecoView {

	public static EnderecoUsuario criarEnderecoUsuario() {
		Scanner scanner = new Scanner(System.in);
		EnderecoUsuario endereco = new EnderecoUsuario();
		System.out.println("Pais : ");
		endereco.setPais(scanner.nextLine());
		System.out.println("Estado : ");
		endereco.setEstado(scanner.nextLine());
		System.out.println("Sigla do Estado : ");
		endereco.setSiglaEstado(scanner.nextLine());
		System.out.println("Cidade : ");
		endereco.setCidade(scanner.nextLine());
		System.out.println("Logradouro : ");
		endereco.setLogradouro(scanner.nextLine());
		System.out.println("Bairro : ");
		endereco.setBairro(scanner.nextLine());
		System.out.println("Numero : ");
		endereco.setNumero(scanner.nextInt());
		System.out.println("CEP : ");
		endereco.setCep(scanner.nextInt());
		System.out.println("Complemento : ");
		String complemento = scanner.nextLine();
		if (complemento != null) {
			endereco.setComplemento(scanner.nextLine());
		}
		System.out.println("Referencia : ");
		String referencia = scanner.nextLine();
		if (referencia != null) {
			endereco.setReferencia(scanner.nextLine());
		} System.out.println("Escolha o tipo do seu endereço : ");
		System.out.println("1 - Residencial");
		System.out.println("2 - Comercial");
		endereco.setTipo(scanner.nextInt() == 1 ? TipoEndereco.RESIDENCIAL : TipoEndereco.COMERCIAL);

		return endereco;
	}

	public static EnderecoEmpresa criarEnderecoEmpresa() {
		Scanner scanner = new Scanner(System.in);
		EnderecoEmpresa endereco = new EnderecoEmpresa();
		System.out.println("Pais : ");
		endereco.setPais(scanner.nextLine());
		System.out.println("Estado : ");
		endereco.setEstado(scanner.nextLine());
		System.out.println("Sigla do Estado : ");
		endereco.setSiglaEstado(scanner.nextLine());
		System.out.println("Cidade : ");
		endereco.setCidade(scanner.nextLine());
		System.out.println("Logradouro : ");
		endereco.setLogradouro(scanner.nextLine());
		System.out.println("Bairro : ");
		endereco.setBairro(scanner.nextLine());
		System.out.println("CEP : ");
		endereco.setCep(scanner.nextInt());
		System.out.println("Numero : ");
		endereco.setNumero(scanner.nextInt());
		System.out.println("Complemento : ");
		String complemento = scanner.nextLine();
		if (complemento != null) {
			endereco.setComplemento(scanner.nextLine());
		}
		System.out.println("Referencia : ");
		String referencia = scanner.nextLine();
		if (referencia != null) {
			endereco.setReferencia(scanner.nextLine());
		} System.out.println("Escolha o tipo do seu endereço : ");
		System.out.println("1 - Residencial");
		System.out.println("2 - Comercial");
		endereco.setTipo(scanner.nextInt() == 1 ? TipoEndereco.RESIDENCIAL : TipoEndereco.COMERCIAL);

		return endereco;
	}
}
