package model;

import java.util.ArrayList;
import java.util.List;

public class Sentenca {

	private Variavel variavel;
	private Operador operadorSelecionado;
	private List<Operador> operadores;

	public Sentenca(Variavel variavel) {
		this.variavel = variavel;
		inserirOperadores();
	}

	public void inserirOperadores() {
		this.operadores = new ArrayList<>();
		
		operadores.add(Operador.IGUAL);
		operadores.add(Operador.DIFERENTE);

		if (variavel.getTipo() == TipoVariavel.NUMERICO) {
			operadores.add(Operador.MAIOR_IGUAL);
			operadores.add(Operador.MAIOR_QUE);
			operadores.add(Operador.MENOR_IGUAL);
			operadores.add(Operador.MENOR_QUE);
		}
	}
	
	public Variavel getVariavel() {
		return variavel;
	}

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}

	public Operador getOperadorSelecionado() {
		return operadorSelecionado;
	}

	public void setOperadorSelecionado(Operador operadorSelecionado) {
		this.operadorSelecionado = operadorSelecionado;
	}

	public List<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}
}