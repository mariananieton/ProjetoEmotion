package br.com.fiap.emotion.model;

import java.util.StringJoiner;

public class EnderecoUsuario {

	private int id;

	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		return new StringJoiner(", ", EnderecoUsuario.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("usuario=" + usuario)
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
