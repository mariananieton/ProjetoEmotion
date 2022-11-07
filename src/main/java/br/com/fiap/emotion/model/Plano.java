package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class Plano {

	private int id;

	private String nome;

	private double valor;

	private TipoPlano tipo;

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoPlano getTipo() {
		return tipo;
	}

	public void setTipo(TipoPlano tipo) {
		this.tipo = tipo;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Plano.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("nome='" + nome + "'")
				.add("valor=" + valor)
				.add("tipo=" + tipo)
				.add("status=" + status)
				.toString();
	}
}
