package br.com.fiap.emotion.view;

import br.com.fiap.emotion.model.TelefoneEmpresa;
import br.com.fiap.emotion.model.TelefoneUsuario;
import br.com.fiap.emotion.model.TipoTelefone;

import java.util.Scanner;

public class TelefoneView {

	public static TelefoneUsuario criarTelefoneUsuario() {
		Scanner scanner = new Scanner(System.in);
		TelefoneUsuario telefone = new TelefoneUsuario();
		System.out.println("Informe o DDI: ");
		telefone.setDdi(scanner.nextInt());
		System.out.println("Informe o DDD : ");
		telefone.setDdd(scanner.nextInt());
		System.out.println("Informe o numero: ");
		telefone.setNumero(scanner.nextInt());
		System.out.println("Seu telefone é:");
		System.out.println("1 - Fixo");
		System.out.println("2 - Movel");
		telefone.setTipo(scanner.nextInt() == 1 ? TipoTelefone.FIXO : TipoTelefone.MOVEL);
		return telefone;
	}

	public static TelefoneEmpresa criarTelefoneEmpresa() {
		Scanner scanner = new Scanner(System.in);
		TelefoneEmpresa telefone = new TelefoneEmpresa();
		System.out.println("Informe o DDI: ");
		telefone.setDdi(scanner.nextInt());
		System.out.println("Informe o DDD : ");
		telefone.setDdd(scanner.nextInt());
		System.out.println("Informe o numero: ");
		telefone.setNumero(scanner.nextInt());
		System.out.println("Seu telefone é:");
		System.out.println("1 - Fixo");
		System.out.println("2 - Movel");
		telefone.setTipo(scanner.nextInt() == 1 ? TipoTelefone.FIXO : TipoTelefone.MOVEL);
		return telefone;
	}


}
