package br.com.fiap.emotion.view;

import br.com.fiap.emotion.model.EmailEmpresa;
import br.com.fiap.emotion.model.Empresa;
import br.com.fiap.emotion.model.EnderecoEmpresa;
import br.com.fiap.emotion.model.TelefoneEmpresa;

import java.util.List;
import java.util.Scanner;

public class EmpresaView {

	public static Empresa criarEmpresa() {
		Scanner scanner = new Scanner(System.in);
		Empresa empresa = new Empresa();
		System.out.println("Razão Social : ");
		empresa.setRazaoSocial(scanner.nextLine());
		System.out.println("CNPJ : ");
		empresa.setCnpj(scanner.nextInt());
		System.out.println("Email : ");
		final EmailEmpresa email = new EmailEmpresa();
		email.setEmail(scanner.nextLine());
		email.setStatus('A');
		empresa.setEmail(email);

		System.out.println("-------------- Sobre Telefone --------------");
		TelefoneEmpresa telefone = TelefoneView.criarTelefoneEmpresa();
		empresa.setTelefone(telefone);

		System.out.println("-------------- Sobre Endereço --------------");
		EnderecoEmpresa endereco = EnderecoView.criarEnderecoEmpresa();
		empresa.setEndereco(endereco);

		return empresa;
	}

	public static void listarEmpresas(List<Empresa> empresaList) {
		for (int i = 0; i < empresaList.size(); i++) {
			Empresa empresa = empresaList.get(i);
			System.out.println((i + 1) + " - " + empresa);
		}
	}
}
