package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class Recompensas {

	private int id;

	private Empresa empresa;

	private String nome;

	private int valor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Recompensas.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("empresa=" + empresa)
				.add("nome='" + nome + "'")
				.add("valor=" + valor)
				.toString();
	}
}
