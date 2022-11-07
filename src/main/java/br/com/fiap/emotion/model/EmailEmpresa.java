package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class EmailEmpresa {

	private int id;

	private Empresa empresa;

	private String email;

	private char status;

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
		return new StringJoiner(", ", EmailEmpresa.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("empresa=" + empresa)
				.add("email='" + email + "'")
				.add("status=" + status)
				.toString();
	}
}
