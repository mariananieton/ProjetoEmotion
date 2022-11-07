package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class TelefoneUsuario {

	private int id;

	private Usuario usuario;

	private int ddi;

	private int ddd;

	private int numero;

	private char status;

	private TipoTelefone tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getDdi() {
		return ddi;
	}

	public void setDdi(int ddi) {
		this.ddi = ddi;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", TelefoneUsuario.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("usuario=" + usuario)
				.add("ddi=" + ddi)
				.add("ddd=" + ddd)
				.add("numero=" + numero)
				.add("status=" + status)
				.add("tipo=" + tipo)
				.toString();
	}
}
