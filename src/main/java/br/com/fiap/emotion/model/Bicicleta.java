package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class Bicicleta {

	private int id;

	private String modelo;

	private String marca;

	private char status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Bicicleta.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("modelo='" + modelo + "'")
				.add("marca='" + marca + "'")
				.add("status=" + status)
				.toString();
	}
}
