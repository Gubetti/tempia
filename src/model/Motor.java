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
					System.out.println(premissa.getVariavel().getNome() + " " + premissa.getOperadorSelecionado() + " " + premissa.getValorSelecao());
					if (!variavelResultado(premissa, regra.getDescricao(), regras.indexOf(regra) + 1)) {
						condicoes = false;
						break;
					}
				}
				if (condicoes) {
					resultado += "\nTodas as premissas da regra são verdadeiras. Logo, foram efetuadas as seguintes conclusões:";
					// Realizar conclusões, e lembrar que se uma variável for objetivo, encerrar
					for (Sentenca conclusao : regra.getConclusoes()) {
						for (RespostaVariavel respostaVariavel : conclusao.getVariavel().getRespostas()) {
							if(respostaVariavel.getValor().equalsIgnoreCase(conclusao.getValorSelecao())) {
								respostaVariavel.setSelecionado(true);
								resultado += "\nVariável " + conclusao.getVariavel().getNome() + " é IGUAL a "  + respostaVariavel.getValor() + ".";
							} else if(conclusao.getVariavel().getTipo() != TipoVariavel.MULTIVALORADO && respostaVariavel.isSelecionado()) {
								respostaVariavel.setSelecionado(false);
								resultado += "\nVariável " + conclusao.getVariavel().getNome() + " NÃO é mais IGUAL a " + respostaVariavel.getValor() + ".";
							}
						}
						if (conclusao.getVariavel().isObjetivo()) {
							objetivo = true;
									// Instanciar tela com o resultado, árvore e
									// valores das variáveis
						}
					}
				}
			}
		}
		// Instanciar árvore e valores das variáveis. Se chegou aqui, não alcançou nenhum objetivo.
		if(!objetivo) {
			
		}
		limparRespostas();
		System.out.println(resultado);
	}
	
	private boolean variavelResultado(Sentenca premissa, String descricaoRegra, int ordem) {
		if(!verificarVariavelSelecionada(premissa)) {
			// Tela para perguntar valor(es) da variavel da premissa
			resultado += "\nPerguntando ao usuário o valor da variável " + premissa.getVariavel().getNome() + ".";
			TelaPerguntas pergunta = new TelaPerguntas(premissa, descricaoRegra, ordem);
			pergunta.setVisible(true);
		}
		
		boolean valor = verificarValoresVarivael(premissa);
		if(!valor) {
			resultado += "\nNão foi possível realizar as conclusões da regra, pois a variável " + premissa.getVariavel().getNome() + " NÃO é " + premissa.getOperadorSelecionado() + " " + premissa.getValorSelecao() + ".";
		}
		return valor;
	}
	
	private boolean verificarVariavelSelecionada(Sentenca premissa) {
		for(RespostaVariavel respostaVariavel : premissa.getVariavel().getRespostas()) {
			if(respostaVariavel.isSelecionado()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean verificarValoresVarivael(Sentenca premissa) {
		List<String> respostaSelecionadas = new ArrayList<String>(); // Por causa do diferente com multivalorado
		for(RespostaVariavel respostaVariavel : premissa.getVariavel().getRespostas()) {
			if(respostaVariavel.isSelecionado()) {	
				int valorVariavel = 0, valorSentenca = 0;
				if(premissa.getVariavel().getTipo() == TipoVariavel.NUMERICO) {
					valorVariavel = Integer.parseInt(respostaVariavel.getValor());
					valorSentenca = Integer.parseInt(premissa.getValorSelecao());	
				}
				
				switch (premissa.getOperadorSelecionado()) {
				case IGUAL:
					if(respostaVariavel.getValor().equalsIgnoreCase(premissa.getValorSelecao())) {
						return true;
					}
					break;
				case DIFERENTE:
					respostaSelecionadas.add(respostaVariavel.getValor());
					break;
				case MAIOR_IGUAL:
					if(valorVariavel >= valorSentenca) {
						return true;
					}
					break;
				case MAIOR_QUE:
					if(valorVariavel > valorSentenca) {
						return true;
					}
					break;
				case MENOR_IGUAL:
					if(valorVariavel <= valorSentenca) {
						return true;
					}
					break;
				case MENOR_QUE:
					if(valorVariavel < valorSentenca) {
						return true;
					}
					break;
				}
			}
		}
		
		if (!respostaSelecionadas.isEmpty()) {
			for (String valor : respostaSelecionadas) {
				if (valor.equalsIgnoreCase(premissa.getValorSelecao())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean inserirVariavel(Variavel novaVariavel) {
		for(Variavel variavel : variaveis) {
			if(variavel.getNome().equalsIgnoreCase(novaVariavel.getNome())) {
				return false;
			}
		}
		
		variaveis.add(novaVariavel);
		return true;
	}
	
	// Para verificar, quando for excluir uma variável, se ela está sendo usada em alguma regra
	public String verificarVariavelUsada(Variavel variavel) {
		String texto = "";
		for(Regra regra : regras) {
			for(Sentenca premissa: regra.getPremissas()) {
				if(premissa.getVariavel().equals(variavel)) {
					texto += "\nRegra de ordem " + (regras.indexOf(regra) + 1) + ", na(s) premissa(s).";
				}
			}
			for(Sentenca conclusao: regra.getConclusoes()) {
				if(conclusao.getVariavel().equals(variavel)) {
					texto += "\nRegra de ordem " + (regras.indexOf(regra) + 1) + ", na(s) conclusão(ões).";
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
	
	private void limparRespostas() {
		for(Variavel variavel : variaveis) {
			for(RespostaVariavel respostaVariavel : variavel.getRespostas()) {
				respostaVariavel.setSelecionado(false);
			}
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
	
	public static void main(String[] args) {
		//Teste de execução
		Variavel v1 = new Variavel("v1", false, TipoVariavel.MULTIVALORADO);
		v1.setPergunta("Você gosta");
		v1.inserirValor(new RespostaVariavel("Notícias"));
		v1.inserirValor(new RespostaVariavel("Esporte"));
		v1.inserirValor(new RespostaVariavel("Artes"));
		v1.inserirValor(new RespostaVariavel("Humor"));
		Motor.getInstancia().getVariaveis().add(v1);
		
		Variavel con1 = new Variavel("con1", false, TipoVariavel.UNIVALORADO);
		con1.inserirValor(new RespostaVariavel("Sim"));
		con1.inserirValor(new RespostaVariavel("Não"));
		Motor.getInstancia().getVariaveis().add(con1);
		
		Variavel v2 = new Variavel("v2", false, TipoVariavel.UNIVALORADO);
		v2.inserirValor(new RespostaVariavel("Sou"));
		v2.inserirValor(new RespostaVariavel("Não sou"));
		Motor.getInstancia().getVariaveis().add(v2);
		
		Variavel obj = new Variavel("OBJETIVO", true, TipoVariavel.UNIVALORADO);
		obj.inserirValor(new RespostaVariavel("Legal :)"));
		obj.inserirValor(new RespostaVariavel("Não legal :-("));
		Motor.getInstancia().getVariaveis().add(obj);
		
		Sentenca premissa1 = new Sentenca(v1);
		premissa1.setOperadorSelecionado(Operador.IGUAL);
		premissa1.setValorSelecao(v1.getRespostas().get(0).getValor());
		
		Sentenca conclusao1 = new Sentenca(con1);
		conclusao1.setOperadorSelecionado(Operador.IGUAL);
		conclusao1.setValorSelecao(con1.getRespostas().get(0).getValor());
		
		Regra regra1 = new Regra();
		regra1.getPremissas().add(premissa1);
		regra1.getConclusoes().add(conclusao1);
		Motor.getInstancia().getRegras().add(regra1);
		
		Sentenca premissa2 = new Sentenca(v1);
		premissa2.setOperadorSelecionado(Operador.IGUAL);
		premissa2.setValorSelecao(v1.getRespostas().get(2).getValor());
		
		Sentenca premissa3 = new Sentenca(v2);
		premissa3.setOperadorSelecionado(Operador.DIFERENTE);
		premissa3.setValorSelecao(v2.getRespostas().get(1).getValor());
		
		Sentenca conclusao2 = new Sentenca(con1);
		conclusao2.setOperadorSelecionado(Operador.IGUAL);
		conclusao2.setValorSelecao(con1.getRespostas().get(1).getValor());
		
		Regra regra2 = new Regra();
		regra2.setDescricao("Teste de nome de regra");
		regra2.getPremissas().add(premissa2);
		regra2.getPremissas().add(premissa3);
		regra2.getConclusoes().add(conclusao2);
		Motor.getInstancia().getRegras().add(regra2);
		
		Sentenca premissa4 = new Sentenca(con1);
		premissa4.setOperadorSelecionado(Operador.IGUAL);
		premissa4.setValorSelecao(con1.getRespostas().get(0).getValor());
		
		Sentenca conclusaoObjetivo1 = new Sentenca(obj);
		conclusaoObjetivo1.setOperadorSelecionado(Operador.IGUAL);
		conclusaoObjetivo1.setValorSelecao(obj.getRespostas().get(0).getValor());
		
		Regra regraObjetivo1 = new Regra();
		regraObjetivo1.getPremissas().add(premissa4);
		regraObjetivo1.getConclusoes().add(conclusaoObjetivo1);
		Motor.getInstancia().getRegras().add(regraObjetivo1);
		
		Sentenca premissa5 = new Sentenca(con1);
		premissa5.setOperadorSelecionado(Operador.IGUAL);
		premissa5.setValorSelecao(con1.getRespostas().get(1).getValor());
		
		Sentenca conclusaoObjetivo2 = new Sentenca(obj);
		conclusaoObjetivo2.setOperadorSelecionado(Operador.IGUAL);
		conclusaoObjetivo2.setValorSelecao(obj.getRespostas().get(1).getValor());

		Regra regraObjetivo2 = new Regra();
		regraObjetivo2.getPremissas().add(premissa5);
		regraObjetivo2.getConclusoes().add(conclusaoObjetivo2);
		Motor.getInstancia().getRegras().add(regraObjetivo2);
		
		Motor.getInstancia().executar();
	}
}
