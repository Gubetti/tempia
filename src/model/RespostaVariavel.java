package model;

public class RespostaVariavel {

	private String valor;
	private boolean selecionado;
	private Operador operador;
	
	public RespostaVariavel(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public boolean isSelecionado() {
		return selecionado;
	}
	
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	public Operador getOperador() {
		return operador;
	}
	
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
}
