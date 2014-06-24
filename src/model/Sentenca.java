package model;

import java.io.Serializable;


public class Sentenca implements Serializable {

	private static final long serialVersionUID = 6073823444652606993L;
	private Variavel variavel;
	private String valorSelecao;
	private Operador operadorSelecionado;

	public Sentenca(Variavel variavel) {
		this.variavel = variavel;
	}
	
	public void gravar(String valorSelecao, Operador operadorSelecionado) {
		this.valorSelecao = valorSelecao;
		this.operadorSelecionado = operadorSelecionado;
	}
	
	public Variavel getVariavel() {
		return variavel;
	}

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}

	public String getValorSelecao() {
		return valorSelecao;
	}

	public void setValorSelecao(String valorSelecao) {
		this.valorSelecao = valorSelecao;
	}

	public Operador getOperadorSelecionado() {
		return operadorSelecionado;
	}

	public void setOperadorSelecionado(Operador operadorSelecionado) {
		this.operadorSelecionado = operadorSelecionado;
	}
}