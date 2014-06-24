package model;

import java.io.Serializable;

public class RespostaVariavel implements Serializable{

	private static final long serialVersionUID = 1734857364085987324L;
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
