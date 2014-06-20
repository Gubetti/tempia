package model;

import java.util.ArrayList;
import java.util.List;

public class Motor {

	private List<Variavel> variaveis;
	private List<Regra> regras;
	private String resultado; // Onde será escrito a "árvore"
	private static Motor instancia;
	
	private Motor() {
		this.variaveis = new ArrayList<>();
		this.regras = new ArrayList<>();
	}

	public void executar() {
		for(Regra regra : regras) {
			boolean condicoes = true;
			for(Sentenca premissa : regra.getPremissas()) {
				if(!variavelResultado(premissa)) {
					condicoes = false;
					break;
				}
			}
			if(condicoes) {
				// Realizar conclusões, e lembrar que se uma variável for objetivo, encerrar
				for(Sentenca conclusao : regra.getConclusoes()) {
					for(Variavel variavel : variaveis) {
						if(variavel.equals(conclusao.getVariavel())) {
							for(RespostaVariavel respostaVariavel : variavel.getRespostas()) {
								if(respostaVariavel.getValor().equalsIgnoreCase(conclusao.getValorSelecao())) {
									respostaVariavel.setOperador(conclusao.getOperadorSelecionado());
									respostaVariavel.setSelecionado(true);
								} else if(variavel.getTipo() != TipoVariavel.MULTIVALORADO) {
									respostaVariavel.setOperador(null);
									respostaVariavel.setSelecionado(false);
								}
							}
							if(variavel.isObjetivo()) {
								// Encerrar
							}
							
							break;
						}
					}
				}
			}
		}
	}
	
	private boolean variavelResultado(Sentenca premissa) {
		for(Variavel variavel : variaveis) {
			if(variavel.equals(premissa.getVariavel())) {
				for(RespostaVariavel respostaVariavel : variavel.getRespostas()) {
					if(respostaVariavel.getValor().equalsIgnoreCase(premissa.getValorSelecao()) && respostaVariavel.isSelecionado()) {
						if(premissa.getOperadorSelecionado() == Operador.IGUAL || premissa.getOperadorSelecionado() == Operador.MAIOR_IGUAL || premissa.getOperadorSelecionado() == Operador.MENOR_IGUAL) {
							return true;
						} else {
							return false;
						}
						
					}
				}
				break;
			}
		}
		// Tela para perguntar valor(es) da variavel da premissa
		return false;
	}
	
	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public static Motor getInstancia() {
		if(instancia == null) {
			instancia = new Motor();
		}
		return instancia;
	}
	
/*	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<>();
		strings.add("A");
		strings.add("B");
		strings.add("C");
		for(int i = 0;  i < strings.size(); i++) {
			System.out.println(strings.get(i) + " " + i);
		}
		strings.remove(1);
		for(int i = 0;  i < strings.size(); i++) {
			System.out.println(strings.get(i) + " " + i);
		}
		strings.add("AF");
		for(int i = 0;  i < strings.size(); i++) {
			System.out.println(strings.get(i) + " " + i);
		}
	}*/
}
