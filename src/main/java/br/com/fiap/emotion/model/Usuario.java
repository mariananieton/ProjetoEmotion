package br.com.fiap.emotion.model;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Usuario {

	private int id;

	private String nome;

	private LocalDate dataNascimento;

	private EnderecoUsuario endereco;

	private TelefoneUsuario telefone;

	private EmailUsuario email;

	private int cpf;

	private String login;

	private String senha;

	private int totalPontos;

	private char status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnderecoUsuario getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoUsuario endereco) {
		this.endereco = endereco;
	}

	public TelefoneUsuario getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneUsuario telefone) {
		this.telefone = telefone;
	}

	public EmailUsuario getEmail() {
		return email;
	}

	public void setEmail(EmailUsuario email) {
		this.email = email;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTotalPontos() {
		return totalPontos;
	}

	public void setTotalPontos(int totalPontos) {
		this.totalPontos = totalPontos;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Usuario.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("nome='" + nome + "'")
				.add("dataNascimento=" + dataNascimento)
				.add("endereco=" + endereco)
				.add("telefone=" + telefone)
				.add("email=" + email)
				.add("cpf=" + cpf)
				.add("login='" + login + "'")
				.add("senha='" + senha + "'")
				.add("totalPontos=" + totalPontos)
				.add("status=" + status)
				.toString();
	}
}
