package model;

import java.util.ArrayList;
import java.util.List;

import view.TelaPerguntas;

public class Motor {

	private List<Variavel> variaveis;
	private List<Regra> regras;
	private String resultado = "Árvore da consulta:"; // Onde será escrito a "árvore"
	private static Motor instancia;
	
	private Motor() {
		this.variaveis = new ArrayList<>();
		this.regras = new ArrayList<>();
	}

	public void executar() {
		boolean objetivo = false;
		for (Regra regra : regras) {
			if (!objetivo) {
				resultado += "\n\nEntrando na regra de ordem " + (regras.indexOf(regra) + 1);
				if (regra.getDescricao().equalsIgnoreCase("")) {
					resultado += ".";
				} else {
					resultado += ": " + regra.getDescricao();
				}
				boolean condicoes = true;
				for (Sentenca premissa : regra.getPremissas()) {
					if (!variavelResultado(premissa)) {
						condicoes = false;
						break;
					}
				}
				if (condicoes) {
					resultado += "\nTodas as premissas da regra são verdadeiras. Logo, foram efetuadas as seguintes conclusões:";
					// Realizar conclusões, e lembrar que se uma variável for objetivo, encerrar
					for (Sentenca conclusao : regra.getConclusoes()) {
						for (Variavel variavel : variaveis) {
							if (variavel.equals(conclusao.getVariavel())) {
								for (RespostaVariavel respostaVariavel : variavel.getRespostas()) {
									if(respostaVariavel.getValor().equalsIgnoreCase(conclusao.getValorSelecao())) {
										respostaVariavel.setSelecionado(true);
										resultado += "\nVariável " + variavel.getNome() + " é IGUAL a "  + respostaVariavel.getValor() + ".";
									} else if(variavel.getTipo() != TipoVariavel.MULTIVALORADO && respostaVariavel.isSelecionado()) {
										respostaVariavel.setSelecionado(false);
										resultado += "\nVariável " + variavel.getNome() + " NÃO é mais IGUAL a " + respostaVariavel.getValor() + ".";
									}
								}
								if (variavel.isObjetivo()) {
									objetivo = true;
									// Instanciar tela com o resultado, árvore e
									// valores das variáveis
								}

								break;
							}
						}
					}
				}
			}
		}
		// Instanciar árvore e valores das variáveis. Se chegou aqui, não alcançou nenhum objetivo.
		if(!objetivo) {
			
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
							resultado += "\nNão foi possível realizar as conclusões da regra, pois a variável " + variavel.getNome() + " é IGUAL a " + respostaVariavel.getValor() + ", e deveria ser " + premissa.getOperadorSelecionado() + " " + premissa.getValorSelecao() + "."; 
							return false;
						}
						
					}
				}
				break;
			}
		}
		// Tela para perguntar valor(es) da variavel da premissa
//		TelaPerguntas pergunta = new TelaPerguntas(premissa);
//		pergunta.setVisible(true);
		return false;
	}
	
	// Para verificar, quando for excluir uma variável, se ela está sendo usada em alguma regra
	public String verificarVariavelUsada(Variavel variavel) {
		String texto = "";
		for(Regra regra : regras) {
			for(Sentenca premissa: regra.getPremissas()) {
				if(premissa.getVariavel().equals(variavel)) {
					texto += "\nRegra de ordem " + (regras.indexOf(regra) + 1) + ", nas premissas.";
				}
			}
			for(Sentenca conclusao: regra.getConclusoes()) {
				if(conclusao.getVariavel().equals(variavel)) {
					texto += "\nRegra de ordem " + (regras.indexOf(regra) + 1) + ", nas conclusões.";
				}				
			}
		}
		return texto;
	}
	
	// Exclui além da variável, as regras onde ela está sendo utilizada
	public void excluirVariavelUsada(Variavel variavel) {
		List<Regra> regrasVariavel = new ArrayList<Regra>();
		for(Regra regra : regras) {
			for(Sentenca premissa: regra.getPremissas()) {
				if(premissa.getVariavel().equals(variavel) && !regrasVariavel.contains(regra)) {
					regrasVariavel.add(regra);
				}
			}
			
			for(Sentenca conclusao: regra.getConclusoes()) {
				if(conclusao.getVariavel().equals(variavel) && !regrasVariavel.contains(regra)) {
					regrasVariavel.add(regra);
				}
			}
		}
		variaveis.remove(variavel);
		for(Regra regraVariavel : regrasVariavel) {
			regras.remove(regraVariavel);
		}
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
