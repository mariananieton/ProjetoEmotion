package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class Empresa {

	private int id;

	private String razaoSocial;

	private EnderecoEmpresa endereco;

	private TelefoneEmpresa telefone;

	private EmailEmpresa email;

	private int cnpj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public EnderecoEmpresa getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEmpresa endereco) {
		this.endereco = endereco;
	}

	public TelefoneEmpresa getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneEmpresa telefone) {
		this.telefone = telefone;
	}

	public EmailEmpresa getEmail() {
		return email;
	}

	public void setEmail(EmailEmpresa email) {
		this.email = email;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Empresa.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("razaoSocial='" + razaoSocial + "'")
				.add("endereco=" + endereco)
				.add("telefone=" + telefone)
				.add("email=" + email)
				.add("cnpj=" + cnpj)
				.toString();
	}
}
