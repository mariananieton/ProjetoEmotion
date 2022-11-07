package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class EmailUsuario {


	private int id;

	private Usuario usuario;

	private String email;

	private char status;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", EmailUsuario.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("usuario=" + usuario)
				.add("email='" + email + "'")
				.add("status=" + status)
				.toString();
	}
}
