package model;

public class RespostaVariavel {

	private String valor;
	private boolean selecionado;
	
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

	@Override
	public String toString() {
		return valor;
	}
}
