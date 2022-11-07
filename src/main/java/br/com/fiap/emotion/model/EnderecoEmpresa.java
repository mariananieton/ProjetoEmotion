package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class EnderecoEmpresa {

	private int id;

	private Empresa empresa;

	private int cep;

	private String bairro;

	private String logradouro;

	private int numero;

	private String complemento;

	private String referencia;

	private String cidade;

	private String estado;

	private String siglaEstado;

	private String pais;

	private TipoEndereco tipo;

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

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", EnderecoEmpresa.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("empresa=" + empresa)
				.add("cep=" + cep)
				.add("bairro='" + bairro + "'")
				.add("logradouro='" + logradouro + "'")
				.add("numero=" + numero)
				.add("complemento='" + complemento + "'")
				.add("referencia='" + referencia + "'")
				.add("cidade='" + cidade + "'")
				.add("estado='" + estado + "'")
				.add("siglaEstado='" + siglaEstado + "'")
				.add("pais='" + pais + "'")
				.add("tipo=" + tipo)
				.toString();
	}
}
