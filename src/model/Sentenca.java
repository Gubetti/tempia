package model;


public class Sentenca {

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