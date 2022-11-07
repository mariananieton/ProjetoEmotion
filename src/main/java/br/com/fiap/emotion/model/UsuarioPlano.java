package br.com.fiap.emotion.model;

import java.time.LocalDate;
import java.util.StringJoiner;

public class UsuarioPlano {

	private int id;

	private Usuario usuario;

	private Plano plano;

	private Bicicleta bicicleta;

	private LocalDate dataUltimaUtilizacao;

	private int kmRodados;

	private char status;

	private LocalDate dataEntregaBike;

	private LocalDate dataPrevistaRetorno;

	private LocalDate dataRetornoBike;

	private int pontos;

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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public LocalDate getDataUltimaUtilizacao() {
		return dataUltimaUtilizacao;
	}

	public void setDataUltimaUtilizacao(LocalDate dataUltimaUtilizacao) {
		this.dataUltimaUtilizacao = dataUltimaUtilizacao;
	}

	public int getKmRodados() {
		return kmRodados;
	}

	public void setKmRodados(int kmRodados) {
		this.kmRodados = kmRodados;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public LocalDate getDataEntregaBike() {
		return dataEntregaBike;
	}

	public void setDataEntregaBike(LocalDate dataEntregaBike) {
		this.dataEntregaBike = dataEntregaBike;
	}

	public LocalDate getDataPrevistaRetorno() {
		return dataPrevistaRetorno;
	}

	public void setDataPrevistaRetorno(LocalDate dataPrevistaRetorno) {
		this.dataPrevistaRetorno = dataPrevistaRetorno;
	}

	public LocalDate getDataRetornoBike() {
		return dataRetornoBike;
	}

	public void setDataRetornoBike(LocalDate dataRetornoBike) {
		this.dataRetornoBike = dataRetornoBike;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UsuarioPlano.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("usuario=" + usuario)
				.add("plano=" + plano)
				.add("bicicleta=" + bicicleta)
				.add("dataUltimaUtilizacao=" + dataUltimaUtilizacao)
				.add("kmRodados=" + kmRodados)
				.add("status=" + status)
				.add("dataEntregaBike=" + dataEntregaBike)
				.add("dataPrevistaRetorno=" + dataPrevistaRetorno)
				.add("dataRetornoBike=" + dataRetornoBike)
				.add("pontos=" + pontos)
				.toString();
	}
}
