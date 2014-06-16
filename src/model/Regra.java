package model;

import java.util.List;

public class Regra {

	private String descricao;
	private List<Sentenca> premissas;
	private List<Sentenca> conclusoes;
	
	public Regra() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Sentenca> getPremissas() {
		return premissas;
	}

	public void setPremissas(List<Sentenca> premissas) {
		this.premissas = premissas;
	}

	public List<Sentenca> getConclusoes() {
		return conclusoes;
	}

	public void setConclusoes(List<Sentenca> conclusoes) {
		this.conclusoes = conclusoes;
	}
}
